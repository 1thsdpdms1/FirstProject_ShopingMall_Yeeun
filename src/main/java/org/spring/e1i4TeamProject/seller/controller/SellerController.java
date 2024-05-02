package org.spring.e1i4TeamProject.seller.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.seller.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/sellerList")
    public String sellerList(Model model){
        List<MemberDto> seller=sellerService.sellerRole();
        model.addAttribute("seller", seller);
        return "seller/sellerList";
    }

}
