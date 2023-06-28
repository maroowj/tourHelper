package com.field.muzi.web.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminBoardListRequest {

    private String memberSeq;
    private String category;
}
