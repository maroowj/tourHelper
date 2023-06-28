package com.field.muzi.web.user.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormUpdateRequest {

    private String title;
    private Map<String, Object> content;
    private String etc;
}
