package org.spring.e1i4TeamProject.admin.service.adminServiceInterface;

import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminServiceInterface {

    Page<MemberDto> memberList(Pageable pageable, String subject, String search);

    Page<MemberDto> sellerList(Pageable pageable, String subject, String search);


    int memberListDelete(Long id);

//    List<MemberDto> sellerRole();
//Page<MemberDto> sellerRole(Pageable pageable, String subject, String search);
}
