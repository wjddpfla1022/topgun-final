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
public class AirLineInterceptor implements HandlerInterceptor{
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		//[1] OPTIONS 요청이 들어오면 통과시킨다
		//- options는 통신이 가능한지 확인하는 선발대 형식의 요청
		//- CORS 상황이거나 GET, HEAD, POST와 같은 일반적인 요청이 아니면 발생
		String method = request.getMethod();
		if(method.toLowerCase().equals("options")) {
			return true;
		}
		
		//[2] Authorization 헤더를 검사
		//(1) Authorization 헤더가 반드시 존재해야 함
		//(2) 헤더의 값은 Bearer로 시작해야 함
		//(3) 해석했을 때 userId와 userType이 나와야 함 (유효한 토큰)
		//(3) - 1 해석한 토큰의 userType이 AIRLINE인지 확인
		//(4) 이 중 하나라도 일치하지 않는다면 정상적인 로그인이 아니라고 간주 (401)
		try {
			String token = request.getHeader("Authorization");
			if(token == null) throw new Exception("헤더 없음");//(1)
			
			if(tokenService.isBearerToken(token) == false)
				throw new Exception("Bearer 토큰이 아님");//(2)
			
			String realToken = tokenService.removeBearer(token);
			
			UserClaimVO claimVO = tokenService.check(realToken);//(3)
			
			// (3) - 1 여기서 USER_TYPE이 AIRLINE인지 확인
			if (!claimVO.getUserType().equals("AIRLINE")) {
				response.sendError(403, "권한이 없습니다."); // (3)-1 권한 부족 시 403 Forbidden 반환
			}
			return true;
		}
		catch(Exception e) {
			response.sendError(401);//(4)
			return false;
		}
	}
}






