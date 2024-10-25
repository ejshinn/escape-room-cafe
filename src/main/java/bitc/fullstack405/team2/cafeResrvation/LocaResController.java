package bitc.fullstack405.team2.cafeResrvation;

import bitc.fullstack405.team2.mainThemePop.ThemeCafeDTO;
import bitc.fullstack405.team2.mainThemePop.ThemeService;
import bitc.fullstack405.team2.user.UserDTO;
import bitc.fullstack405.team2.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
public class LocaResController {

  @Autowired
  ThemeService themeService;

  @Autowired
  ResService resService;

  @Autowired
  UserService userService;

  // 주소에서 카페이름, idx 받아오기
  @RequestMapping("reservation/{cafeName}/{cafeIdx}")
  public ModelAndView HongdaeSelect(@PathVariable("cafeName") String cafeName, @PathVariable("cafeIdx") int cafeIdx) throws Exception {
    // requestparam 대신 pathvariable 쓰니까 됨
    
    // 카페이름 받아오고 뷰 주소에 붙임
    ModelAndView mv = new ModelAndView("reservation/" + (cafeName));
    List<ThemeCafeDTO> themeList = themeService.mainItemsList(cafeIdx);
    mv.addObject("themeList", themeList);

    return mv;
  }

  // 예약 정보 입력 페이지로 이동
  @RequestMapping("reservation/{cafeName}/{cafeIdx}/{themeIdx}/{time}/{date}")
  public ModelAndView CafeResInfo(@PathVariable("cafeIdx") int cafeIdx, @PathVariable("themeIdx") int themeIdx, @PathVariable("time") int time, @PathVariable("date") String date, HttpServletRequest req) throws Exception{
    ModelAndView mv = new ModelAndView("reservation/res2");
    ThemeCafeDTO themeInfo = themeService.selectTheme(cafeIdx, themeIdx);

    mv.addObject("themeInfo", themeInfo);
    mv.addObject("date", date);
    mv.addObject("time", time);

    HttpSession session = req.getSession();
    String userId = (String)session.getAttribute("userId");
    if (userId == null){
      return new ModelAndView("/user/login");
    }

    UserDTO user = userService.getUserInfo(userId);
    mv.addObject("user", user);

    Random random = new Random();

    String serialNum = date + themeInfo.getCafeId() + themeInfo.getThemeIdx() + random.nextInt(1000);

    mv.addObject("serialNum", serialNum);
    return mv;
  }

  @ResponseBody
  @RequestMapping(value="/ajax/restimelist", method = RequestMethod.POST)
  public Object resTime(@RequestParam("cafeIdx") int cafeIdx) throws Exception {
    // 해당 지점+테마에 시간 행 다 불러와서 제이쿼리로 비교
    // 예약 안했으면 null 임
    List<ResDTO> restimeList = resService.selectResTime(cafeIdx);

    return restimeList;
  }

  // 예약 정보 저장
  @PostMapping("/reservation/write")
  public String writeReservation(ResDTO res, HttpServletRequest req) throws Exception {
    resService.insertResInfo(res);
    req.setAttribute("res", res);
    return "forward:/reservation/complete";
  }

  @RequestMapping("/reservation/complete")
  public String complete() throws Exception {

    return "reservation/complete";
  }


}
