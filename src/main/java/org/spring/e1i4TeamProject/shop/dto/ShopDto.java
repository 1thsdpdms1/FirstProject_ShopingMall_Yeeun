package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopFileEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopReplyEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShopDto {
    private Long id;

    private String shopTitle;

    private String shopContent;

    private int shopPrice;

    private int count;

    private int category;

    private int shopHit;

    private int shopAttachFile;

    private Long memberId;

    private MultipartFile shopFile;

    private String shopSeller;

    private List<CartShopListEntity> cartShopListEntityList;

    private List<ShopFileEntity> shopFileEntityList;

    private List<ShopReplyEntity> shopReplyEntityList;

    private MemberEntity memberEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String shopNewFileName;

    private String shopOldFileName;


    public static ShopDto toselectShopDto(ShopEntity shopEntity) {
        ShopDto shopDto=new ShopDto();

        shopDto.setId(shopEntity.getId());
        shopDto.setShopTitle(shopEntity.getShopTitle());
        shopDto.setShopContent(shopEntity.getShopContent());
        shopDto.setShopPrice(shopEntity.getShopPrice());
        shopDto.setCount(shopEntity.getCount());
        shopDto.setCategory(shopEntity.getCategory());
        shopDto.setShopSeller(shopEntity.getShopSeller());
        shopDto.setShopHit(shopEntity.getShopHit());
        shopDto.setCreateTime(shopEntity.getCreateTime());
        shopDto.setUpdateTime(shopEntity.getUpdateTime());
        shopDto.setMemberEntity(shopEntity.getMemberEntity());
        if(shopEntity.getShopAttachFile()==0) {
            //파일0
            shopDto.setShopAttachFile(shopEntity.getShopAttachFile());
        }else{
            shopDto.setShopAttachFile(shopEntity.getShopAttachFile());
            //새파일
            shopDto.setShopNewFileName(shopEntity.getShopFileEntityList().get(0).getShopNewFileName());
            //원본파일
            shopDto.setShopOldFileName(shopEntity.getShopFileEntityList().get(0).getShopOldFileName());
        }
        return shopDto;
    }
}
