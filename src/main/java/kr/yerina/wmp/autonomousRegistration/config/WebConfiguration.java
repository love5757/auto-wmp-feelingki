package kr.yerina.wmp.autonomousRegistration.config;

import kr.yerina.wmp.autonomousRegistration.interceptor.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Autowired
    private HandlerInterceptor handlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/public/**");
    }

}
