package com.study.springjwt.service;

import com.study.springjwt.domain.UserEntity;
import com.study.springjwt.dto.EnrollUser;
import com.study.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userEnroll(EnrollUser enrollUser) {
        // enrollUser를 받아서 userEntiry로 바꿔서 저장해야함
        UserEntity data = new UserEntity();
        data.setUsername(enrollUser.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(enrollUser.getPassword()));
        //data.setRole("ROLE_ADMIN"); // 첫 사람은 ADMIN으로 만들고 그 이후로는 ROLE_USER로 생성
        data.setRole("ROLE_ROLE");

        userRepository.save(data);
    }
}
