package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.dto.ShopFileDto;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopFileEntity;
import org.spring.e1i4TeamProject.shop.repository.CartRepository;
import org.spring.e1i4TeamProject.shop.repository.CartShopListRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopFileRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.ShopServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopService implements ShopServiceImpl {
  private final ShopRepository shopRepository;
  private final ShopFileRepository shopFileRepository;
  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final CartShopListRepository cartShopListRepository;

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

  @Override
  public void addCart(Long id, Long shopId) {
    MemberEntity memberEntity=memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    Optional<CartEntity> cartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());
    CartEntity cartEntity1=null;

    if(!cartEntity.isPresent()) {
      cartEntity1=CartEntity.builder().memberEntity(memberEntity).build();
      cartRepository.save(cartEntity1);
    }else{
      cartEntity1=cartRepository.findByMemberEntityId(memberEntity.getId()).orElseThrow(IllegalArgumentException::new);
    }
    //shop 확인
    ShopEntity shopEntity=shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
    //shopListEntity 확인
    List<CartShopListEntity> cartShopListEntity
        =cartShopListRepository.findByCartEntityIdAndShopEntityId(cartEntity1.getId(),shopEntity.getId());
    if(cartShopListEntity.isEmpty()){
      CartShopListEntity cartShopListEntity1=CartShopListEntity.builder()
          .count(1)
          .cartEntity(cartEntity1)
          .shopEntity(shopEntity)
          .build();
      cartShopListRepository.save(cartShopListEntity1);
    }else{
      cartShopListRepository.save(CartShopListEntity.builder()
          .id(cartShopListEntity.get(0).getId())
          .count(cartShopListEntity.get(0).getCount()+1)
          .cartEntity(cartEntity1)
          .shopEntity(shopEntity)
          .build());

    }
  }
  @Override
  public List<ShopDto> shopList1() {
    List<ShopEntity> shopEntityList=new ArrayList<>();

    shopEntityList=shopRepository.findByCategory(1);
    return shopEntityList.stream().map(ShopDto::toselectShopDto).collect(Collectors.toList());
  }
  @Override
  public List<ShopDto> shopList2() {
    List<ShopEntity> shopEntityList=new ArrayList<>();

    shopEntityList=shopRepository.findByCategory(2);
    return shopEntityList.stream().map(ShopDto::toselectShopDto).collect(Collectors.toList());
  }
  @Override
  public List<ShopDto> shopList3() {
    List<ShopEntity> shopEntityList=new ArrayList<>();

    shopEntityList=shopRepository.findByCategory(3);
    return shopEntityList.stream().map(ShopDto::toselectShopDto).collect(Collectors.toList());
  }
  @Override
  public List<ShopDto> shopList4() {
    List<ShopEntity> shopEntityList=new ArrayList<>();

    shopEntityList=shopRepository.findByCategory(4);
    return shopEntityList.stream().map(ShopDto::toselectShopDto).collect(Collectors.toList());
  }



}
