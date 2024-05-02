package org.spring.e1i4TeamProject.admin.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.admin.repository.AdminRepository;
import org.spring.e1i4TeamProject.admin.service.adminServiceInterface.AdminServiceInterface;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface {

    private final AdminRepository adminRepository;
    private final ShopRepository shopRepository;

    @Override
    public Page<MemberDto> memberList(Pageable pageable, String subject, String search) {

        Page<MemberEntity> memberEntityPage = null;
        if (subject != null) {
            if (subject.equals("name")) {
                memberEntityPage = adminRepository.findByRoleAndNameContains(pageable, Role.MEMBER, search);
            } else if (subject.equals("phoneNumber")) {
                memberEntityPage = adminRepository.findByRoleAndPhoneNumberContains(pageable, Role.MEMBER, search);
            } else if (subject.equals("userEmail")) {
                memberEntityPage = adminRepository.findByRoleAndUserEmailContains(pageable, Role.MEMBER, search);
            } else {
                memberEntityPage = adminRepository.findByRole(pageable, Role.MEMBER);
            }
        } else {
            memberEntityPage = adminRepository.findByRole(pageable, Role.MEMBER);
        }
        Page<MemberDto> memberDtos = memberEntityPage.map(MemberDto::toMemberDto);


        return memberDtos;

    }


    @Override
    public int memberListDelete(Long id) {
        adminRepository.deleteById(id);
        return 0;
    }

    @Override
    public int sellerListDelete(Long id) {
        adminRepository.deleteById(id);
        return 0;
    }
    @Override
    public Page<ShopDto> shopList(Pageable pageable, String subject1, String subject2, String search) {
        Page<ShopEntity> shopEntityPage=null;

        if(subject1!=null && subject2!=null && search!=null) {
            if ("1".equals(subject1)) {

                if ("shopTitle".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(1, search, pageable);
                } else if ("shopContent".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopContentContains(1, search, pageable);
                }
            } else if ("2".equals(subject1)) {

                if ("shopTitle".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(2, search, pageable);
                } else if ("shopContent".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopContentContains(2, search, pageable);
                }
            } else if ("3".equals(subject1)) {

                if ("shopTitle".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(3, search, pageable);
                } else if ("shopContent".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopContentContains(3, search, pageable);
                }
            } else if ("4".equals(subject1)) {

                if ("shopTitle".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopTitleContains(4, search, pageable);
                } else if ("shopContent".equals(subject2)) {
                    shopEntityPage = shopRepository.findByCategoryAndShopContentContains(4, search, pageable);
                }
            } else {
                shopEntityPage = shopRepository.findAll(pageable);
                System.out.println("else");
            }
        }else {
            shopEntityPage = shopRepository.findAll(pageable);
        }

        Page<ShopDto> shopDtos = shopEntityPage.map(ShopDto::toselectShopDto);

        return shopDtos;
    }


    @Override
    public Page<MemberDto> sellerList(Pageable pageable, String subject, String search) {

        Page<MemberEntity> memberEntityPage = null;

        if (subject != null) {
            if (subject.equals("name")) {
                memberEntityPage = adminRepository.findByRoleAndNameContains(pageable, Role.MANAGER, search);
            } else if (subject.equals("phoneNumber")) {
                memberEntityPage = adminRepository.findByRoleAndPhoneNumberContains(pageable, Role.MANAGER, search);
            } else if (subject.equals("userEmail")) {
                memberEntityPage = adminRepository.findByRoleAndUserEmailContains(pageable, Role.MANAGER, search);
            } else {
                memberEntityPage = adminRepository.findByRole(pageable, Role.MANAGER);
            }
        } else {
            memberEntityPage = adminRepository.findByRole(pageable, Role.MANAGER);
        }
        Page<MemberDto> memberDtos = memberEntityPage.map(MemberDto::toMemberDto);

        return memberDtos;

    }



    //체크삭제
    @Transactional
    @Override
    public void deleteMembers(List<Long> ids) {
        adminRepository.deleteByIdIn(ids);
    }

    @Transactional
    @Override
    public void deleteSeller(List<Long> ids) {
        adminRepository.deleteByIdIn(ids);
    }
}




