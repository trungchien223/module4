package com.example.blog.repository;

import com.example.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE "
            + "(:category IS NULL OR :category = '' OR LOWER(b.category.name) LIKE LOWER(CONCAT('%', :category, '%'))) "
            + "AND (:title IS NULL OR :title = '' OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))")
    Page<Blog> search(@Param("category") String category,
                      @Param("title") String title,
                      Pageable pageable);
}

