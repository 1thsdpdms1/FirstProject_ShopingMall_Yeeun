package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.dto.ShopFileDto;
import org.spring.e1i4TeamProject.shop.entity.*;
import org.spring.e1i4TeamProject.shop.repository.*;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.ShopServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopService implements ShopServiceImpl {


  private static final String SUCCESS_LIKE_SHOP = "좋아요 처리 완료";
  private static final String SUCCESS_UNLIKE_SHOP = "좋아요 취소 완료";

  private final ShopRepository shopRepository;
  private final ShopFileRepository shopFileRepository;
  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final CartShopListRepository cartShopListRepository;
  private final ShopLikeRepository shopLikeRepository;

  @Override
  public void insertShop(ShopDto shopDto) throws IOException {
    if (shopDto.getShopFile().isEmpty()) {
      shopDto.setMemberEntity(MemberEntity.builder()
          .id(shopDto.getMemberId())
          .build());
      ShopEntity shopEntity = ShopEntity.toInsertShopEntity(shopDto);
      shopRepository.save(shopEntity);
    } else {

      MultipartFile shopFile = shopDto.getShopFile(); //1.파일을 가져온다 dto
      String oldFilename = shopFile.getOriginalFilename(); //원본파일이름
      UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 클래스

      String newFileName = uuid + "_" + oldFilename; //저장파일이름 (보안)
      String filePath = "C:/e1i4_file/" + newFileName; //실제파일 저장경로+이름
      //실제 파일 저장 실행
      shopFile.transferTo(new File(filePath));//저장 예외처리

      //1.게시글저장
      shopDto.setMemberEntity(MemberEntity.builder()
          .id(shopDto.getMemberId())
          .build());
      ShopEntity shopEntity = ShopEntity.toInsertFileShopEntity(shopDto);
      Long shopId = shopRepository.save(shopEntity).getId();//저장실행->게시글의 아이디를 가져온다

      //2.저장한후 ID에 해당하는 게시글조회
      Optional<ShopEntity> shopEntity2 = shopRepository.findById(shopId);//id에 해당하는 게시글
      if (shopEntity2.isPresent()) {

        ShopEntity shopEntity1 = shopEntity2.get();
        ShopFileDto shopfileDto = ShopFileDto.builder().shopOldFileName(oldFilename).shopNewFileName(newFileName).shopEntity(shopEntity1).build();

        ShopFileEntity shopfileEntity = ShopFileEntity.toInsertShopFile(shopfileDto);
        shopFileRepository.save(shopfileEntity);

      } else {
        throw new IllegalArgumentException("아이디없");
      }
    }
  }
  @Override
  public void updateShopHit(Long id) {
    shopRepository.updateShopHit(id);
  }

  @Override
  public ShopDto detail(Long Id) {
    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(Id);
    if (optionalShopEntity.isPresent()) {
      //조회할 게시물이 있으면
      ShopEntity shopEntity = optionalShopEntity.get();
      ShopDto shopDto = ShopDto.toselectShopDto(shopEntity);
      return shopDto;
    }
    throw new IllegalArgumentException("아이다가 fail");
  }

  @Override
  public Page<ShopDto> shopSearchPagingList(Pageable pageable, String subject, String search) {

    Page<ShopEntity> shopEntityList = null;
    if(subject==null || search==null){
      shopEntityList = shopRepository.findAll(pageable);
    } else{
      if (subject.equals("shopTitle")) {
        shopEntityList = shopRepository.findByShopTitleContaining(pageable, search);
      } else if (subject.equals("shopContent")) {
        shopEntityList = shopRepository.findByShopContentContaining(pageable, search);
      }
      else {
        shopEntityList = shopRepository.findAll(pageable);
      }
    }
    // paging -> map(DTO::DTO(매서드) -> Entity-> DTO
    Page<ShopDto> shopDtoPage = shopEntityList.map(ShopDto::toselectShopDto);

    return shopDtoPage;
  }


  @Override
  public void shopUpdateOk(ShopDto shopDto) throws IOException {
    //게시물 유무 체크
    ShopEntity shopEntity = shopRepository.findById(shopDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("수정게시물없음"));
    //파일체크
    Optional<ShopFileEntity> optionalFileEntity = shopFileRepository.findByShopEntityId(shopDto.getId());
    //파일이 있으면 파일 기존 파일 삭제
    if (optionalFileEntity.isPresent()) {
      String fileNewName = optionalFileEntity.get().getShopNewFileName();
      String filePath = "C:/e1i4_file/" + fileNewName;
      File deleteFile = new File(filePath);
      if (deleteFile.exists()) {
        deleteFile.delete();
        System.out.println("파일을 삭제하였습니다");
      } else {
        System.out.println("파일이 존재하지않습니다");
      }
      shopFileRepository.delete(optionalFileEntity.get());//파일 테이블 레코드 삭제
    }
    //수정
    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(shopDto.getId());
    MemberEntity memberEntity = MemberEntity.builder().id(shopDto.getMemberId()).build();
    shopDto.setMemberEntity(memberEntity);

    if (shopDto.getShopFile().isEmpty()) {
      //파일 없는경우
      shopEntity = ShopEntity.toUpdateShopEntity(shopDto);
      shopRepository.save(shopEntity);
    } else {
      //파일있는경우
      MultipartFile shopFile = shopDto.getShopFile();
      String fileOldName = shopFile.getOriginalFilename();
      UUID uuid = UUID.randomUUID();
      String fileNewName = uuid + "_" + fileOldName;
      String savaPath = "C:/e1i4_file/" + fileNewName;
      shopFile.transferTo(new File(savaPath));

      shopEntity = ShopEntity.toUpdateFileShopEntity(shopDto);
      shopRepository.save(shopEntity);

      ShopFileEntity bFileEntity = ShopFileEntity.builder()
          .shopEntity(shopEntity)
          .shopNewFileName(fileNewName)
          .shopOldFileName(fileOldName)
          .build();
      Long fileId = shopFileRepository.save(bFileEntity).getId();
      shopFileRepository.findById(fileId).orElseThrow(() -> {
        throw new IllegalArgumentException("파일등록 실패");
      });
    }
    //게시글 수정 확인
    shopRepository.findById(shopDto.getId()).orElseThrow(() -> {
      throw new IllegalArgumentException("게시글 수정실패");
    });
  }


  @Override
  public void shopDelete(Long id) {
    ShopEntity shopEntity= shopRepository.findById(id).orElseThrow(()->{
      throw new IllegalArgumentException("삭제할 게시물 없음");
    });
    shopRepository.delete(shopEntity);
  }

//  @Override
//  public void addCart(Long id, Long shopId, ShopDto shopDto, int priceCount) {
//    MemberEntity memberEntity=memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
//    Optional<CartEntity> cartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());
//    CartEntity cartEntity1=null;
//    if(!cartEntity.isPresent()) {
//      cartEntity1=CartEntity.builder().memberEntity(memberEntity).build();
//      cartRepository.save(cartEntity1);
//    }else{
//      cartEntity1=cartRepository.findByMemberEntityId(memberEntity.getId()).orElseThrow(IllegalArgumentException::new);
//    }
//    //shop 확인
//    ShopEntity shopEntity=shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
//
//    //shopListEntity 확인
//    List<CartShopListEntity> cartShopListEntity
//        =cartShopListRepository.findByCartEntityIdAndShopEntityId(cartEntity1.getId(),shopEntity.getId());
//    if(cartShopListEntity.isEmpty()){
//    ShopEntity shopEntity1=shopRepository.findById(shopDto.getId()).orElseThrow(IllegalArgumentException::new);
//
//    int oldCount=shopEntity1.getPriceCount();
//
//      CartShopListEntity cartShopListEntity1=CartShopListEntity.builder()
//          .count(priceCount+oldCount)
//          .cartEntity(cartEntity1)
//          .shopEntity(shopEntity)
//          .build();
//      cartShopListRepository.save(cartShopListEntity1);
//    }else{
//    ShopEntity shopEntity1=shopRepository.findById(shopDto.getId()).orElseThrow(IllegalArgumentException::new);
//    int oldCount=shopEntity1.getPriceCount();
//      cartShopListRepository.save(CartShopListEntity.builder()
//          .id(cartShopListEntity.get(0).getId())
//          .count(cartShopListEntity.get(0).getCount()+ priceCount+oldCount)
//          .cartEntity(cartEntity1)
//          .shopEntity(shopEntity)
//          .build());
//
//    }
//  }


  @Override
  public void addCart(Long id, Long shopId, ShopDto shopDto, int priceCount) {
    MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    Optional<CartEntity> cartEntityOpt = cartRepository.findByMemberEntityId(memberEntity.getId());
    CartEntity cartEntity;

    if (!cartEntityOpt.isPresent()) {
      cartEntity = CartEntity.builder().memberEntity(memberEntity).build();
      cartRepository.save(cartEntity);
    } else {
      cartEntity = cartEntityOpt.get();
    }

    // shop 확인
    ShopEntity shopEntity = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);

    // shopListEntity 확인
    List<CartShopListEntity> cartShopListEntity = cartShopListRepository.findByCartEntityIdAndShopEntityId(cartEntity.getId(), shopEntity.getId());
    ShopEntity shopEntity1 = shopRepository.findById(shopDto.getId()).orElseThrow(IllegalArgumentException::new);
    int oldCount = shopEntity1.getPriceCount();
    int totalCount = priceCount + oldCount;

    if (cartShopListEntity.isEmpty()) {
      CartShopListEntity cartShopListEntity1 = CartShopListEntity.builder()
          .count(totalCount)
          .cartEntity(cartEntity)
          .shopEntity(shopEntity)
          .build();
      cartShopListRepository.save(cartShopListEntity1);
    } else {
      cartShopListRepository.save(CartShopListEntity.builder()
          .id(cartShopListEntity.get(0).getId())
          .count(cartShopListEntity.get(0).getCount() + totalCount)
          .cartEntity(cartEntity)
          .shopEntity(shopEntity)
          .build());
    }
  }

