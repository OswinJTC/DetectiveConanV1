package com.tai.DetectiveConanV1.User;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query; // Import the correct Query class
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;



    public User findUserByUsername(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null); // Or handle the absence of the user differently

    }

    public boolean checkPassword(User user, String enteredPassword) {
        return user.getPassword().equals(enteredPassword);
    }

    public User updateAboutMe(String username, String nickname,String email,String password,String aboutme) {
        Query query = new Query(Criteria.where("username").is(username));
        User user = mongoTemplate.findOne(query, User.class);


        if(user != null){
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPassword(password);
            user.setAboutme(aboutme);

            mongoTemplate.save(user);

            return user;
        }

        return null;
    }




}