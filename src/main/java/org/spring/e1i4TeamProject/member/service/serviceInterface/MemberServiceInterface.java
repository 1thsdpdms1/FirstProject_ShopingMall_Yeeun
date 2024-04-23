package org.spring.e1i4TeamProject.member.service.serviceInterface;

import org.spring.e1i4TeamProject.member.dto.MemberDto;

import java.io.IOException;

public interface MemberServiceInterface {
    void memberJoin(MemberDto memberDto) throws IOException;

    MemberDto memberDetail(Long id);

    void memberUpdate(MemberDto memberDto) throws IOException;

    void sellerJoin(MemberDto memberDto) throws IOException;

    void memberDelete(Long id);
}
