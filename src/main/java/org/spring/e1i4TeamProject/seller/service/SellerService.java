package org.spring.e1i4TeamProject.seller.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.seller.repository.SellerRepository;
import org.spring.e1i4TeamProject.seller.service.sellerServiceInterface.SellerServiceInterface;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService implements SellerServiceInterface {

private final SellerRepository sellerRepository;
private final ShopRepository shopRepository;


    public List<MemberDto> sellerRole() {
        List<MemberEntity> memberDtoList=new ArrayList<>();
        Role role = Role.MANAGER;
        memberDtoList=sellerRepository.findByRole(role);
        return memberDtoList.stream().map(MemberDto::toMemberDto).collect(Collectors.toList());

    }

    @Override
    public MemberDto sellerDetail(Long id) {
        MemberEntity memberEntity=sellerRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException("해당 아이디가 없습니다");
        });
        MemberDto memberDto=MemberDto.toMemberDto(memberEntity);
        return memberDto;
    }

    @Override
    public List<ShopDto> shopList(Long id) {
        MemberEntity memberEntity = sellerRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 판매자를 찾을 수 없습니다. ID: " + id));

        List<ShopEntity> shopEntityList = shopRepository.findByMemberEntityId(memberEntity.getId());

        List<ShopDto> shopDtos = shopEntityList.stream().map(ShopDto::toselectShopDto)
            .collect(Collectors.toList());

        return shopDtos;
    }



    @Override
    public int sellerListDelete(Long id) {
        sellerRepository.deleteById(id);
        return 0;
    }
}
