package com.yjstudy.springstudy.repository;

import com.yjstudy.springstudy.SpringStudyApplication;
import com.yjstudy.springstudy.config.JpaConfig;
import com.yjstudy.springstudy.domain.Article;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
@ContextConfiguration(classes = SpringStudyApplication.class)
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorkFine() {
        // given
        
        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(0);
    }
    
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorkFine() {
        // given
        long previousCount = articleRepository.count();

        // when
        Article savedArticle = articleRepository.save(Article.of("new article","new content", "#spring"));

        // then
        assertThat(articleRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorkFine() {
        // given
        articleRepository.save(Article.of("new article","new content","#spring"));
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        // when
        Article savedArticle = articleRepository.saveAndFlush(article);

        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatedHashtag);
    }


}