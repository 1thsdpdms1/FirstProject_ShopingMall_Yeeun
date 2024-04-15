package org.spring.e1i4TeamProject.config;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.spring.e1i4TeamProject.member.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MyDefaultOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    // OAuth (사용자)
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientId = clientRegistration.getClientId(); // 클라이언트 ID
        String registrationId = clientRegistration.getRegistrationId(); // google, kakao, naver

        Map<String, Object> attributes = oAuth2User.getAttributes();

        return oAuth2UserSuccess(oAuth2User, registrationId);
    }

    private OAuth2User oAuth2UserSuccess(OAuth2User oAuth2User, String registrationId) {
        String userEmail = "";
        String name = "";
        String userPw = "";
        String address = ""; // 자동

        if (registrationId.equals("google")) {
            System.out.println("구글 정보");
            userEmail = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        } else if (registrationId.equals("kakao")) {
            System.out.println("kakao");
            // JSON으로 받은 데이터 map으로 변환
            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) response.get("profile");

            System.out.println(profile + "kakao profile");
            System.out.println(response.get("email") + " <------");
//            userEmail = (String) response.get("email");
//            userEmail = (String) response.get("email");
            name = (String) profile.get("nickname");
            userEmail = name + "@kakao.test";
        } else if (registrationId.equals("naver")) {
            System.out.println("naver");
//            String provider = oAuth2User.getProvider();
//            String providerId = oAuth2User.getProviderId();
            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("response");
            userEmail = (String) response.get("email");
            name = (String) response.get("name");
//            oAuth2User = new MyUserDetailsImpl((Map)oAuth2User.getAttributes().get("response"));
        }

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserEmail(userEmail);

        if (optionalMemberEntity.isPresent()) {

            return new MyUserDetailsImpl(optionalMemberEntity.get());
        }
        userPw = passwordEncoder.encode("fdsafasdf");
//        address = "서울";

        MemberEntity memberEntity
            = memberRepository.save(MemberEntity.builder()
            .userEmail(userEmail)
            .userPw(userPw)
            .name(name)
//            .address(address)
            .role(Role.MEMBER)
            .build());

        return new MyUserDetailsImpl(memberEntity, oAuth2User.getAttributes());
    }
}
