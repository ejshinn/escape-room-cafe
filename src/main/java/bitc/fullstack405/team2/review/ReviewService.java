package bitc.fullstack405.team2.review;

import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;


public interface ReviewService {
    Page<ReviewDTO> selectReviewList(int pageNum) throws Exception;

    ReviewDTO selectReviewDetail(int boardIdx) throws Exception;

    void updateHitCount(int boardIdx) throws Exception;

    void insertReview(ReviewDTO review, MultipartFile uploadFile) throws Exception;

    void deleteReview(int boardIdx) throws Exception;

    void updateReview(ReviewDTO review, MultipartFile uploadFile) throws Exception;

    int findBoardIdxByResId(int resId) throws Exception;
}