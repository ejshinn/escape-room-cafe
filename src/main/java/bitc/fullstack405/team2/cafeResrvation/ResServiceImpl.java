package bitc.fullstack405.team2.cafeResrvation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResServiceImpl implements ResService {
    @Autowired
    ResMapper resMapper;

    @Override
    public List<ResDTO> selectResTime(int cafeIdx) throws Exception {
        return resMapper.selectResTime(cafeIdx);
    }

    @Override
    public void insertResInfo(ResDTO res) throws Exception {
        resMapper.insertResInfo(res);
    }

    @Override
    public ResDTO selectReservation(int resId) throws Exception {
        return resMapper.selectReservation(resId);
    }

    @Override
    public void updateReviewStatusY(int resId) throws Exception {
        resMapper.updateReviewStatusY(resId);
    }

    @Override
    public void updateReviewStatusN(int resId) throws Exception {
        resMapper.updateReviewStatusN(resId);
    }
}