package org.spring.e1i4TeamProject.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.role.Role;

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
            ,fetch = FetchType.LAZY
            ,cascade = CascadeType.REMOVE)
    private List<BoardEntity> boardEntityList;
}
