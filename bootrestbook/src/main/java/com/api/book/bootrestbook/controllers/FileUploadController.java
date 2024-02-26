package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.helper.FileUploadHelper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

  @Autowired
  private FileUploadHelper fileUploadHelper;

  @PostMapping("/uploadFile")
  public ResponseEntity<String> uploadFile(
    @RequestParam("file") MultipartFile file
  ) {
    System.out.println(file.getContentType());
    if (
      (
        !file.getContentType().equals("image/jpeg") &&
        !file.getContentType().equals("image/png")
      ) ||
      file.isEmpty()
    ) {
      return ResponseEntity.badRequest().body("Bad request");
    }

    try {
      boolean isFileUploaded = fileUploadHelper.uploadFile(file);

      if (isFileUploaded) return ResponseEntity.ok(
        ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/images/")
          .path(file.getOriginalFilename())
          .toUriString()
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body("Something went wrong..");
  }
}
