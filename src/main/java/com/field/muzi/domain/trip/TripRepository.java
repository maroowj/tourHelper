package com.field.muzi.domain.trip;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, String>, TripQueryRepository {
    List<TripEntity> findAllByMember(MemberEntity member);
}
