package bitc.fullstack405.team2.configuration;

import bitc.fullstack405.team2.review.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 지정한 외부폴더를 스프링의 내부 폴더로 추가(notice)
        //registry.addResourceHandler("/noticeImg/**").addResourceLocations("file:///C:/fullstack405/noticeImg/");
        registry.addResourceHandler("/noticeImg/**").addResourceLocations("file:///home/ec2-user/noticeImg/");

        // 지정한 외부폴더를 스프링의 내부 폴더로 추가(review)
        //registry.addResourceHandler("/reviewImg/**").addResourceLocations("file:///C:/fullstack405/reviewImg/");
        registry.addResourceHandler("/reviewImg/**").addResourceLocations("file:///home/ec2-user/reviewImg/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheck())
                .addPathPatterns("/review/write", "/review/{boardIdx}");
    }
}