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
public class FileServiceImplement implements FileService{

  // application.properties에 file.path와 file.url을 설정해야 bean 주입을 할 수 있습니다.
  @Value("${file.path}")
  private String filePath;

  @Value("${file.url}")
  private String fileUrl;

  @Override
  public String upload(MultipartFile file) {
    if(file.isEmpty()) return null;

    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
    String uuid = UUID.randomUUID().toString();
    String saveFileName = uuid + extension;
    String savePath = filePath + saveFileName;

    try {
      file.transferTo(new File(savePath));
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    String url = fileUrl + saveFileName;
    return url;
  }

  @Override
  public Resource getImageFile(String fileName) {

    Resource resource = null;

    // description: 파일 저장 경로에 있는 파일 불러오기 //
    try {
      String uri = "file:" + filePath + fileName;
      resource = new UrlResource(uri);
    } catch(Exception exception) {
      exception.printStackTrace();
      return null;
    }

    return resource;
  }
  
}
