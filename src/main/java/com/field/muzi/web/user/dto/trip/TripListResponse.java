package com.field.muzi.web.user.dto.trip;

import com.field.muzi.domain.trip.TripEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripListResponse {

    private String title;
    private String content;
    private String createDate;
    private String state;
    private String country;

    public TripListResponse(TripEntity trip) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.setTitle(trip.getCourse().getSecondCourseTitle());
        this.setContent(trip.getContent());
        Date createDate = java.sql.Timestamp.valueOf(trip.getCreateDate());
        this.setCreateDate(sdf.format(createDate));
        this.setState(trip.getState());
        this.setCountry(trip.getCourse().getCountry());
    }
}
