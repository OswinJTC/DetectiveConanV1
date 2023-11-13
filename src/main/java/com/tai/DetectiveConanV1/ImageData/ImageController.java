package com.tai.DetectiveConanV1.ImageData;


import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "https://detective-conan-client.onrender.com")
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
        try {

            return ResponseEntity.ok(storageService.uploadImage(file, username));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }



    @GetMapping(value = "/download/{name}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String name) {
        byte[] imageData = storageService.downloadImage(name);
        if (imageData != null) {
            String[] parts = name.split("\\.");
            String extension = parts[parts.length - 1].toLowerCase();
            MediaType mediaType;
            if (extension.equals("jpg") || extension.equals("jpeg")) {
                mediaType = MediaType.IMAGE_JPEG;
            } else if (extension.equals("png")) {
                mediaType = MediaType.IMAGE_PNG;
            } else {
                return ResponseEntity.badRequest().build(); // Unsupported image format
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            headers.setContentLength(imageData.length);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }









}
