package bitc.fullstack405.team2.review;

import bitc.fullstack405.team2.mainThemePop.ThemeMapper;
import bitc.fullstack405.team2.user.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReviewFileUtils fileUtils;

    @Autowired
    private HttpSession session;

    @Override
    public Page<ReviewDTO> selectReviewList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 5);
        return reviewMapper.selectReviewList();
    }

    // review 게시글 세부 내용
    @Override
    public ReviewDTO selectReviewDetail(int boardIdx) throws Exception {
        return reviewMapper.selectReviewDetail(boardIdx);
    }

    // review 조회수 증가
    @Override
    public void updateHitCount(int boardIdx) throws Exception {
        reviewMapper.updateHitCount(boardIdx);
    }

    @Override
    public void insertReview(ReviewDTO review, MultipartFile uploadFile) throws Exception {

        // 세션에서 userId 값을 가져와 review 객체에 설정
        String userId = (String) session.getAttribute("userId");
        review.setCreateUser(userId);

        if (uploadFile != null && !uploadFile.isEmpty()) {
            String reviewImage = fileUtils.parseFileInfo(uploadFile);
            review.setCommImage(reviewImage);
            userMapper.updateUserPoints700(userId);
        } else {
            review.setCommImage(null);
            userMapper.updateUserPoints300(userId);
        }

        reviewMapper.insertReview(review);
    }

    @Override
    public void deleteReview(int boardIdx) throws Exception {
        reviewMapper.deleteReview(boardIdx);
    }

    @Override
    public void updateReview(ReviewDTO review, MultipartFile uploadFile) throws Exception {
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String reviewImage = fileUtils.parseFileInfo(uploadFile);
            review.setCommImage(reviewImage);
        } else {
            review.setCommImage(null);
        }

        reviewMapper.updateReview(review);
    }

    @Override
    public int findBoardIdxByResId(int resId) throws Exception {
        return reviewMapper.findBoardIdxByResId(resId);
    }
}