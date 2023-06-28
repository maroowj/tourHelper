package com.field.muzi.repository;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.member.MemberListResponse;
import com.field.muzi.web.user.dto.member.MemberListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String>, MemberQueryRepository {

    //소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드
    Optional<MemberEntity> findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);
    Optional<MemberEntity> findByMemberSeq(String memberSeq);

    Optional<MemberEntity> findBySnsIdAndProvider(String snsId, String provider);

    Optional<MemberEntity> findByMemberSeqAndSnsId(String memberSeq, String snsId);
}
