package org.spring.e1i4TeamProject.config;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    // 일반 사용자
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByUserEmail(userEmail).orElseThrow(() -> {
            throw new IllegalArgumentException(userEmail + " 이 존재하지 않습니다.");
        });
        System.out.println(memberEntity+" memberEntity");
        // 인증된 회원을 Security 가 관리하도록 설정
        return new MyUserDetailsImpl(memberEntity);
    }
}
