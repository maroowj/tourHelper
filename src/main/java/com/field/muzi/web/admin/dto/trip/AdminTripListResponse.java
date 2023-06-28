package com.field.muzi.web.admin.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTripListResponse {

    private int rowNum;
    private String tripSeq;
    private String name;
    private String tel;
    private String email;
    private String content;
    private String title;
    private String country;
    private String state;
}
