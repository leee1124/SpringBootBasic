package com.example.Sbb.Sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * SiteUserEntity의 PK(id)는 Long 타입
 */
public interface UserRepository extends JpaRepository<SiteUserEntity, Long> {
    Optional<SiteUserEntity> findByUsername(String username);
}
