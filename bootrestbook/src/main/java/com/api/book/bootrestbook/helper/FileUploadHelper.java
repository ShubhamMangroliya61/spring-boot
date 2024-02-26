package com.api.book.bootrestbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

  //   static final String FOLDER_LOCATION ="/home/aditya/Documents/JAVA/Spring-Boot/bootrestbook/src/main/resources/static/images";
  public FileUploadHelper() throws IOException {}

  public boolean uploadFile(MultipartFile file) throws IOException {
    String FOLDER_LOCATION = new ClassPathResource("static/images/")
      .getFile()
      .getAbsolutePath();

    boolean flag = false;

    try {
      Files.copy(
        file.getInputStream(),
        Path.of(FOLDER_LOCATION + File.separator + file.getOriginalFilename()),
        StandardCopyOption.REPLACE_EXISTING
      );
      flag = true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return flag;
  }
}
