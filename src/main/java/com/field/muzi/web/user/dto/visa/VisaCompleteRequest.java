package com.field.muzi.web.user.dto.visa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisaCompleteRequest {

    private String visaSeq;
    private Map<String, Object> content;
    private int point;
}
