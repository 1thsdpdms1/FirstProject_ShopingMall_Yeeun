package org.spring.e1i4TeamProject.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.service.serviceInterface.MemberServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void memberJoin(MemberDto memberDto) {

        memberRepository.findByUserEmail(memberDto.getUserEmail()).ifPresent(email->{
            throw new RuntimeException(memberDto.getUserEmail()+" 이메일이 이미 존재합니다!");
        });

        MemberEntity memberEntity = MemberEntity.toMemberJoinEntity(memberDto, passwordEncoder);

        memberRepository.save(memberEntity);
    }
}
