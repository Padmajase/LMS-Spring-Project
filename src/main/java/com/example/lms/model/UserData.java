package com.example.lms.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserData {
    private int userId;
    private String userName;
    private String emailId;
    private String password;
    private String address;
}
