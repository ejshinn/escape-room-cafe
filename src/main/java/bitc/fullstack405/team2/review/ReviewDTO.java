package bitc.fullstack405.team2.review;

import lombok.Data;

// communication 테이블과 데이터를 주고 받기 위한 DTO 클래스
@Data
public class ReviewDTO {
    private int boardIdx;
    private String commTitle;
    private String contents;
    private String createAt;
    private String createUser;
    private String commImage;
    private String themeTitle;
    private int cafeId;
    private int commThemeIdx;
    private int hitCnt;
    private String cafeName;
    private int resId;
}