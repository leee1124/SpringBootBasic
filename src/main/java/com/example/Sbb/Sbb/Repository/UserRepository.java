package com.example.Sbb.Sbb.Repository;

import com.example.Sbb.Sbb.Entity.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SiteUserEntity의 PK(id)는 Long 타입
 */
public interface UserRepository extends JpaRepository<SiteUserEntity, Long> {
}
