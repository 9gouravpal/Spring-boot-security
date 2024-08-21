package com.security.config;

import com.security.entity.UserInfo;
import com.security.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserReposiory reposiory;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserInfo> user=reposiory.findByName(name);
        if (user.isPresent()){
            return new UserInfoUserDetails(user.get());
        }
        else {
            throw new UsernameNotFoundException("user not found"+name);
        }
    }
}
