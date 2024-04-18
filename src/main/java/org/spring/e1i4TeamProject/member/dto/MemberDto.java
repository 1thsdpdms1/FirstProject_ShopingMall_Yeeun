package org.spring.e1i4TeamProject.member.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;

import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {
    private Long id;

    @Size(min = 3, max = 15)
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPw;

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    private String address;

    private String phoneNumber;

    private Role role;

    private List<BoardEntity> boardEntityList;

    private List<ShopEntity> shopEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
