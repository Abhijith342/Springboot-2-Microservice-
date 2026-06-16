package com.student.student.app.dto;
import com.student.student.app.model.UserRole;

import lombok.Data;

@Data

public class StudentResponse {
    Integer id;
    String fname;
    String lname;
    private String phone;
    private String email;
    private UserRole userRole;
    private AddressDTO address;
   
}
