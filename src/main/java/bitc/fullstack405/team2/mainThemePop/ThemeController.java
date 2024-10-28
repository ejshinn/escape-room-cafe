package bitc.fullstack405.team2.mainThemePop;

import bitc.fullstack405.team2.PopCafe.CafeDTO;
import bitc.fullstack405.team2.PopCafe.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ThemeController {

  @Autowired
  private ThemeService themeService;

  @Autowired
  private CafeService cafeService;

  // 테마 정보 리스트 불러와보기
    @RequestMapping("/main")
  public ModelAndView selectThemeList() throws Exception {
    ModelAndView mv = new ModelAndView("main/main");

    // 팝업에 붙일 테마 데이터
    List<ThemeCafeDTO> themeList = themeService.selectThemeCafeList();
    mv.addObject("themeList", themeList);

    // 그냥 리뷰 함수
    List<ThemeCafeDTO> reviewList = themeService.selectReviewList();
    mv.addObject("reviewList", reviewList);
    //System.out.println("리뷰 리스트 불러오기 성공");

    return mv;
  }


  @ResponseBody
  @RequestMapping(value = "/ajax/mainItems", method = RequestMethod.POST)
  public Object ajaxMainItems(@RequestParam("cafeIdx") int idx) throws Exception {
    List<ThemeCafeDTO> mainItems = themeService.mainItemsList(idx);
    return mainItems;
  }


  // ajax 리뷰 함수
  @ResponseBody
  @RequestMapping(value = "/ajax/reviewlist", method = RequestMethod.POST)
  public Object ajaxReviewList(@RequestParam("cafeIdx") int cafeIdx ,@RequestParam("themeIdx") int themeIdx) throws Exception {
    // 팝업에 붙일 리뷰 목록, 카페id, 테마id 로 식별, 맵퍼에서 파람 사용해야함

    List<ThemeCafeDTO> reviewList = themeService.selectAjaxReviewList(cafeIdx, themeIdx);
    return reviewList;
  }


}
