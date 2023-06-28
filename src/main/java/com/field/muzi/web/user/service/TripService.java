package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.SecondCourseEntity;
import com.field.muzi.domain.trip.TripEntity;
import com.field.muzi.domain.trip.TripRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.repository.SecondCourseRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.user.dto.trip.TripListResponse;
import com.field.muzi.web.user.dto.trip.TripSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;
    private final SecondCourseRepository secondCourseRepository;

    @Transactional
    public void create(TripSaveRequest request) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        SecondCourseEntity course = EntityUtils.tourThrowable(secondCourseRepository, request.getCourseSeq());

        tripRepository.save(
                TripEntity.create(
                        request.getName(),
                        request.getTel(),
                        request.getEmail(),
                        request.getContent(),
                        member,
                        course
                )
        );
    }

    @Transactional
    public List<TripListResponse> tripList() {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        List<TripListResponse> tripList = new ArrayList<>();
        List<TripEntity> tripEntities = tripRepository.findAllByMember(member);
        for(TripEntity trip : tripEntities) {
            TripListResponse response = new TripListResponse(trip);
            tripList.add(response);
        }
        return tripList;
    }
}
