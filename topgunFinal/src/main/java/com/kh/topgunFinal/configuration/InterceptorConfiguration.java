package com.kh.topgunFinal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.topgunFinal.interceptor.AdminInterceptor;
import com.kh.topgunFinal.interceptor.AirLineInterceptor;
import com.kh.topgunFinal.interceptor.LoginInterceptor;


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	// 로그인 여부
	@Autowired
	private LoginInterceptor loginInterceptor;
		
	// Type이 AIRLINE인지
	@Autowired
	private AirLineInterceptor airLineInterceptor;
	
	// Type이 ADMIN인지
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:3000") // 허용할 출처 (예시)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("Authorization", "Content-Type") // 허용할 헤더를 명시적으로 설정
                .allowCredentials(true); // 쿠키나 인증 정보를 허용할지 여부
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그인 인터셉터를 적용할 경로 추가(즉 로그인만 되어있는지 아닌지 판단할 경우)
		registry.addInterceptor(loginInterceptor)
					.addPathPatterns(
						"/users/**",
						"/room/**",
						"/chat/**",
						"/seats/**",
						"/api/**"
					)
					.excludePathPatterns(
						"/users/login", // 로그인
						"/users/join", // 회원가입
						"/users/resetPw", // 비밀번호 초기화
						"/users/changePassword", // 비밀번호 변경
						"/users/checkId", // 아이디 중복 검증
						"/users/search" // ADMIN만 접근 가능한 경로도 제외
					);
			
		// 로그인 인터셉터를 적용할 경로 추가(즉 로그인만 되어있는지 아닌지 판단할 경우) + TYPE이 AIRLINE인 경우
		registry.addInterceptor(airLineInterceptor)
					.addPathPatterns(
						"/flight/detail/{flightId}",
						"/flight/column/{column}/keyword/{keyword}"
						//그래프 api
						
					)
					.excludePathPatterns(

					);
				
		// 로그인 인터셉터를 적용할 경로 추가(즉 로그인만 되어있는지 아닌지 판단할 경우) + TYPE이 ADMIN인 경우
		registry.addInterceptor(adminInterceptor)
					.addPathPatterns(
						"/users/search", // 회원 목록 리스트 조회
						"/admin/list",//항공편 조회 및 승인 거절
						"/admin/detail/{flight}",
							
							//공지사항 등록시 어드민인 경우만 허용하도록 조회를 제외한 편집 기능 api 를 모두 포함
	                        "/notice/edit/{noticeId}",
	                        "/notice/delete/{noticeId}",
	                        "/notice/post"
	                        //그래프 api
					)
					.excludePathPatterns(

					);
	}
	
}




