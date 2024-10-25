package bitc.fullstack405.team2.cafeResrvation;

import java.util.List;

public interface ResService {
    List<ResDTO> selectResTime(int cafeIdx) throws Exception;

    void insertResInfo(ResDTO res) throws Exception;

    ResDTO selectReservation(int resId) throws Exception;

    void updateReviewStatusY(int resId) throws Exception;

    void updateReviewStatusN(int resId) throws Exception;
}