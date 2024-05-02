package org.spring.e1i4TeamProject.admin.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.admin.repository.AdminRepository;
import org.spring.e1i4TeamProject.admin.service.adminServiceInterface.AdminServiceInterface;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface {

    private final AdminRepository adminRepository;

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
}


