package com.study.springjwt.controller;

import com.study.springjwt.service.UserService;
import com.study.springjwt.dto.EnrollUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // json으로 주고 받아야 하므로
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String mainRoot(){
        return "Main Controller"; // security를 사용하면 여기 들어갈때도 로그인을 해야함
    }

    @PostMapping("/enroll")
    public String userEnroll(EnrollUser enrollUser){
        System.out.println(enrollUser.getUsername());
        userService.userEnroll(enrollUser);

        return "ok";
    }
}
