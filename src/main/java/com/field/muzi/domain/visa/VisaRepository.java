package com.field.muzi.domain.visa;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.resume.ResumeEntity;
import com.field.muzi.domain.resume.ResumeQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisaRepository extends JpaRepository<VisaEntity, String>, VisaQueryRepository {
    List<VisaEntity> findAllByMemberOrderByVisaSeqDesc(MemberEntity member);
    Optional<VisaEntity> findByMember(MemberEntity member);
    boolean existsAllByMember(MemberEntity member);
}
