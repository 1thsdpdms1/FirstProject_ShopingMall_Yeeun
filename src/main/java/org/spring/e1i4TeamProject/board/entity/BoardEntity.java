package org.spring.e1i4TeamProject.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false)
    private String boardWriter;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int boardHit;

    @Column(nullable = false)
    private int boardAttachFile;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardFileEntity> boardFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardReplyEntity> boardReplyEntityList;
}
