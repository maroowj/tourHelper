package com.field.muzi.web.user.service;

import com.field.muzi.domain.board.BoardEntity;
import com.field.muzi.domain.board.BoardRepository;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.repository.setup.FileManagerRepository;
import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.utils.FileManagerUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.user.dto.board.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final FileManagerRepository fileManagerRepository;
    private final FileHandler fileHandler;

    @Transactional
    public void create(BoardSaveRequest request, FileManagerEntity image, FileManagerEntity file) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);

        BoardEntity board = BoardEntity.create(
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                member,
                image,
                file
        );
        boardRepository.save(board);
    }

    @Transactional
    public void update(BoardUpdateRequest request, String boardSeq) {
        BoardEntity board = EntityUtils.boardThrowable(boardRepository, boardSeq);

        board.update(request.getTitle(), request.getContent(), request.getCategory());

        if(request.getFile()!=null && !request.getFile().isEmpty()) {
            List<MultipartFile> files = new ArrayList<>();
            files.add(request.getFile());
            try {
                FileManagerEntity img = fileManagerRepository.save(fileHandler.parse(files, "board_file").get(0));
                board.updateFile(img);
            } catch (Exception e) {
                fileHandler.parse(files, "board_file").stream().findAny().ifPresent(FileManagerUtils::delete);
                throw new RuntimeException("이미지 수정에 실패했습니다.");
            }
        }
    }

    @Transactional
    public void drop(String boardSeq) {
        BoardEntity board = EntityUtils.boardThrowable(boardRepository, boardSeq);
        board.withdrawal();
    }

    @Transactional
    public Page<BoardListResponse> boardListByMemberAndCategory(Pageable pageable, String category) {

        MemberEntity member = EntityUtils.memberThrowable(memberRepository);

        List<BoardEntity> boardList = boardRepository.findAllByWriterAndAndReadBooleanAndAnswerBooleanAndCategory(member, false, true, category);
        if(boardList!=null && boardList.size()!=0) {
            for(BoardEntity board : boardList) {
                board.read();
            }
        }

        return boardRepository.boardListByMemberAndCategory(pageable, member, category);
    }

    @Transactional
    public BoardDetailsResponse boardDetails(String boardSeq) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        BoardEntity board = EntityUtils.boardThrowable(boardRepository, boardSeq);

        if(board.getWriter()!=member) {
            throw new CInvalidValueException.CBoardInvalidUserException();
        }

        String fileName = null;
        String fileUrl = null;

        if(!ObjectUtils.isEmpty(board.getFile())) {
            fileName = board.getFile().getOriginName();
            fileUrl = fileHandler.fileUrl(board.getFile());
        }

        return new BoardDetailsResponse(board, fileName, fileUrl);
    }

    /** 카테고리 별로 묶기 **/
    @Transactional
    public List<BoardGroupByCategoryResponse> categoryList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
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
                response.setNewPostCount(boardRepository.newPostCountOfMember(member, board.getCategory()));
                Date createDate = java.sql.Timestamp.valueOf(board.getCreateDate());
                response.setCreateDate(sdf.format(createDate));
                result.add(response);
            }
        }

        return result;
    }
}
