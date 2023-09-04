package com.yjstudy.springstudy.repository;

import com.yjstudy.springstudy.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}