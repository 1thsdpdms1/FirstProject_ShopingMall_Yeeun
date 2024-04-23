package org.spring.e1i4TeamProject.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.dto.MemberFileDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.entity.MemberFileEntity;
import org.spring.e1i4TeamProject.member.repository.MemberFileRepository;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.service.serviceInterface.MemberServiceInterface;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void memberJoin(MemberDto memberDto) throws IOException {

        memberRepository.findByUserEmail(memberDto.getUserEmail()).ifPresent(email -> {
            throw new RuntimeException(memberDto.getUserEmail() + " 이메일이 이미 존재합니다!");
        });

        if (memberDto.getMemberFile().isEmpty()) {
            MemberEntity memberEntity1 = MemberEntity.toMemberJoinEntity0(memberDto, passwordEncoder);
            memberRepository.save(memberEntity1);
        } else {
            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            String newFileName = uuid + "_" + oldFileName;
            String filePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(filePath));

            memberDto.setMemberFileName(newFileName);

            MemberEntity memberEntity1 = MemberEntity.toMemberJoinEntity1(memberDto, passwordEncoder);

            Long memberId = memberRepository.save(memberEntity1).getId();

            MemberEntity memberEntity2 =
                memberRepository.findById(memberId).orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
                });

            MemberFileDto memberFileDto
                = MemberFileDto.builder()
                .memberOldFileName(oldFileName)
                .memberNewFileName(newFileName)
                .memberEntity(memberEntity2)
                .build();

            MemberFileEntity memberFileEntity = MemberFileEntity
                .builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFileName(memberFileDto.getMemberOldFileName())
                .memberNewFileName(memberFileDto.getMemberNewFileName())
                .build();

            memberFileRepository.save(memberFileEntity);
        }
    }

    @Override
    public MemberDto memberDetail(Long id) {

        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 아이디가 없습니다.");
        });

        MemberDto memberDto = MemberDto.toMemberDto(memberEntity);

        return memberDto;
    }

    @Override
    public String uploadMemberImg(MemberDto memberDto) {

        return null;
    }
}
