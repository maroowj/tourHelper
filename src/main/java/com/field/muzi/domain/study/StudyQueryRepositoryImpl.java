package com.field.muzi.domain.study;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.form.FormEntity;
import com.field.muzi.domain.form.FormQueryRepository;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.user.dto.form.FormListResponse;
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
import static com.field.muzi.domain.form.QFormEntity.formEntity;
import static com.field.muzi.domain.study.QStudyEntity.studyEntity;


@Repository
public class StudyQueryRepositoryImpl extends QuerydslRepositorySupport implements StudyQueryRepository {
    private final JPAQueryFactory queryFactory;

    public StudyQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(StudyEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
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
    }

    private BooleanExpression searchBySchoolSeq(String schoolSeq) {
        return schoolSeq != null ? studyEntity.school.firstCourseSeq.eq(schoolSeq) : null;
    }
}
