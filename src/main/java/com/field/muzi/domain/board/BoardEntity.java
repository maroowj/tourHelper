package com.field.muzi.domain.board;

import com.field.muzi.domain.base.BaseTimeEntity;
import com.field.muzi.domain.base.SeqManager;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.setup.FileManagerEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.jdo.annotations.Join;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Table(name = "board")
@DynamicInsert
@DynamicUpdate
@Entity
public class BoardEntity extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_board")
    @GenericGenerator(name = "seq_manager_board", strategy = "com.field.muzi.domain.base.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "bord_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String boardSeq;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String category;

    @OneToOne
    @JoinColumn(name = "memberSeq")
    private MemberEntity writer;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean answerBoolean;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image")
    private FileManagerEntity image;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file")
    private FileManagerEntity file;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean readBoolean;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean withdrawal;

    public static BoardEntity create(String title, String content, String category, MemberEntity writer, FileManagerEntity image, FileManagerEntity file) {
        BoardEntity board = new BoardEntity();
        board.setTitle(title);
        board.setContent(content);
        board.setCategory(category);
        board.setWriter(writer);
        board.setImage(image);
        board.setFile(file);
        board.setWithdrawal(false);
        board.setAnswerBoolean(false);
        board.setReadBoolean(false);
        return board;
    }

    public static BoardEntity createByAdmin(String title, String content, String category, MemberEntity writer, FileManagerEntity image ,FileManagerEntity file) {
        BoardEntity board = new BoardEntity();
        board.setTitle(title);
        board.setContent(content);
        board.setCategory(category);
        board.setWriter(writer);
        board.setImage(image);
        board.setFile(file);
        board.setWithdrawal(false);
        board.setAnswerBoolean(true);
        board.setReadBoolean(false);
        return board;
    }

    public void read() {
        this.setReadBoolean(true);
    }

    public void update(String title, String content, String category) {
        this.setTitle(title);
        this.setContent(content);
        this.setCategory(category);
    }

    public void updateFile(FileManagerEntity file) {
        this.setFile(file);
    }

    public void answer(String answer) {
        this.answer(answer);
    }

    public void withdrawal() {
        this.setWithdrawal(true);
    }
}
