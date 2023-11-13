package com.tai.DetectiveConanV1.User;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public User createUser(String username, String email, String password) {
        User user = userRepository.insert(new User(username, "",email, password, "", new ArrayList<>(), new ArrayList<>()));
        // Additional logic for managing user data or related operations can be added here
        return user;
    }
}
