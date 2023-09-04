package com.yjstudy.springstudy.repository;

import com.yjstudy.springstudy.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
