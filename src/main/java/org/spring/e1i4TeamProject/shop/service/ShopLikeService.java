package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.service.serviceInterface.MemberServiceInterface;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.repository.ShopLikeRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.ShopLikeServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopLikeService implements ShopLikeServiceImpl {
  private final MemberRepository memberRepository;
  private final MemberServiceInterface memberServiceInterface;
  private final ShopLikeRepository shopLikeRepository;
  private final ShopRepository shopRepository;




}
