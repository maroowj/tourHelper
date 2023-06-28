package com.field.muzi.domain.study;

import com.field.muzi.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, String>, StudyQueryRepository {
    List<StudyEntity> findAllByMember(MemberEntity member);
}
