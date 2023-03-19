package ckeditor.practice.ckeditorpracticespring.service;

import ckeditor.practice.ckeditorpracticespring.domain.post.Post;
import ckeditor.practice.ckeditorpracticespring.domain.post.PostRepository;
import ckeditor.practice.ckeditorpracticespring.web.dto.Message;
import ckeditor.practice.ckeditorpracticespring.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Message uploadPost(String title, String content) {
        Post newPost = Post.builder()
                .title(title)
                .content(content)
                .build();

        return new Message("게시글 업로드 성공", new PostDto(postRepository.save(newPost)));
    }

    public Message getAllPost() {
        List<PostDto> all = postRepository.findAll().stream()
                .map(PostDto::new)
                .collect(Collectors.toList());

        return new Message("전체 게시글 조회 성공", all);
    }

    public Message getPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } else {
            return new Message(id + "번 게시글 조회 성공", new PostDto(postOptional.get()));
        }
    }
}
