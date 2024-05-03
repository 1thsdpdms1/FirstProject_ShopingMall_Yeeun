package org.spring.e1i4TeamProject.seller.service.sellerServiceInterface;

import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;

import java.util.List;

public interface SellerServiceInterface {
    MemberDto sellerDetail(Long id);

    List<MemberDto> sellerRole();

    List<ShopDto> shopList(Long id);

    int sellerListDelete(Long id);
}
