package com.security.repository;

import com.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposiory extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByName(String name);
}
