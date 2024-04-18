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

    @Enumerated(EnumType.STRING)
    private Role role;

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

    public static MemberEntity toMemberJoinEntity(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setName(memberDto.getName());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setPhoneNumber(memberDto.getPhoneNumber());
        memberEntity.setRole(Role.MEMBER);
        memberEntity.setBoardEntityList(memberDto.getBoardEntityList());

        return memberEntity;
    }


}
