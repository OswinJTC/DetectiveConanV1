package com.tai.DetectiveConanV1.User;



import com.tai.DetectiveConanV1.ImageData.ImageData;
import com.tai.DetectiveConanV1.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    private String username;
    private String nickname = "";
    private String email;
    private String password;
    private String aboutme = "";


    @DocumentReference
    private List<Review> user_reviews;
    private List<ImageData> profilePhoto;


    public User(String username, String nickname, String email, String password, String aboutme, List<Review> user_reviews, List<ImageData> profilePhoto) {

        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.aboutme = aboutme;
        this.user_reviews = user_reviews;
        this.profilePhoto = profilePhoto;

    }
}
