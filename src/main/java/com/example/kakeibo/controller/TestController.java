// package com.fullness.kakeibo.controller;

// import com.example.kakeibo.repository.UserRepository;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class TestController {

//     private final UserRepository userRepository;

//     public TestController(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @GetMapping("/test-db")
//     public String testDb() {
//         long count = userRepository.count();
//         return "Users count = " + count;
//     }
// }
