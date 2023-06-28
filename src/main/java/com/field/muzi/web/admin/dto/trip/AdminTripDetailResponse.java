package com.field.muzi.web.admin.dto.trip;

import com.field.muzi.domain.trip.TripEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTripDetailResponse {

    private String tripSeq;
    private String name;
    private String tel;
    private String email;
    private String title;
    private String country;
    private String content;
    private String state;

    public AdminTripDetailResponse(TripEntity trip) {
        this.setTripSeq(trip.getTripSeq());
        this.setName(trip.getName());
        this.setTel(trip.getTel());
        this.setEmail(trip.getEmail());
        this.setTitle(trip.getCourse().getSecondCourseTitle());
        this.setCountry(trip.getCourse().getCountry());
        this.setContent(trip.getContent());
        this.setState(trip.getState());
    }
}
