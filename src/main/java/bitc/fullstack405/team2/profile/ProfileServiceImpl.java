package bitc.fullstack405.team2.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public List<ProfileReservationDTO> selectProfileList(String userId) throws Exception {
        return profileMapper.selectProfileList(userId);
    }

    @Override
    public List<ProfileDTO> selectProfileRv(String userId) throws Exception {
        return profileMapper.selectProfileRv(userId);
    }

    @Override
    public List<ProfileDTO> selectProfileOverRv(String userId) throws Exception {
        return profileMapper.selectProfileOverRv(userId);
    }

    @Override
    public ProfileDTO selectProfile(String userId) throws Exception {
        return profileMapper.selectProfile(userId);
    }

    @Override
    public ProfileDTO selectProfileUpdate(String userId) throws Exception {
        return profileMapper.selectProfileUpdate(userId);
    }

}
