package com.example.movieticket.service;

import com.example.movieticket.model.User;
import com.example.movieticket.model.Role;
import com.example.movieticket.repository.IRoleRepository;
import com.example.movieticket.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    /*@Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService() {
        return new CustomOAuth2UserService(); // Your custom implementation
    }*/

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        System.out.println("xxx" + email + " " + name);

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            // User not found, register new user
            User user = new User();
            user.setUsername(email);  // Using email as username
            user.setEmail(email);
            user.setFullname(name);
            user.setPassword(new BCryptPasswordEncoder().encode("default_password"));  // Generate or set a random password

            // Set default role
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRoles(Set.of(defaultRole));

            // Save the new user
            userRepository.save(user);
        }

        // Tạo một đối tượng UserDetails
        User userDetails = new User();
        userDetails.setUsername(email);

        // Lưu UserDetails vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(new OAuth2AuthenticationToken(oAuth2User, userDetails.getAuthorities(), userRequest.getClientRegistration().getRegistrationId()));
        // Trả về đối tượng OAuth2User (có thể sử dụng nó sau này)
        return new DefaultOAuth2User(userDetails.getAuthorities(), oAuth2User.getAttributes(), "email");
    }
}
