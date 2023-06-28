package com.field.muzi.web.user.dto.visa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisaSaveRequest {

    private String name;
    private String phone;
    private String country;
    private String address1;
    private String address2;
    private String postCode;
    private String company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
}
