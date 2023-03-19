package ckeditor.practice.ckeditorpracticespring.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter
@RequiredArgsConstructor
public class Message {
    private final String message;
    private final Object data;
}
