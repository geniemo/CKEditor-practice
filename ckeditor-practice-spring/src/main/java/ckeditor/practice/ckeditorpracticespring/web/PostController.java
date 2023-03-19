package ckeditor.practice.ckeditorpracticespring.web;

import ckeditor.practice.ckeditorpracticespring.service.PostService;
import ckeditor.practice.ckeditorpracticespring.web.dto.Message;
import ckeditor.practice.ckeditorpracticespring.web.dto.PostUploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v0/post")
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Message> uploadPost(@RequestBody @Valid PostUploadRequestDto postUploadRequestDto) {
        return ResponseEntity.ok(postService.uploadPost(postUploadRequestDto.getTitle(), postUploadRequestDto.getContent()));
    }

    @GetMapping("")
    public ResponseEntity<Message> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }
}
