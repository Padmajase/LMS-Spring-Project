package com.example.lms.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginStatus {
    private LoginDTO loginDTO;
    private String mesage;
}
