package com.field.muzi.web.user.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripSaveRequest {

    private String name;
    private String tel;
    private String email;
    private String content;
    private String courseSeq;
}
