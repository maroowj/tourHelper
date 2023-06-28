package com.field.muzi.domain.board;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.setup.QDirManagerEntity;
import com.field.muzi.setup.QFileManagerEntity;
import com.field.muzi.web.admin.dto.board.BoardMemberListRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstLIstResponse;
import com.field.muzi.web.user.dto.board.BoardListResponse;
import com.field.muzi.web.admin.dto.board.BoardMemberListResponse;
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

import java.util.List;

import static com.field.muzi.domain.board.QBoardEntity.boardEntity;
import static com.field.muzi.domain.entity.QFirstCourseEntity.firstCourseEntity;


@Repository
public class BoardQueryRepositoryImpl extends QuerydslRepositorySupport implements BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public BoardQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BoardEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<BoardListResponse> boardListByMemberAndCategory(Pageable pageable, MemberEntity member, String category) {
        QFileManagerEntity file = new QFileManagerEntity("file");
        QDirManagerEntity fileDir = new QDirManagerEntity("fileDir");
        QFileManagerEntity image = new QFileManagerEntity("image");
        QDirManagerEntity imageDir = new QDirManagerEntity("imageDir");


        JPAQuery<BoardListResponse> query =  queryFactory.select(Projections.constructor(BoardListResponse.class,
                        boardEntity.boardSeq,
                        boardEntity.content,
                        boardEntity.category,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d (%a) %T')", boardEntity.createDate),
                        boardEntity.answerBoolean,
                        Projections.constructor(BoardListResponse.Image.class,
                                boardEntity.image.originName,
                                imageDir.dirUrl.append("/").append(boardEntity.image.fileName),
                                boardEntity.image.fileExtension).skipNulls(),
                        Projections.constructor(BoardListResponse.File.class,
                                boardEntity.file.originName,
                                fileDir.dirUrl.append("/").append(boardEntity.file.fileName),
                                boardEntity.file.fileExtension).skipNulls()
                ))
                .from(boardEntity)
                .leftJoin(boardEntity.file, file)
                .leftJoin(boardEntity.file.dirManager, fileDir)
                .leftJoin(boardEntity.image, image)
                .leftJoin(boardEntity.image.dirManager, imageDir)
                .where(
                        boardEntity.writer.eq(member),
                        boardEntity.category.eq(category)
                )
                .orderBy(boardEntity.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(boardEntity.getType(), boardEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<BoardListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());


    }

    @Override
    public List<String> categoryListByMember(MemberEntity member) {
        return queryFactory.select(
                        boardEntity.category
                )
                .from(boardEntity)
                .where(
                        boardEntity.writer.eq(member)
                )
                .groupBy(boardEntity.category)
                .orderBy(boardEntity.createDate.desc())
                .fetch();
    }

    @Override
    public int newPostCountOfMember(MemberEntity member, String category) {
        return queryFactory.select(
                        boardEntity.count().intValue()
                )
                .from(boardEntity)
                .where(
                        boardEntity.writer.eq(member),
                        boardEntity.category.eq(category),
                        boardEntity.readBoolean.isFalse(),
                        boardEntity.answerBoolean.isTrue()
                )
                .fetchOne();
    }

    @Override
    public int newPostCountOfAdmin(MemberEntity member, String category) {
        return queryFactory.select(
                        boardEntity.count().intValue()
                )
                .from(boardEntity)
                .where(
                        boardEntity.writer.eq(member),
                        boardEntity.category.eq(category),
                        boardEntity.readBoolean.isFalse(),
                        boardEntity.answerBoolean.isFalse()
                )
                .fetchOne();
    }

    @Override
    public int newPostCountOfAdmin(MemberEntity member) {
        return queryFactory.select(
                        boardEntity.count().intValue()
                )
                .from(boardEntity)
                .where(
                        boardEntity.writer.eq(member),
                        boardEntity.readBoolean.isFalse(),
                        boardEntity.answerBoolean.isFalse()
                )
                .fetchOne();
    }

    @Override
    public Page<BoardMemberListResponse> boardMemberList(Pageable pageable, BoardMemberListRequest request) {
        JPAQuery<BoardMemberListResponse> query = queryFactory.select(Projections.constructor(BoardMemberListResponse.class,
                        boardEntity.writer.memberSeq,
                        boardEntity.writer.memberInfo.memberName,
                        boardEntity.writer.memberInfo.email,
                        Expressions.constant(0),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d %T')", boardEntity.createDate).max(),
                        boardEntity.writer.memberInfo.tel,
                        boardEntity.writer.memberInfo.preference1,
                        boardEntity.writer.memberInfo.preference2,
                        boardEntity.writer.provider
                ))
                .from(boardEntity)
                .where(
                        boardEntity.content.contains(request.getKeyword())
                )
                .groupBy(boardEntity.writer)
                .orderBy(boardEntity.boardSeq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        QueryResults<BoardMemberListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression searchByCategory(String category) {
        return category != null ? boardEntity.category.contains(category) : null;
    }

    private BooleanExpression searchByKeyword(String keyword) {
        return keyword != null ? boardEntity.title.contains(keyword).or(boardEntity.content.contains(keyword)) : null;
    }

   /* private Predicate searchByAnswer(String answer) {
        BooleanBuilder builder = new BooleanBuilder();
        switch (answer) {
            case "전체":
                return null;
            case "답변예정":
                builder.and(boardEntity.answer.isNull().or(boardEntity.answer.eq("")));
                break;
            case "답변완료":
                builder.and(boardEntity.answer.isNotNull().or(boardEntity.answer.ne("")));
                break;
        }
        return builder;
    }*/
}
