package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.spring.e1i4TeamProject.shop.repository.CartRepository;
import org.spring.e1i4TeamProject.shop.repository.CartShopListRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopFileRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.CartShopServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartShopService implements CartShopServiceImpl {

    private final ShopRepository shopRepository;
    private final ShopFileRepository shopFileRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartShopListRepository cartShopListRepository;

    @Override
    public List<CartShopListDto> cartList(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        Optional<CartEntity> optionalCartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());

        if (!optionalCartEntity.isPresent()) {
            throw new IllegalArgumentException("장바구니 내역이 없습니다");
        }

        List<CartShopListEntity> cartShopListEntity = cartShopListRepository.findAllByCartEntityId(optionalCartEntity.get().getId());
        List<CartShopListDto> cartShopListDtos = new ArrayList<>();
        cartShopListDtos = cartShopListEntity.stream().map(shop -> CartShopListDto.builder()
            .id(shop.getId())
            .count(shop.getCount())
            .cartEntity(shop.getCartEntity())
            .shopEntity(shop.getShopEntity())
            .build()
        ).collect(Collectors.toList());

        return cartShopListDtos;
    }

    @Override
    public List<CartShopListDto> memberCartList(Long id) {

        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> {
            throw new NullPointerException("존재하지 않는 아이디입니다");
        });

        CartEntity cartEntity = cartRepository.findByMemberEntity(memberEntity)
            .orElseThrow(() -> {
                throw new IllegalArgumentException("예외");
            });

//    List<CartShopListEntity> cartShopListEntity
//        = cartShopListRepository.findByCartEntity(CartEntity.builder().id(1L).build());

        List<CartShopListEntity> cartShopListEntityList
            = cartShopListRepository.findByCartEntity(CartEntity.builder().id(cartEntity.getId()).build());

        return cartShopListEntityList.stream().map((shop) -> CartShopListDto.builder()
                .id(shop.getId())
                .count(shop.getCount())
                .cartEntity(shop.getCartEntity())
                .shopEntity(shop.getShopEntity())
                .build())
            .collect(Collectors.toList());
    }
}
