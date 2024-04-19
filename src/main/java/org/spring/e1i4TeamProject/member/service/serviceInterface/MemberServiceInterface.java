package org.spring.e1i4TeamProject.member.service.serviceInterface;

import org.spring.e1i4TeamProject.member.dto.MemberDto;

import java.io.IOException;

public interface MemberServiceInterface {
    void memberJoin(MemberDto memberDto) throws IOException;

    MemberDto memberDetail(Long id);

    String uploadMemberImg(MemberDto memberDto);

    void memberUpdate(MemberDto memberDto) throws IOException;
}
