package com.field.muzi.domain.board;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.form.FormEntity;
import com.field.muzi.domain.form.FormQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String>, BoardQueryRepository {
    Optional<BoardEntity> findTopByWriterAndCategoryOrderByBoardSeqDesc(MemberEntity member, String category);

    List<BoardEntity> findAllByWriterAndAndReadBooleanAndAnswerBooleanAndCategory(MemberEntity member, boolean readBoolean, boolean answerBoolean, String category);
}
