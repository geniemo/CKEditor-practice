package ckeditor.practice.ckeditorpracticespring.web.dto;

import ckeditor.practice.ckeditorpracticespring.domain.file.File;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter
@RequiredArgsConstructor
public class FileUploadResponseDto {
    private final String uri;

    public FileUploadResponseDto(File file) {
        this.uri = file.getUri();
    }
}
