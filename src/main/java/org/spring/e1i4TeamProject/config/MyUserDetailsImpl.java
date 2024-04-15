package org.spring.e1i4TeamProject.config;

import lombok.*;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MyUserDetailsImpl implements UserDetails, OAuth2User {

    private MemberEntity memberEntity;
    private Map<String, Object> getAttributes;

    public MyUserDetailsImpl(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    public MyUserDetailsImpl(Map<String, Object> getAttributes) {
        this.getAttributes = getAttributes;
    }

    public MyUserDetailsImpl(MemberEntity memberEntity, Map<String, Object> getAttributes) {
        this.memberEntity = memberEntity;
        this.getAttributes = getAttributes;
    }

    @Override
    public String getName() {
        return memberEntity.getName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return getAttributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectionRole = new ArrayList<>();
        collectionRole.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return "ROLE_" + memberEntity.getRole().toString();
            }
        });

        return collectionRole;
    }

    @Override
    public String getPassword() {
        return memberEntity.getUserPw();
    }

    @Override
    public String getUsername() {
        return memberEntity.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
