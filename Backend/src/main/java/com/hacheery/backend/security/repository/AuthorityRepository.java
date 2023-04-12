package com.hacheery.backend.security.repository;

import com.hacheery.backend.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authorities} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authorities, String> {
}
