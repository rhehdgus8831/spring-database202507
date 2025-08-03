package com.spring.database.jpa.practice1.repository;

import com.spring.database.jpa.practice1.entity.Post;
import com.spring.database.jpa.practice1.entity.PostTag;
import com.spring.database.jpa.practice1.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    EntityManager em;
    @Autowired
    private PostTagRepository postTagRepository;

    @BeforeEach
    void setUp() {

        Post p1 = Post.builder().writer("트럼프").title("관세 빔~!").content("이번주 관세 떄린다 ㅋㅋ ").build();
        Post p2 = Post.builder().writer("카리나팬").title("카리나 워터밤").content("카리나 워터밤 지림 대황카리나").build();
        Post p3 = Post.builder().writer("구루루").title("롤체 이번시즌 개 꿀잼").content("롤체 이번 시즌 존잼임;;").build();

        Tag t1 = Tag.builder().name("미국주식 갤러리").build();
        Tag t2 = Tag.builder().name("아이돌 갤러리").build();
        Tag t3 = Tag.builder().name("게임 갤러리").build();

        postRepository.saveAllAndFlush(
                List.of(p1, p2, p3)
        );

        tagRepository.saveAllAndFlush(
                List.of(t1, t2, t3)
        );

    }

      @Test
      @DisplayName("1번 게시물에 1번 태그를 연결해야 한다.")
       void TrumpTagCreateTest() {

          //given

          Post post = postRepository.findAll().get(0);
          Tag tag = tagRepository.findAll().get(0);

          //when

          PostTag postTag = PostTag.builder().post(post).tag(tag).build();
          postTagRepository.save(postTag);

          em.flush();
          em.clear();

          //then
          Post foundPost = postRepository.findById(post.getId()).orElseThrow();

          assertEquals(1, foundPost.getPostTags().size());
          System.out.println("foundPost = " + foundPost.getPostTags().get(0));


      }

        @Test
            @DisplayName("데이터 생성 테스트")
            void createPostTest() {

                //given

            Tag tag = Tag.builder().name("부동산 갤러리").build();

            Post post = Post.builder().writer("홈리스").title("두꺼비도 집이 있는데").content("두꺼비 헌 집도 못구하는 헬조선").build();



            //when

                //then
            }





}