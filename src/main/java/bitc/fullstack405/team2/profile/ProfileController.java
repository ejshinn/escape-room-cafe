package bitc.fullstack405.team2.profile;

import bitc.fullstack405.team2.review.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ReviewService reviewService;

    // 마이페이지 가기
    @RequestMapping("/profile")
    public ModelAndView profileMain(HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView("profile/profilemain");

        HttpSession session = req.getSession();

        if(session.getAttribute("userId") == null) {
            mv = new ModelAndView("redirect:/user/login");
            return mv;
        }

        String userId = (String) session.getAttribute("userId");

        ProfileDTO profile = profileService.selectProfile(userId);
        mv.addObject("profile", profile);

        // 유저 예약 현황 보기
        List<ProfileReservationDTO> profileList = profileService.selectProfileList(userId);
        mv.addObject("profileList", profileList);

        // 유저 예약 현황(현재)
        List<ProfileDTO> profileRv = profileService.selectProfileRv(userId);
        mv.addObject("profileRv", profileRv);

        // 유저 예약 현황(과거)
        List<ProfileDTO> profileOverRv = profileService.selectProfileOverRv(userId);
        mv.addObject("profileOverRv", profileOverRv);
        return mv;
    }

    //  유저 정보 수정
    @RequestMapping("/profileedit")
    public ModelAndView profileEdit(HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView("profile/profileedit");

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        ProfileDTO profile = profileService.selectProfileUpdate(userId);
        mv.addObject("profile", profile);

        return mv;
    }
}