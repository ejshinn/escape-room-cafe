package bitc.fullstack405.team2.profile;

import lombok.Data;

@Data
public class ProfileDTO {
  private String userId;
  private String userPw;
  private String userName;
  private String email;
  private String gender;
  private int age;
  private String mobileNumber;
  private String nickName;
  private int point;
  private String reviewYn;
  private int resId;

  private String serialNum;
  private String cafeName;
  private String themeTitle;
  private String reservationDate;
  private String resTime;
}
