package com.field.muzi.domain.trip;


import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.admin.dto.trip.AdminTripListRequest;
import com.field.muzi.web.admin.dto.trip.AdminTripListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripQueryRepository {

//    Page<StudyListResponseAdmin> studyList(Pageable pageable, String schoolSeq);
    Page<AdminTripListResponse> tripListPage(Pageable pageable, AdminTripListRequest request);
}
