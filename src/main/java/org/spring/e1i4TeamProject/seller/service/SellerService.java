package org.spring.e1i4TeamProject.seller.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.seller.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

private final SellerRepository sellerRepository;


    public List<MemberDto> sellerRole() {
        List<MemberEntity> memberDtoList=new ArrayList<>();
        Role role = Role.MANAGER;
        memberDtoList=sellerRepository.findByRole(role);
        return memberDtoList.stream().map(MemberDto::toMemberDto).collect(Collectors.toList());

    }
}
