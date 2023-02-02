package com.backend.Assure.controller;

import com.backend.Assure.dto.UserDTO;
import com.backend.Assure.dto.LoginDTO;
import com.backend.Assure.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.crypto.Data;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;
@GetMapping("/Hi")
public String test(){
    return "hello";
}
    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get data by id", response = Data.class)
    public ResponseEntity<String> getUserName(@PathVariable Long userId) {
        String userName = userService.getUserName(userId);
        return new ResponseEntity<>(userName, HttpStatus.FOUND);
    }

    //Authenticating and registering a new user
    @PostMapping("/user/register")
    public ResponseEntity registerUser(@RequestBody UserDTO userDTO){
        // filtering out and sending just the user mail and mobile for authentication and otp
        // transfer to the user
        Long userID = userService.registerUser(userDTO);
        return ResponseEntity.ok(userID);
    }

    // Login request
    @PostMapping("/user/getUser")
    public ResponseEntity getUserDetails(@RequestBody LoginDTO loginDTO){
        Long userId = userService.getUserDetails(loginDTO);

        return new ResponseEntity(userId, HttpStatus.OK);
    }

    // Deleting phone-otp relation
//    @PostMapping("/user/phoneotp")
//    public ResponseEntity removePhoneOTP(@RequestBody UserDTO userDTO){
////        userService.removePhoneOTP(userDTO);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    @Bean
//    public WebMvcConfigurer configurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/*").allowedOrigins("*");
//            }
//        };
//    }

}
