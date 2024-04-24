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
import java.util.Optional;
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
    public void memberUpdate(MemberDto memberDto) throws IOException {
        MemberEntity memberEntity = memberRepository.findById(memberDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("해당 아이디가 없습니다.");
        });

        Optional<MemberFileEntity> optionalMemberFileEntity
            = memberFileRepository.findByMemberEntityId(memberDto.getId());

//        if (optionalMemberFileEntity.isPresent()) {
//            String newFileName = optionalMemberFileEntity.get().getMemberNewFileName();
//            String filePath = "c:/e1i4_file/" + newFileName;
//            File deleteFile = new File(filePath);
//            if (deleteFile.exists()) {
//                deleteFile.delete();
//            } else {
//                System.out.println("파일이 존재하지 않습니다.");
//            }
//            memberFileRepository.delete(optionalMemberFileEntity.get());
//        }

        String oldPw = memberEntity.getUserPw();

        if (memberDto.getMemberFile().isEmpty() && memberDto.getUserPw().equals(oldPw)) {
            memberEntity = MemberEntity.toMemberUpdateEntity0(memberDto);
            memberRepository.save(memberEntity);
        } else if (!memberDto.getMemberFile().isEmpty() && memberDto.getUserPw().equals(oldPw)) {
            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + oldFileName;

            String savePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(savePath));

            memberDto.setMemberFileName(newFileName);

            memberEntity = MemberEntity.toMemberUpdateEntity1(memberDto);

            Long memberId = memberRepository.save(memberEntity).getId();

            MemberEntity memberEntity1 =
                memberRepository.findById(memberId).orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
                });

            MemberFileDto memberFileDto
                = MemberFileDto.builder()
                .memberOldFileName(oldFileName)
                .memberNewFileName(newFileName)
                .memberEntity(memberEntity1)
                .build();

            MemberFileEntity memberFileEntity = MemberFileEntity
                .builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFileName(memberFileDto.getMemberOldFileName())
                .memberNewFileName(memberFileDto.getMemberNewFileName())
                .build();

            memberFileRepository.save(memberFileEntity);

        } else if (memberDto.getMemberFile().isEmpty() && !memberDto.getUserPw().equals(oldPw)) {

            String newPw = passwordEncoder.encode(memberDto.getUserPw());

            memberDto.setUserPw(newPw);
            memberEntity = MemberEntity.toMemberUpdateEntity0(memberDto);
            memberRepository.save(memberEntity);

        } else if (!memberDto.getMemberFile().isEmpty() && !memberDto.getUserPw().equals(oldPw)) {

            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + oldFileName;

            String savePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(savePath));

            memberDto.setMemberFileName(newFileName);

            String newPw = passwordEncoder.encode(memberDto.getUserPw());

            memberDto.setUserPw(newPw);

            memberEntity = MemberEntity.toMemberUpdateEntity1(memberDto);

            Long memberId = memberRepository.save(memberEntity).getId();

            MemberEntity memberEntity1 = memberRepository.findById(memberId).orElseThrow(() -> {
                throw new RuntimeException("해당 아이디가 존재하지 않습니다.");
            });

            MemberFileDto memberFileDto
                = MemberFileDto.builder()
                .memberOldFileName(oldFileName)
                .memberNewFileName(newFileName)
                .memberEntity(memberEntity1)
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
    public void sellerJoin(MemberDto memberDto) throws IOException {
        memberRepository.findByUserEmail(memberDto.getUserEmail()).ifPresent(email -> {
            throw new RuntimeException(memberDto.getUserEmail() + " 이메일이 이미 존재합니다!");
        });

        if (memberDto.getMemberFile().isEmpty()) {
            MemberEntity memberEntity1 = MemberEntity.toSellerJoinEntity0(memberDto, passwordEncoder);
            memberRepository.save(memberEntity1);
        } else {
            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            String newFileName = uuid + "_" + oldFileName;
            String filePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(filePath));

            memberDto.setMemberFileName(newFileName);

            MemberEntity memberEntity1 = MemberEntity.toSellerJoinEntity1(memberDto, passwordEncoder);

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
    public void memberDelete(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException("해당 아이디가 없습니다.");
        });

        memberRepository.deleteById(memberEntity.getId());
    }
}
