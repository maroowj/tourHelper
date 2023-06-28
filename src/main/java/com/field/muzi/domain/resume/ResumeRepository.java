package com.field.muzi.domain.resume;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, String>, ResumeQueryRepository {
    List<ResumeEntity> findAllByMemberOrderByResumeSeqDesc(MemberEntity member);
}
