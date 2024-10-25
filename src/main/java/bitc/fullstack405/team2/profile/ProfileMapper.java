package bitc.fullstack405.team2.profile;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {

    // 유저 정보 기본보기
    ProfileDTO selectProfile(String userId) throws Exception;

    // 유저 정보 수정하기
    ProfileDTO selectProfileUpdate(String userId) throws Exception;

    // 유저 예약 정보보기
    List<ProfileReservationDTO> selectProfileList(String userId) throws Exception;

    // 유저 예약 현황보기(현재)
    List<ProfileDTO> selectProfileRv(String userId) throws Exception;

    // 유저 예약 현황보기(과거)
    List<ProfileDTO> selectProfileOverRv(String userId) throws Exception;
}