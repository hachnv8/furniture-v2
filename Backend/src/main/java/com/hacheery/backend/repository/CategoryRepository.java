package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by HachNV on 17/04/2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c where c.name like %?1%")
    Page<Category> findByName(String name, Pageable pageable);
    //Page<Category> findByParent(Long parentId, Pageable pageable);
}
