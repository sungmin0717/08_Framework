package edu.kh.project.common.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 *  자주 사용하는 Pointcut을 모아두는 클래스
 *  - 작성하기 힘든 Pointcut을 작성해서 변수 저장/호출해서 사용
 *  
 *  [작성법]
 *  
 *  
 *  (선어)
 *  @Pointcut("포인트컷 구문")
 *  public void 메서드명 (){}
 *  
 *  
 *  (호출)
 *  @Before("PointcutBundle.메서드명()")
 *  
 *  
 */
public class PointcutBundle {

	
	//모든 컨트롤러 지정
	@Pointcut("execution(* edu.kh.project..*Controller*.*(..))")
	public void controllerPointcut() {}
	
	//모든 ServiceImpl
	@Pointcut("execution(* edu.kh.project..*ServiceImpl*.*(..))")
	public void serviceImplPointcut() {
}
}
