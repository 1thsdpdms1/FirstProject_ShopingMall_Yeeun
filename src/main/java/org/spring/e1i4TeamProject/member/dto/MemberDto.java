package org.spring.e1i4TeamProject.member.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;

import org.spring.e1i4TeamProject.member.role.Role;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {
    private Long id;

    private String userEmail;

    private String userPw;

    private String name;

    private String address;

    private String phoneNumber;

    private Role role;

    private List<BoardEntity> boardEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
