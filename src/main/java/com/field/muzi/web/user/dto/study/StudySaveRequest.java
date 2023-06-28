package com.field.muzi.web.user.dto.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudySaveRequest {

    private String schoolSeq;
    private String period;
    private String topik;
    private String name;
    private String gender;
    private String phone;
    private String country;
    private String etc;
    private String birth;
}
