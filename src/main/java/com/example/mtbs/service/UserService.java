package com.example.mtbs.service;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.UserDetail;

import java.util.List;

public interface UserService{

    public  UserDetail saveUser(UserRegistrationDTO userRegistrationDTO);
    public List<UserDetail> getAllUsers();

}
