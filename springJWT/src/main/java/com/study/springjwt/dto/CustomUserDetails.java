package com.study.springjwt.dto;

import com.study.springjwt.domain.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    // roll값 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        // extends된 값으로 Collection을 ArrayList로 만듦
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
        // DB가 연동되면 userEntity에서 password를 가져옴
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
        // 여기서도 DB연동 후 userEntity에서 username을 가져옴
    }

    // 계정이 만료 되었는지 여부(true:만료되지 않음을 의미 / false:만료)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않은지(true:잠겨있지않음 / false:잠겨있음-로그인 X)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 패스워드가 만료되지 않았는지 여부(true:만료되지 않음 / false:만료됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 사용가능한 계정인지(true:사용가능한 계정을 의미 / false:사용 불가능한 계정)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