@Override
public Page<ShopDto> shopList(Pageable pageable, String subject1, String subject2, String search) {
  Page<ShopEntity> shopEntityPage=null;

  if(subject1!=null && subject2!=null && search!=null) {
    if ("1".equals(subject1)) {

      if ("shopTitle".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(1, search, pageable);
      } else if ("shopContent".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopContentContains(1, search, pageable);
      }
    } else if ("2".equals(subject1)) {

      if ("shopTitle".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(2, search, pageable);
      } else if ("shopContent".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopContentContains(2, search, pageable);
      }
    } else if ("3".equals(subject1)) {

      if ("shopTitle".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(3, search, pageable);
      } else if ("shopContent".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopContentContains(3, search, pageable);
      }
    } else if ("4".equals(subject1)) {

      if ("shopTitle".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(4, search, pageable);
      } else if ("shopContent".equals(subject2)) {
        shopEntityPage = shopRepository.findByCategoryAndShopContentContains(4, search, pageable);
      }
    } else {
      shopEntityPage = shopRepository.findAll(pageable);
      System.out.println("else");
    }
  }else {
    shopEntityPage = shopRepository.findAll(pageable);
  }

  Page<ShopDto> shopDtos = shopEntityPage.map(ShopDto::toselectShopDto);

  return shopDtos;
}

  @Override
  public Page<ShopDto> shopSearchPageList1(Pageable pageable, String subject, String search) {
    ShopEntity shopEntity=new ShopEntity();
    Page<ShopEntity> shopEntityPage = null;


    if(subject==null || search==null){
      shopEntityPage = shopRepository.findByCategory1Contains(pageable);
    }else {
      if (subject.equals("shopTitle")){
        shopEntityPage=shopRepository.findByShopTitle1Contains(pageable,search);
      } else if (subject.equals("shopContent")) {
        shopEntityPage=shopRepository.findByShopContent1Contains(pageable,search);
      }else {
        shopEntityPage= shopRepository.findByCategory1Contains(pageable);
      }
    }

    Page<ShopDto> shopDtoPage = shopEntityPage.map(ShopDto::toselectShopDto);

    return shopDtoPage;
  }
  @Override
  public Page<ShopDto> shopSearchPageList2(Pageable pageable, String subject, String search) {
    ShopEntity shopEntity=new ShopEntity();
    Page<ShopEntity> shopEntityPage = null;


    if(subject==null || search==null){
      shopEntityPage = shopRepository.findByCategory2Contains(pageable);
    }else {
      if (subject.equals("shopTitle")){
        shopEntityPage=shopRepository.findByShopTitle2Contains(pageable,search);
      } else if (subject.equals("shopContent")) {
        shopEntityPage=shopRepository.findByShopContent2Contains(pageable,search);
      }else {
        shopEntityPage= shopRepository.findByCategory2Contains(pageable);
      }
    }

    Page<ShopDto> shopDtoPage = shopEntityPage.map(ShopDto::toselectShopDto);

    return shopDtoPage;
  }
  @Override
  public Page<ShopDto> shopSearchPageList3(Pageable pageable, String subject, String search) {
    ShopEntity shopEntity=new ShopEntity();
    Page<ShopEntity> shopEntityPage = null;


    if(subject==null || search==null){
      shopEntityPage = shopRepository.findByCategory3Contains(pageable);
    }else {
      if (subject.equals("shopTitle")){
        shopEntityPage=shopRepository.findByShopTitle3Contains(pageable,search);
      } else if (subject.equals("shopContent")) {
        shopEntityPage=shopRepository.findByShopContent3Contains(pageable,search);
      }else {
        shopEntityPage= shopRepository.findByCategory3Contains(pageable);
      }
    }

    Page<ShopDto> shopDtoPage = shopEntityPage.map(ShopDto::toselectShopDto);

    return shopDtoPage;
  }
  @Override
  public Page<ShopDto> shopSearchPageList4(Pageable pageable, String subject, String search) {
    ShopEntity shopEntity=new ShopEntity();
    Page<ShopEntity> shopEntityPage = null;


    if(subject==null || search==null){
      shopEntityPage = shopRepository.findByCategory4Contains(pageable);
    }else {
      if (subject.equals("shopTitle")){
        shopEntityPage=shopRepository.findByShopTitle4Contains(pageable,search);
      } else if (subject.equals("shopContent")) {
        shopEntityPage=shopRepository.findByShopContent4Contains(pageable,search);
      }else {
        shopEntityPage= shopRepository.findByCategory4Contains(pageable);
      }
    }

    Page<ShopDto> shopDtoPage = shopEntityPage.map(ShopDto::toselectShopDto);

    return shopDtoPage;
  }
  


  @Transactional
  public String toggleLikeShop(Long shopId, Long memberId) {
    MemberEntity memberEntity=memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    ShopEntity shopEntity = shopRepository.findById(shopId)
        .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));
    Optional<ShopLikeEntity> shopLikeEntity =shopLikeRepository.findByShopEntityAndMemberEntity(shopEntity, memberEntity);
    ShopLikeEntity shopLikeEntity1=null;
    if (shopLikeEntity.isEmpty()) {
      // 좋아요가 없는 경우 새로운 좋아요 엔터티를 생성하여 저장합니다.
      shopEntity.increaseLikeCount();
      shopLikeEntity1 = ShopLikeEntity.builder()
          .shopEntity(shopEntity)
          .memberEntity(memberEntity)
          .liked(true)
          .build();
      shopLikeRepository.save(shopLikeEntity1);
      return "좋아요 성공.";
    } else {
      // 이미 좋아요가 있는 경우 좋아요를 취소합니다.
      shopEntity.decreaseLikeCount();
      shopLikeRepository.deleteById(shopLikeEntity.get().getId());
      return "좋아요 취소했습니다.";
    }
  }
  public String checkLikeStatus(Long shopId, Long memberId) {
    MemberEntity memberEntity = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    ShopEntity shopEntity = shopRepository.findById(shopId)
        .orElseThrow(() -> new IllegalArgumentException("상점을 찾을 수 없습니다."));

    if (hasLikedShop(shopEntity, memberEntity)) {
      return "liked";
    } else {
      return "unliked";
    }
  }
  private boolean hasLikedShop(ShopEntity shopEntity, MemberEntity memberEntity) {
    return shopLikeRepository.findByShopEntityAndMemberEntity(shopEntity, memberEntity).isPresent();
  }

  private void createLikeShop(ShopEntity shopEntity, MemberEntity memberEntity) {
    shopLikeRepository.save(new ShopLikeEntity(shopEntity, memberEntity));
  }

  private void removeLikeShop(ShopEntity shopEntity, MemberEntity memberEntity) {
    ShopLikeEntity shopLikeEntity = shopLikeRepository.findByShopEntityAndMemberEntity(shopEntity, memberEntity)
        .orElseThrow(() -> new RuntimeException("Shop like not found for shop and member."));
    shopLikeRepository.delete(shopLikeEntity);
  }


  @Override
  public List<ShopDto> liked() {

    List<ShopEntity> like = shopRepository.findTop5ByOrderByLikedDesc();

    List<ShopDto> likeDto = like.stream().map(
        ShopDto::toselectShopDto).collect(Collectors.toList());


    return likeDto;
  }

}


