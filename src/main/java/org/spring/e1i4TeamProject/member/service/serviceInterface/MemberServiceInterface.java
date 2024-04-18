package org.spring.e1i4TeamProject.member.service.serviceInterface;

import org.spring.e1i4TeamProject.member.dto.MemberDto;

public interface MemberServiceInterface {
    void memberJoin(MemberDto memberDto);

    MemberDto memberDetail(Long id);
}
