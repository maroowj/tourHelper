package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.trip.AdminTripDetailResponse;
import com.field.muzi.web.admin.dto.trip.AdminTripListRequest;
import com.field.muzi.web.admin.dto.trip.AdminTripListResponse;
import com.field.muzi.web.admin.dto.trip.TripStateUpdateRequest;
import com.field.muzi.web.admin.service.AdminTripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminTripAPI {

    private final AdminTripService adminTripService;

    @GetMapping("/trip")
    public Page<AdminTripListResponse> tripPage(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable, AdminTripListRequest request) {
        return adminTripService.tripListResponsePage(pageable, request);
    }

    @GetMapping("/trip/{tripSeq}")
    public AdminTripDetailResponse tripDetails(@PathVariable("tripSeq") String tripSeq) {
        return adminTripService.tripDetails(tripSeq);
    }

    @PutMapping("/trip/{tripSeq}")
    public void updateState(@PathVariable("tripSeq") String tripSeq, @RequestBody TripStateUpdateRequest request) {
        adminTripService.updateState(tripSeq, request);
    }
}
