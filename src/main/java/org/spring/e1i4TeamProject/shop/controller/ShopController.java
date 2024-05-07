package org.spring.e1i4TeamProject.shop.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.dto.ShopReplyDto;
import org.spring.e1i4TeamProject.shop.repository.CartRepository;
import org.spring.e1i4TeamProject.shop.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final ShopService shopService;
  private final ShopReplyService shopReplyService;
  private final CartService cartService;
  private final CartShopService cartShopService;
  private final ShopLikeService shopLikeService;
  private final MemberService memberService;
  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;

  @GetMapping({"/", "/index"})
  public String index() {
    return "shop/index";
  }

  @GetMapping("/shopWrite")
  public String shopWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                          ShopDto shopDto, Model model) {
    model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
    model.addAttribute("myUserDetails", myUserDetails);
    model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());
    return "shop/shopWrite";
  }

  @PostMapping("/shopWrite")
  public String shopWriteOk(ShopDto shopDto) throws IOException {
    shopService.insertShop(shopDto);

    return "redirect:/shop/shopList";
  }

  @GetMapping("/shopDetail/{Id}")
  public String shopDetail(@PathVariable("Id") Long Id,
                           @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                           Model model) {
    boolean shopLike = false;

    shopService.updateShopHit(Id);
    ShopDto shopDto = shopService.detail(Id);

    // 상점 댓글 목록 조회
    List<ShopReplyDto> shopReplyList = shopReplyService.shopReplyList(shopDto.getId());

    model.addAttribute("shopLike", shopLike);
    model.addAttribute("shopDto", shopDto);
    model.addAttribute("shopReplyList", shopReplyList);
    model.addAttribute("myUserDetails", myUserDetails);

    return "shop/shopDetail";
  }


  @GetMapping("/shopList")
  public String shopList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         Model model,
                         @RequestParam(name = "subject1", required = false) String subject1,
                         @RequestParam(name = "subject2", required = false) String subject2,
                         @RequestParam(name = "search", required = false) String search,
                         @AuthenticationPrincipal MyUserDetailsImpl myUserDetails ) {


    Page<ShopDto> shopList = shopService.shopList(pageable, subject1, subject2, search);


    int totalPage = shopList.getTotalPages();//전체page
    int newPage = shopList.getNumber();//현재page
    Long totalElements = shopList.getTotalElements();//전체 레코드 갯수
    int size = shopList.getSize();//페이지당 보이는 갯수

    int blockNum = 3; //브라우저에 보이는 페이지 갯수

    int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
        ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
    int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("shopList", shopList);
    model.addAttribute("myUserDetails", myUserDetails);

    return "shop/shopList";
  }

  @GetMapping("/shopList1")
  public String ShopList1 (@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
                           @PageableDefault(page = 0, size = 8, sort = "shop_id", direction = Sort.Direction.DESC)
                           Pageable pageable, Model model){

    Page<ShopDto> shopDtoList = shopService.shopSearchPageList1(pageable, subject, search);

    model.addAttribute("myUserDetails", myUserDetails);

    //paging
    int totalPages = shopDtoList.getTotalPages(); // 전체 페이지
    int newPage = shopDtoList.getNumber(); // 현재 페이지
    int blockNum = 8;// 브라우저에 보이는 페이지번호

    int startPage = (int) (
        (Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

    model.addAttribute("startPage", startPage);
    model.addAttribute("newPage", newPage);
    model.addAttribute("endPage", endPage);

    model.addAttribute("shopDtoList1", shopDtoList);

    return "shop/shopList1";
  }//1만 보이기
  @GetMapping("/shopList2")
  public String ShopList2 (@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
                           @PageableDefault(page = 0, size = 8, sort = "shop_id", direction = Sort.Direction.DESC)
                           Pageable pageable, Model model){

    Page<ShopDto> shopDtoList = shopService.shopSearchPageList2(pageable, subject, search);

    model.addAttribute("myUserDetails", myUserDetails);

    //paging
    int totalPages = shopDtoList.getTotalPages(); // 전체 페이지
    int newPage = shopDtoList.getNumber(); // 현재 페이지
    int blockNum = 8;// 브라우저에 보이는 페이지번호

    int startPage = (int) (
        (Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

    model.addAttribute("startPage", startPage);
    model.addAttribute("newPage", newPage);
    model.addAttribute("endPage", endPage);

    model.addAttribute("shopDtoList2", shopDtoList);

    return "shop/shopList2";
  }//2만 보이기
  @GetMapping("/shopList3")
  public String ShopList3 (@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                                  @RequestParam(name = "subject", required = false) String subject,
                                  @RequestParam(name = "search", required = false) String search,
                                  @PageableDefault(page = 0, size = 8, sort = "shop_id", direction = Sort.Direction.DESC)
                                  Pageable pageable, Model model){

    Page<ShopDto> shopDtoList = shopService.shopSearchPageList3(pageable, subject, search);

    model.addAttribute("myUserDetails", myUserDetails);

    //paging
    int totalPages = shopDtoList.getTotalPages(); // 전체 페이지
    int newPage = shopDtoList.getNumber(); // 현재 페이지
    int blockNum = 8;// 브라우저에 보이는 페이지번호

    int startPage = (int) (
        (Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

    model.addAttribute("startPage", startPage);
    model.addAttribute("newPage", newPage);
    model.addAttribute("endPage", endPage);

    model.addAttribute("shopDtoList3", shopDtoList);

    return "shop/shopList3";
  }//3만 보이기
  @GetMapping("/shopList4")
  public String shopList4 (@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                                  @RequestParam(name = "subject", required = false) String subject,
                                  @RequestParam(name = "search", required = false) String search,
                                  @PageableDefault(page = 0, size = 8, sort = "shop_id", direction = Sort.Direction.DESC)
                                  Pageable pageable, Model model){
    
    Page<ShopDto> shopDtoList = shopService.shopSearchPageList4(pageable, subject, search);

    model.addAttribute("myUserDetails", myUserDetails);

    //paging
    int totalPages = shopDtoList.getTotalPages(); // 전체 페이지
    int newPage = shopDtoList.getNumber(); // 현재 페이지
    int blockNum = 8;// 브라우저에 보이는 페이지번호

    int startPage = (int) (
        (Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

    model.addAttribute("startPage", startPage);
    model.addAttribute("newPage", newPage);
    model.addAttribute("endPage", endPage);

    model.addAttribute("shopDtoList4", shopDtoList);
    
    return "shop/shopList4";
  }//4만 보이기


  @PostMapping("/shopUpdate")
  public String shopUpdate(ShopDto shopDto) throws IOException {
    shopService.shopUpdateOk(shopDto);
    return "redirect:/shop/shopDetail/" + shopDto.getId();
  }

  @GetMapping("/shopUpdate/{Id}")
  public String shopUpdate(@PathVariable("Id") Long Id,
                           @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                           Model model) {
    boolean shopLike = false;

    ShopDto shopDto = shopService.detail(Id);

    // 상점 댓글 목록 조회

    model.addAttribute("shopLike", shopLike);
    model.addAttribute("shopDto", shopDto);
    model.addAttribute("myUserDetails", myUserDetails);

    return "shop/shopUpdate";
  }



  @GetMapping("/shopDelete/{id}")
  public String shopDelete(@PathVariable("id") Long id) {
    shopService.shopDelete(id);
    return "redirect:/shop/shopList";
  }


  @GetMapping("/cart/{id}/{priceCount}")
  public String cart(@PathVariable("id") Long id,@PathVariable("priceCount")int priceCount,ShopDto shopDto,
                     @AuthenticationPrincipal MyUserDetailsImpl myUserDetails) {

    shopService.addCart(myUserDetails.getMemberEntity().getId(), id,shopDto,priceCount);

    return "redirect:/shop/cartList";
  }

  @GetMapping("/cartList")
  public String cartList(Model model,
                         @AuthenticationPrincipal MyUserDetailsImpl myUserDetails) {
    List<CartShopListDto> cartShopListDto = cartShopService.cartList(myUserDetails.getMemberEntity().getId());
    model.addAttribute("cartShopListDto", cartShopListDto);
    model.addAttribute("myUserDetails", myUserDetails);

    return "shop/cartList";
  }


  @GetMapping("/cartShopDelete/{id}")
  public String cartShopDelete(@PathVariable("id") Long id) {
    cartShopService.cartShopDelete(id);

    return "redirect:/shop/cartList";
  }

  @PostMapping("/cartListDelete")
  public String deleteSelectedCart(@RequestParam("cartIds") List<Long> ids) {
    cartShopService.deleteCart(ids);
    return "redirect:/shop/cartList";
  }

  @GetMapping("/cartShopAllDelete")
  public String cartShopAllDelete(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails, Model model) {
    // 로그인한 사용자의 ID를 가져옵니다.
    Long userId = myUserDetails.getMemberEntity().getId();

    // 카트 ID를 가져옵니다.
    Long cartId = cartService.getCartIdByUserId(userId);

    // 카트가 존재하는 경우에만 삭제하고 회원 ID를 반환합니다.
    if (cartId != null) {
      Long memberId = cartService.cartShopAllDelete(cartId);
      return "redirect:/member/memberDetail/" + memberId; // 회원 상세 페이지로 리다이렉트합니다.
    } else {
      // 카트가 없는 경우 처리
      return "redirect:/error"; // 오류 페이지로 리다이렉트합니다.
    }
  }
}
