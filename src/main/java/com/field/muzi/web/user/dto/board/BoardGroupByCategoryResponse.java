package com.field.muzi.web.user.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGroupByCategoryResponse {

    private String category;
    private String content;
    private String createDate;
    private int newPostCount;
}
