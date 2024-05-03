package org.spring.e1i4TeamProject.shop.dto;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ShopLikeDto {

  private Long id;
  private ShopEntity shopEntity;


  private MemberEntity memberEntity;

  private boolean liked;


}
