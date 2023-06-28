package com.field.muzi.domain.trip;

import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyQueryRepository;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.admin.dto.trip.AdminTripListRequest;
import com.field.muzi.web.admin.dto.trip.AdminTripListResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
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
import org.springframework.util.StringUtils;

import static com.field.muzi.domain.entity.QFirstCourseEntity.firstCourseEntity;
import static com.field.muzi.domain.entity.QSecondCourseEntity.secondCourseEntity;
import static com.field.muzi.domain.study.QStudyEntity.studyEntity;
import static com.field.muzi.domain.trip.QTripEntity.tripEntity;


@Repository
public class TripQueryRepositoryImpl extends QuerydslRepositorySupport implements TripQueryRepository {
    private final JPAQueryFactory queryFactory;

    public TripQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TripEntity.class);
        this.queryFactory = queryFactory;
    }

    /*@Override
    public Page<StudyListResponseAdmin> studyList(Pageable pageable, String schoolSeq) {
        JPAQuery<StudyListResponseAdmin> query = queryFactory.select(Projections.constructor(StudyListResponseAdmin.class,
                Expressions.constant(0),
                studyEntity.studySeq,
                firstCourseEntity.firstCourseTitle,
                studyEntity.period,
                studyEntity.topik,
                studyEntity.step,
                studyEntity.name,
                studyEntity.gender,
                studyEntity.phone,
                studyEntity.country,
                studyEntity.document,
                studyEntity.etc
        ))
                .from(studyEntity)
                .leftJoin(firstCourseEntity).on(studyEntity.school.eq(firstCourseEntity))
                .where(
                        searchBySchoolSeq(schoolSeq)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(studyEntity.getType(), studyEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<StudyListResponseAdmin> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }*/

    private BooleanExpression searchBySchoolSeq(String schoolSeq) {
        return schoolSeq != null ? studyEntity.school.firstCourseSeq.eq(schoolSeq) : null;
    }

    @Override
    public Page<AdminTripListResponse> tripListPage(Pageable pageable, AdminTripListRequest request) {
        JPAQuery<AdminTripListResponse> query = queryFactory.select(Projections.constructor(AdminTripListResponse.class,
                    Expressions.constant(0),
                    tripEntity.tripSeq,
                    tripEntity.name,
                    tripEntity.tel,
                    tripEntity.email,
                    tripEntity.content,
                    secondCourseEntity.secondCourseTitle,
                    secondCourseEntity.country,
                    tripEntity.state
                ))
                .from(tripEntity)
                .leftJoin(secondCourseEntity).on(tripEntity.course.eq(secondCourseEntity))
                .where(
                        searchByKeyword(request.getKeyword()),
                        searchByCountry(request.getCountry())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(tripEntity.getType(), tripEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<AdminTripListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression searchByKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? tripEntity.name.contains(keyword).or(tripEntity.email.contains(keyword)) : null ;
    }

    private BooleanExpression searchByCountry(String country) {
        return StringUtils.hasText(country) ? tripEntity.course.country.contains(country) : null ;
    }
}
