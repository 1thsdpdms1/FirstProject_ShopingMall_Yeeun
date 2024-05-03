package org.spring.e1i4TeamProject.member.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.entity.MemberFileEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.springframework.web.multipart.MultipartFile;

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

    @NotBlank(message = "비밀번호는 필수 사항입니다.")
    private String userPw;

    @NotBlank(message = "비밀번호를 확인 해주세요.")
    private String userPwCheck;

    @NotBlank(message = "이름은 필수 사항입니다.")
    private String name;

    private String address;

    private String phoneNumber;

    private String licence;

    private Role role;

    private int memberAttachFile;

    private MultipartFile memberFile;

    private String memberNewFileName;

    private String memberOldFileName;

    private String memberFileName;

    private List<MemberFileEntity> memberFileEntityList;

    private List<BoardEntity> boardEntityList;

    private List<ShopEntity> shopEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {

        MemberDto memberDto = new MemberDto();

        memberDto.setId(memberEntity.getId());
        memberDto.setUserEmail(memberEntity.getUserEmail());
        memberDto.setUserPw(memberEntity.getUserPw());
        memberDto.setName(memberEntity.getName());
        memberDto.setAddress(memberEntity.getAddress());
        memberDto.setPhoneNumber(memberEntity.getPhoneNumber());
        memberDto.setRole(memberEntity.getRole());
        memberDto.setLicence(memberEntity.getLicence());
        memberDto.setBoardEntityList(memberEntity.getBoardEntityList());
        memberDto.setShopEntityList(memberEntity.getShopEntityList());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());
        memberDto.setLicence(memberEntity.getLicence());
        if(memberEntity.getMemberAttachFile()==0){
            //파일 x
            memberDto.setMemberAttachFile(memberDto.getMemberAttachFile());
        }else {
            memberDto.setMemberAttachFile(memberDto.getMemberAttachFile());
            memberDto.setMemberNewFileName(memberEntity.getMemberFileEntityList().get(0).getMemberNewFileName());
            memberDto.setMemberOldFileName(memberEntity.getMemberFileEntityList().get(0).getMemberOldFileName());
        }

        if (memberEntity.getMemberAttachFile() == 0) {
            memberDto.setMemberAttachFile(memberEntity.getMemberAttachFile());
        } else {
            memberDto.setMemberAttachFile(memberEntity.getMemberAttachFile());
            memberDto.setMemberFileName(memberEntity.getMemberFileEntityList().get(0).getMemberNewFileName());
            memberDto.setMemberOldFileName(memberEntity.getMemberFileEntityList().get(0).getMemberOldFileName());
            memberDto.setMemberNewFileName(memberEntity.getMemberFileEntityList().get(0).getMemberNewFileName());
        }

        return memberDto;
    }
}
