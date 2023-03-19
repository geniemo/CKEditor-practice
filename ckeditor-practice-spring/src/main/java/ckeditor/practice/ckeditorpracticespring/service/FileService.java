package ckeditor.practice.ckeditorpracticespring.service;

import ckeditor.practice.ckeditorpracticespring.domain.file.File;
import ckeditor.practice.ckeditorpracticespring.domain.file.FileRepository;
import ckeditor.practice.ckeditorpracticespring.web.dto.FileUploadResponseDto;
import ckeditor.practice.ckeditorpracticespring.web.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    private final Path root;
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        root = Paths.get("/FileServer").toAbsolutePath().normalize();
        this.fileRepository = fileRepository;

        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("root 경로 생성 불가", e);
        }
    }

    @Transactional
    public Message upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("파일이 비어있을 수 없음");
            }
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (filename.contains("..")) {
                throw new RuntimeException("파일 이름이 적절하지 않음: " + filename);
            }

            Files.copy(file.getInputStream(), root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v0/file")
                    .queryParam("uri", filename)
                    .toUriString();
            File fileEntity = File.builder()
                    .uri(uri)
                    .build();
            return new Message("파일 저장 성공", new FileUploadResponseDto(fileRepository.save(fileEntity)));
        } catch (IOException e) {
            throw new RuntimeException("파일을 저장할 수 없음.", e);
        }
    }

    public Resource loadAsResource(String uri) {
        try {
            Path path = root.resolve(uri).normalize();
            UrlResource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException(uri + " 의 파일을 찾을 수 없음.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
