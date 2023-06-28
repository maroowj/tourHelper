package com.field.muzi.domain.visa;

import com.field.muzi.domain.resume.ResumeEntity;
import com.field.muzi.domain.resume.ResumeQueryRepository;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.admin.dto.visa.VisaListResponseAdmin;
import com.field.muzi.web.common.dto.CommonCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.field.muzi.domain.entity.QFirstCourseEntity.firstCourseEntity;
import static com.field.muzi.domain.entity.QMemberEntity.memberEntity;
import static com.field.muzi.domain.entity.QMemberInfoEntity.memberInfoEntity;
import static com.field.muzi.domain.study.QStudyEntity.studyEntity;
import static com.field.muzi.domain.visa.QVisaEntity.visaEntity;


@Repository
public class VisaQueryRepositoryImpl extends QuerydslRepositorySupport implements VisaQueryRepository {
    private final JPAQueryFactory queryFactory;

    public VisaQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(VisaEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<VisaListResponseAdmin> visaList(Pageable pageable, String keyword) {
        JPAQuery<VisaListResponseAdmin> query = queryFactory.select(Projections.constructor(VisaListResponseAdmin.class,
                        Expressions.constant(0),
                        visaEntity.visaSeq,
                        visaEntity.content,
                        visaEntity.accept,
                        visaEntity.point,
                        visaEntity.name,
                        visaEntity.phone,
                        visaEntity.country,
                        visaEntity.address1,
                        visaEntity.address2,
                        visaEntity.postCode,
                        visaEntity.company,
                        visaEntity.joinDate,
                        memberInfoEntity.email,
                        visaEntity.comment
                ))
                .from(visaEntity)
                .leftJoin(memberEntity).on(visaEntity.member.eq(memberEntity))
                .leftJoin(memberInfoEntity).on(memberEntity.memberInfo.eq(memberInfoEntity))
                .where(
                        searchByKeyword(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(visaEntity.getType(), visaEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<VisaListResponseAdmin> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression searchByKeyword(String keyword) {
        return keyword != null ? visaEntity.name.contains(keyword) : null;
    }
}
