package bitc.fullstack405.team2.profile;

import java.util.List;

public interface ProfileService {

    ProfileDTO selectProfile(String userId) throws Exception;

    ProfileDTO selectProfileUpdate(String userId) throws Exception;

    List<ProfileReservationDTO> selectProfileList(String userId) throws Exception;

    List<ProfileDTO> selectProfileRv(String userId) throws Exception;

    List<ProfileDTO> selectProfileOverRv(String userId) throws Exception;
}