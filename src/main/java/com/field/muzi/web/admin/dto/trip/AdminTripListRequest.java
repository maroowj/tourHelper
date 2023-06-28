package com.field.muzi.web.admin.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTripListRequest {

    private String keyword;
    private String country;
}
