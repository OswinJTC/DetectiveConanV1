package com.tai.DetectiveConanV1.ImageData;



import com.tai.DetectiveConanV1.Movie.Movie;
import com.tai.DetectiveConanV1.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class StorageService {


    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;



    public String uploadImage(MultipartFile file, String username) throws IOException {
        //byte[] compressedImageData = ImageUtils.compressImage(file.getBytes());

        // Your existing uploadImage logic remains unchanged
        ImageData imageData = storageRepository.insert(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes()) // Save the compressed image data
                .build());

        if (imageData != null) {

            Collections Collections = null;
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("username").is(username))
                    .apply(new Update().set("profilePhoto", Collections.emptyList())) // Clear the list
                    .first();

            mongoTemplate.update(User.class)
                    .matching(Criteria.where("username").is(username))
                    .apply(new Update().push("profilePhoto").value(imageData))
                    .first();
            return "File uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String name) {
        // Your existing downloadImage logic remains unchanged
        Optional<ImageData> dbImageData = storageRepository.findByName(name);
        if (dbImageData.isPresent()) {
            ImageData imageData = dbImageData.get();
            return (imageData.getImageData());
        }
        return null;
    }




}
