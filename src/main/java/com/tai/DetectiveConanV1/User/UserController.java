package com.tai.DetectiveConanV1.User;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User retrievedUser = userService.findUserByUsername(user.getUsername());
        if (retrievedUser != null && userService.checkPassword(retrievedUser, user.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/editProfile")
    public ResponseEntity<User> updateAboutMe(@RequestBody User user) {

        return new ResponseEntity<>(userService.updateAboutMe(user.getUsername(), user.getNickname(), user.getEmail(), user.getPassword(), user.getAboutme()), HttpStatus.OK);

    }




}