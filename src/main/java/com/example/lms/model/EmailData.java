package com.example.lms.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailData {
    private String toEmail;
    private String subject;
    private String body;

    public EmailData(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    public String EmailData() {
        return toEmail;
    }
}
