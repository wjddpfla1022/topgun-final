package com.kh.topgunFinal.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.topgunFinal.service.TokenService;
import com.kh.topgunFinal.vo.UserClaimVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//회원만 통과시키는 인터셉터
//- 기존과 달라진 점은 HttpSession으로 사용자를 조회하는 것이 아니라
//- Authorization이라는 이름의 헤더를 조사하여 검증을 해야한다
@Service
public class MemberInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	System.out.println("멤버 인터셉터 실행: " + request.getRequestURI());

		// [1] OPTIONS 요청이 들어오면 통과시킨다
		// - options는 통신이 가능한지 확인하는 선발대 형식의 요청
		// - CORS 상황이거나 GET, HEAD, POST와 같은 일반적인 요청이 아니면 발생
		String method = request.getMethod();
		if (method.toLowerCase().equals("options")) {
			return true;
		}
    	
    	
    	String token = request.getHeader("Authorization");
        UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(token));

        // 사용자 타입 확인
        if (claimVO != null && "MEMBER".equals(claimVO.getUserType())) {
            return true; // MEMBER 타입인 경우
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Member only");
        return false; // MEMBER 타입이 아닌 경우
    }
}
