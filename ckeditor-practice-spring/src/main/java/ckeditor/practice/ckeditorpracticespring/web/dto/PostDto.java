package ckeditor.practice.ckeditorpracticespring.web.dto;

import ckeditor.practice.ckeditorpracticespring.domain.post.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter
@RequiredArgsConstructor
public class PostDto {

    private final Long id;
    private final String title;
    private final String content;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
