//TODO: 로컬테스트용 CORS Config 파일, 배포시 삭제
package shop.kokodo.calculateservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("http://localhost:9090");
          registry.addMapping("/**").allowedOrigins("*");
    }
}

