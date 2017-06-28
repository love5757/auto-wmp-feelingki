package kr.yerina.wmp.autonomousRegistration.interceptor;


import kr.yerina.wmp.autonomousRegistration.properties.WmpServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@EnableConfigurationProperties(WmpServiceProperties.class)
public class HandlerInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private WmpServiceProperties wmpServiceProperties;

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        if(Objects.nonNull(modelAndView)){
            modelAndView.addObject("wmpServiceConfig", wmpServiceProperties);
        }

    }

}
