package com.field.muzi.web.admin.service;

import com.field.muzi.domain.trip.TripEntity;
import com.field.muzi.domain.trip.TripRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.trip.AdminTripDetailResponse;
import com.field.muzi.web.admin.dto.trip.AdminTripListRequest;
import com.field.muzi.web.admin.dto.trip.AdminTripListResponse;
import com.field.muzi.web.admin.dto.trip.TripStateUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminTripService {

    private final TripRepository tripRepository;

    @Transactional
    public Page<AdminTripListResponse> tripListResponsePage(Pageable pageable, AdminTripListRequest request) {
        Page<AdminTripListResponse> result = tripRepository.tripListPage(pageable, request);

        int pageSize = pageable.getPageSize();
        int pageNum = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNum;

        for(AdminTripListResponse response : result.getContent()) {
            response.setRowNum(totalCount-dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public AdminTripDetailResponse tripDetails(String tripSeq) {
        TripEntity trip = EntityUtils.tripThrowable(tripRepository, tripSeq);

        return new AdminTripDetailResponse(trip);
    }

    @Transactional
    public void updateState(String tripSeq, TripStateUpdateRequest request) {
        TripEntity trip = EntityUtils.tripThrowable(tripRepository, tripSeq);

        trip.update(request.getState());
    }
}
