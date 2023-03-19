package ckeditor.practice.ckeditorpracticespring.web;

import ckeditor.practice.ckeditorpracticespring.service.FileService;
import ckeditor.practice.ckeditorpracticespring.web.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/v0/file")
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Message> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        Message message = fileService.upload(file);
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping("")
    public ResponseEntity<Resource> download(HttpServletRequest request, @RequestParam String uri) {
        Resource resource = fileService.loadAsResource(uri);
        String contentType = "application/octet-stream";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
