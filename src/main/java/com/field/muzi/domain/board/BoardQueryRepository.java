package com.field.muzi.domain.board;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.board.BoardMemberListRequest;
import com.field.muzi.web.user.dto.board.BoardListResponse;
import com.field.muzi.web.admin.dto.board.BoardMemberListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQueryRepository {
    Page<BoardListResponse> boardListByMemberAndCategory(Pageable pageable, MemberEntity member, String category);

    List<String> categoryListByMember(MemberEntity member);
    int newPostCountOfMember(MemberEntity member, String category);
    int newPostCountOfAdmin(MemberEntity member, String category);
    int newPostCountOfAdmin(MemberEntity member);

    Page<BoardMemberListResponse> boardMemberList(Pageable pageable, BoardMemberListRequest request);
}
