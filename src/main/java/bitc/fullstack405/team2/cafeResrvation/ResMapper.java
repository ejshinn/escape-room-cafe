package bitc.fullstack405.team2.cafeResrvation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResMapper {
    // 변수 2개 보낼 때 값을 받을 파라미터 짝 정해주기
    List<ResDTO> selectResTime(@Param("cafeId") int cafeIdx) throws Exception;

    void insertResInfo(ResDTO res) throws Exception;

    ResDTO selectReservation(int resId) throws Exception;

    void updateReviewStatusY(int resId) throws Exception;

    void updateReviewStatusN(int resId) throws Exception;
}
