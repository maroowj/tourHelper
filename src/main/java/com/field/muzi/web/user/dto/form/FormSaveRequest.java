package com.field.muzi.web.user.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSaveRequest {

    private String title;
    private String category;
    private Map<String, Object> content;
    private String etc;
}
