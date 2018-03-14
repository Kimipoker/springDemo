package first;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @program: springDemo
 * @description: 切面
 * @author: Mr.Wang
 * @create: 2018-03-11 00:41
 **/
@Aspect
public class Audience {
    @Pointcut("execution(* first.Performance.perform(..))")
    public void perform(){ }

    /**
     * 也可@Pointcut("execution(* first.Performance.perform(..))")
     */
    @Before("perform()")
    public void silenceCellPhone(){
        System.out.println("set cellphone silence");
    }
    @Before("perform()")
    public void takeSeats(){
        System.out.println("take seates");
    }
    @AfterReturning("perform()")
    public void applause(){
        System.out.println("clap clap clap!");
    }
    @AfterThrowing("perform()")
    public void demandRefund(){
        System.out.println("demanding a refund");
    }

    /**
     * 对于统计表演时间这种需求比较有用  使用前后通知,考虑要引入变量  单例有限制
     * @param pj
     */
    @Around("perform()")
    public void watchPerform(ProceedingJoinPoint pj){
        try {
            System.out.println("set cellphone silence");
            System.out.println("take seates");
            //这里可以不调用方法实现对方法的拦截  也可调用多次
            pj.proceed();
            System.out.println("clap clap clap!");
        } catch (Throwable throwable) {
            System.out.println("demanding a refund");
        }
    }
}
