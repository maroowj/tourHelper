package com.field.muzi.repository;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.user.Role;
import com.field.muzi.web.admin.dto.member.MemberListResponse;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.common.dto.member.LoginRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.field.muzi.domain.entity.QMemberEntity.memberEntity;
import static com.field.muzi.domain.entity.QMemberInfoEntity.memberInfoEntity;
import static com.field.muzi.domain.entity.QMemberTypeEntity.memberTypeEntity;

@Repository
public class MemberQueryRepositoryImpl extends QuerydslRepositorySupport implements MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(MemberEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<MemberEntity> findByMemberIdAndRole(LoginRequest request, List<Role> role) {
        return Optional.ofNullable(
                queryFactory
                        .select(memberEntity)
                        .from(memberEntity)
                        .join(memberTypeEntity).on(memberTypeEntity.member.eq(memberEntity))
                        .where(
                                memberEntity.memberId.eq(request.getMemberId()),
                                searchRole(role),
                                memberEntity.withdrawal.isFalse()
                        )
                        .fetchOne()
        );
    }

    @Override
    public Optional<MemberEntity> findByMemberIdAndRole(String id, Role role) {
        return Optional.ofNullable(
                queryFactory
                        .select(memberEntity)
                        .from(memberEntity)
                        .join(memberTypeEntity).on(memberTypeEntity.member.eq(memberEntity))
                        .where(
                                memberEntity.memberId.eq(id),
                                memberTypeEntity.memberType.eq(role),
                                memberEntity.withdrawal.isFalse()
                        )
                        .fetchOne()
        );
    }

    @Override
    public Page<MemberListResponse> memberList(Pageable pageable, String keyword) {
        JPAQuery<MemberListResponse> query = queryFactory.select(Projections.constructor(MemberListResponse.class,
                Expressions.constant(0),
                memberEntity.memberSeq,
                memberInfoEntity.email,
                memberEntity.provider,
                memberInfoEntity.name,
                memberInfoEntity.tel,
                memberInfoEntity.preference1,
                memberInfoEntity.preference2,
                memberInfoEntity.country
                ))
                .from(memberEntity)
                .leftJoin(memberInfoEntity).on(memberEntity.memberInfo.eq(memberInfoEntity))
                .where(
                        searchByKeyword(keyword),
                        memberEntity.provider.isNotNull(),
                        memberEntity.withdrawal.isFalse()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        QueryResults<MemberListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }


    private Predicate searchRole(List<Role> roles) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (Role role : roles) {
            booleanBuilder.or(
                    memberTypeEntity.memberType.eq(role)
            );
        }
        return booleanBuilder;
    }

    private BooleanExpression searchByKeyword(String keyword) {
        return keyword != null ? memberInfoEntity.name.contains(keyword).or(memberInfoEntity.email.contains(keyword)) : null;
    }
}
