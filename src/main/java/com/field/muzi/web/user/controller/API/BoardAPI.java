package com.field.muzi.web.user.controller.API;

import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.FileManagerUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.user.dto.board.*;
import com.field.muzi.web.user.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.internal.compiler.tool.EclipseFileManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class BoardAPI {

    private final FileHandler fileHandler;
    private final BoardService boardService;

    @PostMapping("/board")
    public void create(BoardSaveRequest request) {
        final List<FileManagerEntity> files = new ArrayList<>();
        final List<FileManagerEntity> images = new ArrayList<>();
        Optional.ofNullable(request.getFile()).ifPresent(multipartFile -> {
            files.addAll(fileHandler.parse(Collections.singletonList(multipartFile), "board_file"));
        });

        Optional.ofNullable(request.getImage()).ifPresent(multipartFile -> {
            images.addAll(fileHandler.parse(Collections.singletonList(multipartFile), "board_file"));
        });

        try {
            boardService.create(request,images.stream().findAny().orElse(null), files.stream().findAny().orElse(null));
        }catch (Exception e) {
            files.stream().findAny().ifPresent(FileManagerUtils::delete);
            images.stream().findAny().ifPresent(FileManagerUtils::delete);
        }
    }

    @GetMapping("/board")
    public Page<BoardListResponse> boardListByMemberAndCategory(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable, BoardListRequest request) {
        return boardService.boardListByMemberAndCategory(pageable, request.getCategory());
    }

    @GetMapping("/board/category")
    public List<BoardGroupByCategoryResponse> categoryList() {
        return boardService.categoryList();
    }

    /*@GetMapping("/board/{boardSeq}")
    public BoardDetailsResponse boardDetails(@PathVariable("boardSeq") String boardSeq) {
        return boardService.boardDetails(boardSeq);
    }

    @PutMapping("/board/{boardSeq}")
    public void update(BoardUpdateRequest request, @PathVariable("boardSeq") String boardSeq) {
        boardService.update(request, boardSeq);
    }

    @DeleteMapping("/board/{boardSeq}")
    public void withdrawal(@PathVariable("boardSeq") String boardSeq) {
        boardService.drop(boardSeq);
    }*/
}
