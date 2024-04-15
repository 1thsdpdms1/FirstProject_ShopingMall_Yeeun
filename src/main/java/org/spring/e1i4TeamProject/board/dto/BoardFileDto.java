package org.spring.e1i4TeamProject.board.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardFileDto {

    private Long id;

    private String boardNewFileName;

    private String boardOldFileName;

    private BoardEntity boardEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
