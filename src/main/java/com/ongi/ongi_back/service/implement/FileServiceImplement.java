package com.ongi.ongi_back.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ongi.ongi_back.service.FileService;

@Service
public class FileServiceImplement implements FileService {

  @Value("${file.path}")
  private String filePath;

  @Value("${file.url}")
  private String fileUrl;

  @Override
  public String upload(MultipartFile file) {
    if (file.isEmpty()) return null;

    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
    String uuid = UUID.randomUUID().toString();
    String saveFileName = uuid + extension;
    String savePath = filePath + saveFileName; // 파일 경로

    try {
      file.transferTo(new File(savePath)); // 실제 저장
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    // 저장된 파일에 접근할 수 있는 URL
    String url = fileUrl + saveFileName;
    return url;
  }

  // FileService에서 파일을 업로드하고 URL을 반환하는 방식
  @Override
  public String[] uploadMultipleFiles(MultipartFile[] files) {
    if (files == null || files.length == 0) return null;

    String[] fileUrls = new String[files.length];

    for (int i = 0; i < files.length; i++) {
        MultipartFile file = files[i];
        if (file.isEmpty()) continue;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName; // 파일 경로
        System.out.println(savePath);

        try {
            file.transferTo(new File(savePath)); // 실제 저장
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        // 서버에서 파일에 접근할 수 있는 URL (localhost:4000을 사용)
        fileUrls[i] = fileUrl + saveFileName;
    }

    return fileUrls;
  }


  @Override
  public Resource getImageFile(String fileName) {
    Resource resource = null;

    try {
      // 파일 경로 + 파일명
      String uri = "file:" + filePath + fileName;
      
      resource = new UrlResource(uri);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    return resource;
  }

  
}
