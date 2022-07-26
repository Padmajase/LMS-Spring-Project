package com.example.lms.model;

import com.example.lms.dto.UserDTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
//@Table(name = "user_details")
public class UserDetails {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
//    @Column(name = "user_Name")
    private String userName;
//    @Column(name = "email_Id")
    private String emailId;
//    @Column(name = "password")
    private String password;
//    @Column(name = "address")
    private String address;

    public UserDetails(int userId, UserDTO userDTO) {
        this.userId = userId;
        this.userName = userDTO.userName;
        this.emailId = userDTO.emailId;
        this.password = userDTO.password;
        this.address = userDTO.address;
    }

    public String getUserName() {
        return userName;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        UserDetails that = (UserDetails) o;
//        return userId != null && Objects.equals(userId, that.userId);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
