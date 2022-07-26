package com.example.lms.controller;

import com.example.lms.dto.LoginDTO;
import com.example.lms.dto.ResponseDTO;
import com.example.lms.dto.UserDTO;
import com.example.lms.email.EmailService;
import com.example.lms.model.UserDetails;
import com.example.lms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
//@SessionAttributes({"myOTP"})
public class UserController {


    /*************** autowiring user service object ***************/
    @Autowired
    private IUserService userService;

    @Autowired
    EmailService emailService;

    /*************** registering user ***************/
    @PostMapping("/register/")
    public ResponseEntity<ResponseDTO> userRegistration(@RequestBody UserDTO userDTO){
        UserDetails userDetails;
        userDetails = userService.createUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Hello "+userDetails.getUserName() +
                " u have Registered Successfully with details", userDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** logging in user ***************/
    @PostMapping("/login/")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO loginDTO){
        ResponseDTO responseDTO = userService.loginUser(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** forget password ***************/
    @PutMapping("/forgetpassword/{emailId}")
    public ResponseEntity<ResponseDTO> forgetPassword(@PathVariable("emailId") String emailId,
                                                      HttpSession session){
        ResponseDTO responseDTO = userService.forgetPassword(emailId, session);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /*************** forget password ***************/
    @PutMapping("/changepassword/{otp}")
    public ResponseEntity<ResponseDTO> setPassword(@PathVariable("otp") Integer otp,
            @RequestBody LoginDTO loginDTO, HttpSession session){
        ResponseDTO responseDTO = userService.changePassword(otp,loginDTO, session);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /*************** updating user details ***************/
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable("userId") int userId,
                                                    @RequestBody UserDTO userDTO){
        UserDetails userDetails;
        userDetails = userService.editUserProfile(userId, userDTO);
        ResponseDTO responseDTO = new ResponseDTO(userDetails.getUserName() +
                " U have Updated Your Profile",userDetails);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /*************** getting registered user list ***************/
    @RequestMapping("/userList/")
    public ResponseEntity<ResponseDTO> getUserList(){
        List<UserDetails> listOfUser;
        listOfUser = userService.getUserList();
        ResponseDTO responseDTO = new ResponseDTO("User List :",listOfUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
