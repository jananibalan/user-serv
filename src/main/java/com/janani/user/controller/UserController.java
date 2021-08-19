package com.janani.user.controller;


import com.janani.user.VO.ResponseTemplateVO;
import com.janani.user.entity.User;
import com.janani.user.entity.UserLog;
import com.janani.user.repository.LogRepository;
import com.janani.user.repository.UserLogRepository;
import com.janani.user.repository.UserRepository;
import com.janani.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @RequestMapping("/usersLog")
    public List<UserLog> usersLog(){
        return userService.getUserLogs();
    }



    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }

    @PutMapping("/{id}")
    public User updateStudent(@RequestBody User user,@PathVariable("id") long userId) {
        User existingUser=this.userRepository.findById(userId).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setDepartmentId(user.getDepartmentId());
        return this.userRepository.save(existingUser);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User existingUser=this.userRepository.findById(userId).get();
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();

    }





}
