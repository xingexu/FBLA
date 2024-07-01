package com.fbla.parters.service.icon;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IconAPI {

    public String uploadIcon(MultipartFile file) throws IOException;

    public Resource downloadIcon(String filename);

}
