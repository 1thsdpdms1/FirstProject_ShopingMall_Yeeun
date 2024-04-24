package org.spring.e1i4TeamProject.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.role.Role;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.spring.e1i4TeamProject.shop.entity.ShopEntity;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true) // 기본이 널 허용
    private String address;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String licence;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String memberFileName;

    @Column(nullable = false)
    private int memberAttachFile; //게시글 작성시 파일이 존재하면 1, 없으면 0

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "memberEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<MemberFileEntity> memberFileEntityList;

    @JsonIgnore
    @OneToMany
        (mappedBy = "memberEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<BoardEntity> boardEntityList;

    @JsonIgnore
    @OneToMany
        (mappedBy = "memberEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<ShopEntity> shopEntityList;

    public static MemberEntity toMemberJoinEntity0(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(0);
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(Role.MEMBER);
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }
    public static MemberEntity toMemberJoinEntity1(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(1);
        memberEntity.setMemberFileName(memberDto.getMemberFileName());
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(Role.MEMBER);
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }

    public static MemberEntity toSellerJoinEntity0(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(0);
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(Role.MANAGER);
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }
    public static MemberEntity toSellerJoinEntity1(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(1);
        memberEntity.setMemberFileName(memberDto.getMemberFileName());
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(Role.MANAGER);
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }

    public static MemberEntity toMemberUpdateEntity0(MemberDto memberDto) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberDto.getId());
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(0);
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(memberDto.getRole());
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;

    }
    public static MemberEntity toMemberUpdateEntity1(MemberDto memberDto) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberDto.getId());
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setLicence(memberDto.getLicence());
        memberEntity.setMemberAttachFile(1);
        memberEntity.setMemberFileName(memberDto.getMemberFileName());
        memberEntity.setMemberFileEntityList(memberDto.getMemberFileEntityList());
        memberEntity.setRole(memberDto.getRole());
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }

}
