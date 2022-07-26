package com.example.lms.service;

import com.example.lms.dto.LoginDTO;
import com.example.lms.dto.ResponseDTO;
import com.example.lms.dto.UserDTO;
import com.example.lms.model.UserDetails;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    UserDetails createUser(UserDTO userDTO);

    ResponseDTO loginUser(LoginDTO loginDTO);


    ResponseDTO forgetPassword(String emailId, HttpSession session);

    UserDetails editUserProfile(int userId, UserDTO userDTO);

//    ResponseDTO getToken(LoginDTO loginDTO);

    List<UserDetails> getUserList();

    ResponseDTO changePassword(Integer otp,LoginDTO loginDTO, HttpSession session);
}
