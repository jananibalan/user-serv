package com.janani.user.service;


import com.janani.user.VO.Department;
import com.janani.user.VO.ResponseTemplateVO;
import com.janani.user.entity.User;
import com.janani.user.entity.UserLog;
import com.janani.user.repository.LogRepository;
import com.janani.user.repository.UserLogRepository;
import com.janani.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private RestTemplate restTemplate;
    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        User save = userRepository.save(user);
        userLogRepository.save(new UserLog(save.getUserId(),200,save.getDepartmentId()));
        return save;


    }



    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo=new ResponseTemplateVO();
        User user=userRepository.findByUserId(userId);

        Department department=
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;


    }

    public List<UserLog> getUserLogs() {
        return userLogRepository.findAll();

    }
}
