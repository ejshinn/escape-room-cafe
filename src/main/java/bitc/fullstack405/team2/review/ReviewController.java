package bitc.fullstack405.team2.review;

import bitc.fullstack405.team2.cafeResrvation.ResDTO;
import bitc.fullstack405.team2.cafeResrvation.ResService;
import bitc.fullstack405.team2.mainThemePop.ThemeDTO;
import bitc.fullstack405.team2.mainThemePop.ThemeService;
import bitc.fullstack405.team2.notice.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ResService resService;

    // review 게시판 목록 출력(페이징)
    @GetMapping("/review")
    public ModelAndView selectReviewList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("review/reviewList");

        List<ReviewDTO> reviewList = reviewService.selectReviewList(pageNum);

        PageInfo<ReviewDTO> pageInfo = new PageInfo<>(reviewList, 5);

        mv.addObject("reviewList", reviewList);
        mv.addObject("pageInfo", pageInfo);

        return mv;
    }

    private ModelAndView getReviewDetailModelAndView(int boardIdx) throws Exception {
        reviewService.updateHitCount(boardIdx); // 조회수 업데이트

        ModelAndView mv = new ModelAndView("review/reviewDetail");

        ReviewDTO review = reviewService.selectReviewDetail(boardIdx); // 리뷰 상세 조회

        mv.addObject("review", review);

        return mv;
    }

    // 마이페이지 -> review 게시물 상세
    @GetMapping("/mypage/review/{resId}")
    public ModelAndView selectMyPageToReview(@PathVariable("resId") int resId) throws Exception {
        int boardIdx = reviewService.findBoardIdxByResId(resId); // resId로 boardIdx 조회

        return getReviewDetailModelAndView(boardIdx);
    }

    // review 게시물 상세
    @GetMapping("/review/{boardIdx}")
    public ModelAndView selectReviewDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
        return getReviewDetailModelAndView(boardIdx);
    }

    // review 게시글 작성(view)
    @GetMapping("/review/write/{resId}")
    public ModelAndView reviewWrite(@PathVariable("resId") int resId) throws Exception {
        ModelAndView mv = new ModelAndView("review/reviewWrite");

        ResDTO reservation = resService.selectReservation(resId);

        mv.addObject("reservation", reservation);
        return mv;
    }

    // review 게시글 작성(내부 처리)
    @PostMapping("/review/write")
    public String reviewWrite(ReviewDTO review, @RequestParam("uploadFile") MultipartFile uploadFile) throws Exception {
        reviewService.insertReview(review, uploadFile);
        resService.updateReviewStatusY(review.getResId());
        return "redirect:/review";
    }

    // review 게시물 삭제
    @DeleteMapping("/review/{boardIdx}")
    public String deleteReview(int boardIdx) throws Exception {
        reviewService.deleteReview(boardIdx);
        resService.updateReviewStatusN(boardIdx);
        return "redirect:/review";
    }

    // review 게시글 수정(view)
    @GetMapping("/review/edit/{boardIdx}/{commThemeIdx}")
    public ModelAndView noticeEdit(@PathVariable("boardIdx") int boardIdx, @PathVariable("commThemeIdx") int commThemeIdx) throws Exception {

        reviewService.updateHitCount(boardIdx);

        ModelAndView mv = new ModelAndView("review/reviewEdit");

        ReviewDTO review = reviewService.selectReviewDetail(boardIdx);
        review.setCommThemeIdx(commThemeIdx);
        mv.addObject("review", review);

        // 테마 리스트를 가져와서 모델에 추가
        List<ThemeDTO> themes = themeService.getThemesForCafe(review.getCafeName());
        mv.addObject("themes", themes);

        return mv;
    }

    // review 게시물 수정(내부 처리)
    @PutMapping("/review/edit/{boardIdx}/{commThemeIdx}")
    public String updateReview(@PathVariable("boardIdx") int boardIdx, ReviewDTO review, @RequestParam("uploadFile") MultipartFile uploadFile) throws Exception {
        int cafeId = noticeService.getCafeIdByName(review.getCafeName());

        // cafeId를 review 객체에 설정
        review.setCafeId(cafeId);

        // boardIdx review 객체에 설정
        review.setBoardIdx(boardIdx);

        reviewService.updateReview(review, uploadFile);

        return "redirect:/review";
    }
}