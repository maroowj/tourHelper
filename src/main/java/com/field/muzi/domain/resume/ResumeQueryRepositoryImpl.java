package com.field.muzi.domain.resume;

import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class ResumeQueryRepositoryImpl extends QuerydslRepositorySupport implements ResumeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public ResumeQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(ResumeEntity.class);
        this.queryFactory = queryFactory;
    }

}
