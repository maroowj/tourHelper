package com.field.muzi.domain.form;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.user.dto.form.FormListResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
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

import static com.field.muzi.domain.form.QFormEntity.formEntity;


@Repository
public class FormQueryRepositoryImpl extends QuerydslRepositorySupport implements FormQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FormQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(FormEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<FormListResponse> formListByMember(Pageable pageable, MemberEntity member) {
        JPAQuery<FormListResponse> query = queryFactory.select(Projections.constructor(FormListResponse.class,
                        formEntity.formSeq,
                        formEntity.title,
                        formEntity.category,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", formEntity.createDate)
                ))
                .from(
                        formEntity
                )
                .where(formEntity.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(formEntity.getType(), formEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<FormListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }
}
