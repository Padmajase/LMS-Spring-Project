package com.example.lms.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LoginDTO {
    public String emailId;
    public String password;
}
