package aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import pbean.UserInfo;

@Aspect
public class AuthorizeAspect {
	
	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
	public void handlerMethod(){}
	
	@Before("handlerMethod()")
	public void interceptMethod(JoinPoint jp) throws Exception {
		MethodSignature ms = (MethodSignature)jp.getSignature();
		
		Method me = ms.getMethod();
		
		//메서드에서 해당어노테이션 취득
		Authrorize au = me.getAnnotation(Authrorize.class);
		AuthrorizeAdmin aua = me.getAnnotation(AuthrorizeAdmin.class);
		
		
		if (au != null && aua != null ) {
			//권한 체크가 필요
			RequestAttributes ra = 
					RequestContextHolder.getRequestAttributes();
			
			UserInfo loginUser = (UserInfo)
					ra.getAttribute("user", RequestAttributes.SCOPE_SESSION);
			
			
			
			if (loginUser == null || !(loginUser.getRole().equals("admin"))) {
				//로그인 하지않고 접근한 경우 처리
				System.out.println("로그인 하십시오");
				throw new InvalidLoginException();
			}else if(loginUser.getRole().equals("admin")){
				ra.setAttribute("role","관리자 모드",RequestAttributes.SCOPE_SESSION);
			}
			
			else {
				//로그인 후 접근한 경우 처리
				System.out.println("로그인 감사합니다");
			}
		}
		
		
		
		
	}
}










