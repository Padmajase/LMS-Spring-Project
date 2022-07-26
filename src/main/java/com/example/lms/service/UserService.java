package com.example.lms.service;

import com.example.lms.dto.LoginDTO;
import com.example.lms.dto.ResponseDTO;
import com.example.lms.dto.UserDTO;
import com.example.lms.email.EmailService;
import com.example.lms.model.UserDetails;
import com.example.lms.repository.UserRepository;
import com.example.lms.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@SessionAttributes({"myOTP"})
public class UserService implements IUserService{


    Random random = new Random(1000);

    HttpSession Asession = new HttpSession() {
        public Object o;

        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int i) {}

        @Override
        public int getMaxInactiveInterval() {return 0;}

        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return o;
        }

        @Override
        public Object getValue(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void setAttribute(String s, Object o) {
            this.o = o;
        }

        @Override
        public void putValue(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public void removeValue(String s) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    };


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails createUser(UserDTO userDTO) {
        UserDetails userDetails = new UserDetails(userRepository.findAll().size() + 1, userDTO);
        userRepository.save(userDetails);
        return userDetails;
    }

    @Override
    public ResponseDTO loginUser(LoginDTO loginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Optional<UserDetails> login = userRepository.findByEmailId(loginDTO.getEmailId());
        if (login.isPresent()) {
            String pwd = login.get().getPassword();
            if (pwd.equals(loginDTO.getPassword())) {
                dto.setMessage("login successful ");
                dto.setData(login.get());
                emailService.sendEmail(loginDTO.getEmailId(), "Login Success", "LoggedIn User Details,\nUserEmail- "
                        + userRepository.findByEmailId(loginDTO.getEmailId()));
                return dto;
            } else {
                dto.setMessage("Sorry! login is unsuccessful");
                dto.setData("Wrong password");
                return dto;
            }
        }
        return new ResponseDTO("User not found!", "Invalid email");
    }

    public List<UserDetails> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public ResponseDTO changePassword(Integer otp,LoginDTO loginDTO, HttpSession session) {
        ResponseDTO dto = new ResponseDTO();
        Integer myOTP = (int) Asession.getAttribute("myOTP");
        System.out.println(myOTP);
        List<UserDetails> userList = this.findUserByEmailId(loginDTO.emailId);
        if (userList.isEmpty()){
            dto.setMessage("Invalid email Id");
            dto.setData(loginDTO.emailId);
            return dto;
        }
        else {
           for (UserDetails userDetails : userList) {
               if(myOTP.equals(otp)) {
                   userDetails.setPassword(loginDTO.password);
                   userRepository.save(userDetails);
                   dto.setMessage("password changed successfuly !");
                   dto.setData(userDetails);
                   return dto;
               }
               else {
                   dto.setMessage(("wrong OTP"));
                   dto.setData(otp);
                   return dto;
               }
            }
        }
        return null;
    }

    private List<UserDetails> findUserByEmailId(String emailId) {
        return userRepository.findUserDetailsBy(emailId);
    }

    @Override
    public ResponseDTO forgetPassword(String emailId, HttpSession session) {
        ResponseDTO dto = new ResponseDTO();
        Optional<UserDetails> user = userRepository.findByEmailId(emailId);
        if (user.isPresent()) {

            // generating random six digit otp
            Integer myOTP = random.nextInt(9999);
            // sending otp to user email
            emailService.sendEmail(emailId, "OTP for email verification", "\n use this otp to set your password  " + myOTP);
            Asession.setAttribute("myOTP", myOTP);
            dto.setMessage("otp has sent to email");
            dto.setData(myOTP);
            return dto;
        }
        return null;
    }

    @Override
    public UserDetails editUserProfile(int userId,UserDTO userDTO) {
        List<UserDetails> userList = this.getUserList();
        for(UserDetails userDetails : userList) {
            if(userDetails.getUserId() == userId) {
                userDetails.setUserName(userDTO.userName);
                userDetails.setEmailId(userDTO.emailId);
                userDetails.setPassword(userDTO.password);
                userDetails.setAddress(userDTO.address);
                userRepository.save(userDetails);
                return userDetails;
            }
        }
        return null;
    }


//    @Override
//    public ResponseDTO getToken(LoginDTO loginDTO) {
//
//        String token;
//        Optional<UserDetails> isUserPresent = userRepository.findByEmailId(loginDTO.getEmailId());
//
//        if (isUserPresent.isPresent()) {
//            String password = isUserPresent.get().getPassword();
//            if (password.equals(loginDTO.getPassword())) {
//                token = tokenUtil.createToken(isUserPresent.get().getUserId());
//                return new ResponseDTO("User is Found", token);
//            } else throw new UserRegistrationException("Password is Wrong");
//        } else {
//            throw new UserRegistrationException("Email Id or Password is Wrong");
//        }
//    }
}
