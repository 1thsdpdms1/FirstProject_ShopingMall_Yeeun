package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.repository.CartRepository;
import org.spring.e1i4TeamProject.shop.repository.CartShopListRepository;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.CartServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService implements CartServiceImpl {

  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;

  @Override
  public Long cartShopAllDelete(Long cartId) {
    // 카트 ID를 기반으로 해당 카트를 삭제하고, 회원 ID를 반환합니다.
    CartEntity cartEntity = cartRepository.findById(cartId)
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    Long memberId = cartEntity.getMemberEntity().getId();
    cartRepository.deleteById(cartId);
    return memberId;
  }


  public Long getCartIdByUserId(Long userId) {
    // 사용자 ID를 기반으로 카트를 조회하여 카트 ID를 반환합니다.
    CartEntity cartEntity = cartRepository.findByMemberEntityId(userId).orElse(null);
    if (cartEntity != null) {
      return cartEntity.getId();
    } else {
      return null;
    }
  }


}
