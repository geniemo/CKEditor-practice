package ckeditor.practice.ckeditorpracticespring.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@Setter
@RequiredArgsConstructor
public class PostUploadRequestDto {

    @NotEmpty
    private final String title;

    @NotEmpty
    private final String content;
}
