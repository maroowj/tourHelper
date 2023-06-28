package com.field.muzi.web.admin.service;

import com.field.muzi.domain.board.BoardEntity;
import com.field.muzi.domain.board.BoardRepository;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.admin.dto.board.AdminBoardListRequest;
import com.field.muzi.web.admin.dto.board.BoardAnswerRequest;
import com.field.muzi.web.admin.dto.board.BoardMemberListRequest;
import com.field.muzi.web.admin.dto.board.BoardMemberListResponse;
import com.field.muzi.web.user.dto.board.BoardDetailsResponse;
import com.field.muzi.web.user.dto.board.BoardGroupByCategoryResponse;
import com.field.muzi.web.user.dto.board.BoardListResponse;
import com.field.muzi.web.user.dto.board.BoardSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminBoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final FileHandler fileHandler;

    @Transactional
    public Page<BoardListResponse> boardListByMemberAndCategory(Pageable pageable, String memberSeq, String category) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository, memberSeq);

        List<BoardEntity> boardList = boardRepository.findAllByWriterAndAndReadBooleanAndAnswerBooleanAndCategory(member, false, false, category);
        if(boardList!=null && boardList.size()!=0) {
            for(BoardEntity board : boardList) {
                board.read();
            }
        }

        return boardRepository.boardListByMemberAndCategory(pageable, member, category);
    }

    @Transactional
    public Page<BoardMemberListResponse> boardMemberList(Pageable pageable, BoardMemberListRequest request) {
        Page<BoardMemberListResponse> result = boardRepository.boardMemberList(pageable, request);

        for(BoardMemberListResponse response : result) {
            MemberEntity member = EntityUtils.memberThrowable(memberRepository, response.getMemberSeq());
            response.setNewPostCount(boardRepository.newPostCountOfAdmin(member));
        }
        return result;
    }

    /** 카테고리 별로 묶기 **/
    @Transactional
    public List<BoardGroupByCategoryResponse> categoryList(String memberSeq) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MemberEntity member = EntityUtils.memberThrowable(memberRepository, memberSeq);
        List<BoardGroupByCategoryResponse> result = new ArrayList<>();
        List<String> categoryList = boardRepository.categoryListByMember(member);
        List<String> seqList = new ArrayList<>();
        if(categoryList!=null && categoryList.size()!=0) {
            for(String category : categoryList) {
//                BoardGroupByCategoryResponse response = new BoardGroupByCategoryResponse();
//                response.setCategory(category);
                Optional<BoardEntity> board = boardRepository.findTopByWriterAndCategoryOrderByBoardSeqDesc(member, category);
                seqList.add(board.get().getBoardSeq());
//                String content = board.get().getContent();
//                if(content==null || content.equals("null")) {
//                    content = "사진 또는 파일";
//                }
//                response.setContent(content);
//                response.setNewPostCount(boardRepository.newPostCountOfMember(member, category));
//                Date createDate = java.sql.Timestamp.valueOf(board.get().getCreateDate());
//                response.setCreateDate(sdf.format(createDate));
//                result.add(response);
            }

            Collections.sort(seqList, Collections.reverseOrder());
            for(String seq : seqList) {
                BoardEntity board = EntityUtils.boardThrowable(boardRepository, seq);
                BoardGroupByCategoryResponse response = new BoardGroupByCategoryResponse();
                String content = board.getContent();
                if(content==null || content.equals("null")) {
                    content = "사진 또는 파일";
                }
                response.setCategory(board.getCategory());
                response.setContent(content);
                response.setNewPostCount(boardRepository.newPostCountOfAdmin(member, board.getCategory()));
                Date createDate = java.sql.Timestamp.valueOf(board.getCreateDate());
                response.setCreateDate(sdf.format(createDate));
                result.add(response);
            }
        }

        return result;
    }

    @Transactional
    public void create(BoardAnswerRequest request, FileManagerEntity image, FileManagerEntity file) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository, request.getMemberSeq());

        BoardEntity board = BoardEntity.createByAdmin(
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                member,
                image,
                file
        );
        boardRepository.save(board);
    }

    /*@Transactional
    public BoardDetailsResponse boardDetails(String boardSeq) {
        BoardEntity board = EntityUtils.boardThrowable(boardRepository, boardSeq);

        String fileName=null;
        String fileUrl=null;
        if(!ObjectUtils.isEmpty(board.getFile())) {
            fileName = board.getFile().getOriginName();
            fileUrl = fileHandler.fileUrl(board.getFile());
        }

        return new BoardDetailsResponse(board, fileName, fileUrl);
    }

    @Transactional
    public void answer(String boardSeq,BoardAnswerRequest request) {
        BoardEntity board = EntityUtils.boardThrowable(boardRepository, boardSeq);
        board.answer(request.getAnswer());
    }*/

}
