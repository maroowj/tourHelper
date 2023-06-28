package com.field.muzi.web.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardMemberListResponse {

    private String memberSeq;
    private String memberName;
    private String memberId;
    private int newPostCount;
    private String createDate;
    private String tel;
    private String preference1;
    private String preference2;
    private String provider;
}
