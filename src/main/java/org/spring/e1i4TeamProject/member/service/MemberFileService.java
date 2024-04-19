package org.spring.e1i4TeamProject.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.service.serviceInterface.MemberFileServiceInterface;
import org.spring.e1i4TeamProject.member.service.serviceInterface.MemberServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberFileService implements MemberFileServiceInterface {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


}
