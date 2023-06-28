package com.field.muzi.web.user.controller.API;

import com.field.muzi.web.user.dto.trip.TripListResponse;
import com.field.muzi.web.user.dto.trip.TripSaveRequest;
import com.field.muzi.web.user.service.TripService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class TripAPI {

    private final TripService tripService;

    @GetMapping("/trip")
    public List<TripListResponse> tripList() {
        return tripService.tripList();
    }

    @PostMapping("/trip")
    public void create(@RequestBody TripSaveRequest request) {
        tripService.create(request);
    }
}
