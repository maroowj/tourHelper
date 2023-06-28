package com.field.muzi.web.admin.controller.API;

import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.FileManagerUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.admin.dto.board.AdminBoardListRequest;
import com.field.muzi.web.admin.dto.board.BoardAnswerRequest;
import com.field.muzi.web.admin.dto.board.BoardMemberListRequest;
import com.field.muzi.web.admin.dto.board.BoardMemberListResponse;
import com.field.muzi.web.admin.service.AdminBoardService;
import com.field.muzi.web.user.dto.board.BoardDetailsResponse;
import com.field.muzi.web.user.dto.board.BoardGroupByCategoryResponse;
import com.field.muzi.web.user.dto.board.BoardListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin")
public class AdminBoardAPI {

    private final AdminBoardService adminBoardService;
    private final FileHandler fileHandler;

    @PostMapping("/board")
    public void create(BoardAnswerRequest request) {
        final List<FileManagerEntity> files = new ArrayList<>();
        final List<FileManagerEntity> images = new ArrayList<>();
        Optional.ofNullable(request.getFile()).ifPresent(multipartFile -> {
            files.addAll(fileHandler.parse(Collections.singletonList(multipartFile), "board_file"));
        });

        Optional.ofNullable(request.getImage()).ifPresent(multipartFile -> {
            images.addAll(fileHandler.parse(Collections.singletonList(multipartFile), "board_file"));
        });

        try {
            adminBoardService.create(request,images.stream().findAny().orElse(null), files.stream().findAny().orElse(null));
        }catch (Exception e) {
            files.stream().findAny().ifPresent(FileManagerUtils::delete);
            images.stream().findAny().ifPresent(FileManagerUtils::delete);
        }
    }

    @GetMapping("/board")
    public Page<BoardListResponse> boardListByMemberAndCategory(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable, AdminBoardListRequest request) {
        return adminBoardService.boardListByMemberAndCategory(pageable, request.getMemberSeq(), request.getCategory());
    }

    @GetMapping("/board/member")
    public Page<BoardMemberListResponse> boardMemberList(Pageable pageable, BoardMemberListRequest request) {
        return adminBoardService.boardMemberList(pageable, request);
    }

    @GetMapping("/board/category")
    public List<BoardGroupByCategoryResponse> boardCategoryList(String memberSeq) {
        return adminBoardService.categoryList(memberSeq);
    }

    /*@GetMapping("/board/{boardSeq}")
    public BoardDetailsResponse boardDetails(@PathVariable("boardSeq") String boardSeq) {
        return adminBoardService.boardDetails(boardSeq);
    }*/

    /*@PutMapping("/board/{boardSeq}")
    public void answer(@PathVariable("boardSeq") String boardSeq, BoardAnswerRequest request) {
        adminBoardService.answer(boardSeq, request);
    }*/
}
