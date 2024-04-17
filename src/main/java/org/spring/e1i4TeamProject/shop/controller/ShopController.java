package org.spring.e1i4TeamProject.shop.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.dto.ShopReplyDto;
import org.spring.e1i4TeamProject.shop.service.ShopReplyService;
import org.spring.e1i4TeamProject.shop.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final ShopService shopService;
  private final ShopReplyService shopReplyService;

  @GetMapping({"/","/index"})
  public String index() {
    return "shop/index";
  }

  @GetMapping("/shopWrite")
  public String shopWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                          ShopDto shopDto, Model model){
    model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());

    return "shop/shopWrite";
  }

  @PostMapping("/shopWrite")
  public String shopWriteOk(ShopDto shopDto) throws IOException{
    shopService.insertShop(shopDto);

    return "redirect:/shop/shopList";
  }

  @GetMapping("/shopDetail/{id}")
  public String shopDetail(@PathVariable("id") Long id,
                       @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                       Model model) {

    shopService.updateShopHit(id);
    ShopDto shopDto = shopService.detail1(id);

    List<ShopReplyDto> shopReplyDtoList = shopReplyService.shopReplyList(shopDto.getId());

    model.addAttribute("shopDto", shopDto);
    model.addAttribute("shopReplyDtoList", shopReplyDtoList);
    model.addAttribute("myUserDetails", myUserDetails);

    return "shop/shopDetail";
  }

  @GetMapping("/shopList")
  public String shopList(@PageableDefault(page = 0,size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                          @RequestParam(name = "subject",required = false)String subject,
                          @RequestParam(name = "search",required = false)String search,
                          @AuthenticationPrincipal MyUserDetailsImpl myUserDetails, Model model){
    Page<ShopDto> shopList = shopService.shopSearchPagingList(pageable, subject, search);

    model.addAttribute("myUserDetails", myUserDetails);

    int newPage=shopList.getNumber();
    int totalPages=shopList.getTotalPages();
    int blockNum=5;

    int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
        ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages);

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

    model.addAttribute("shopList", shopList);
    model.addAttribute("startPage",startPage);
    model.addAttribute("endPage",endPage);

    return "shop/shopList";
  }

  @PostMapping("/shopUpdate")
  public String shopUpdate(ShopDto shopDto) throws IOException{
    shopService.shopUpdateOk(shopDto);
    return "redirect:/shop/shopDetail/"+shopDto.getId();
  }
  @GetMapping("/shopDelete/{id}")
  public String shopDelete(@PathVariable("id") Long id){
    shopService.shopDelete(id);
    return "redirect:/shop/shopList";
  }




}
