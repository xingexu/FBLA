package com.fbla.parters.service.icon;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;

@Service
public class IconService implements IconAPI {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${aws.s3.bucket}")
    private String BUCKET;

    private final S3Operations s3Operations;

    public IconService(S3Operations s3Operations) {
        this.s3Operations = s3Operations;
    }

    @Override
    public Resource downloadIcon(String filename) {
        return s3Operations.download(BUCKET, filename);

    }

    @Override
    public String uploadIcon(MultipartFile multipartFile) throws IOException {
        if (!MediaType.IMAGE_PNG.toString().equals(multipartFile.getContentType())) {
            throw new IllegalArgumentException("Only PNG images are allowed");
        }
        try (InputStream is = multipartFile.getInputStream()) {
            S3Resource res = s3Operations.upload(BUCKET, multipartFile.getOriginalFilename(), is,
                    ObjectMetadata.builder().contentType(multipartFile.getContentType()).build());

            return accept(res);
        }

    }

    ResponseEntity<Resource> download(@RequestParam String key) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(s3Operations.download(BUCKET, key));
    }

    private String accept(S3Resource s3Resource) {

        try {
            String url = s3Resource.getURL().toString();

            String path = s3Resource.getFilename();
            log.info("Icon uploaded to S3 {}", url);
            log.info("Icon path: {}", path);

            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
