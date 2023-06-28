package com.field.muzi.web.user.dto.board;

import com.field.muzi.domain.board.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailsResponse {

    private String boardSeq;
    private String title;
    private String content;
    private String category;
    private String fileName;
    private String fileUrl;
    private String createDate;
    private boolean answer;

    public BoardDetailsResponse(BoardEntity board, String fileName, String fileUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setBoardSeq(board.getBoardSeq());
        this.setTitle(board.getTitle());
        this.setContent(board.getContent());
        this.setCategory(board.getCategory());
        this.setFileName(fileName);
        this.setFileUrl(fileUrl);
        this.setCreateDate(sdf.format(board.getCreateDate()));
        this.setAnswer(board.isAnswerBoolean());
    }
}
