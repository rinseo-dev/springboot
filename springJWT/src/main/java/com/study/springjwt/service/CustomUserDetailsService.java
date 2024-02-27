package com.study.springjwt.service;

import com.study.springjwt.domain.UserEntity;
import com.study.springjwt.dto.CustomUserDetails;
import com.study.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        // userRepository에서 반환값을 UserEntity로 해줬으므로 userEntity에 담아줌

        if(userEntity != null){
            return new CustomUserDetails(userEntity);
        }

        return null;
    }
}
