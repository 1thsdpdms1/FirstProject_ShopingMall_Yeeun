package org.spring.e1i4TeamProject.seller.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.seller.service.SellerService;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.service.ShopService;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final ShopService shopService;

    @GetMapping("/sellerList")
    public String sellerList(Model model){
        List<MemberDto> seller=sellerService.sellerRole();
        model.addAttribute("seller", seller);
        return "seller/sellerList";
    }

    @GetMapping("/sellerDetail/{id}")
    public String sellerDetail(@PathVariable("id")Long id,
                               @AuthenticationPrincipal MyUserDetailsImpl myUserDetails, Model model){
        MemberDto seller=sellerService.sellerDetail(id);
        List<ShopDto> shop=sellerService.shopList(id);

        if (myUserDetails != null) {

            model.addAttribute("myUserDetails", myUserDetails);
        }
        model.addAttribute("seller",seller);
        model.addAttribute("shop",shop);
        return "seller/sellerDetail";
    }

    @GetMapping("/sellerListDelete/{id}")
    public String sellerDelete(@PathVariable("id")Long id){
        sellerService.sellerListDelete(id);
        return "redirect:/seller/sellerList";
    }


}
