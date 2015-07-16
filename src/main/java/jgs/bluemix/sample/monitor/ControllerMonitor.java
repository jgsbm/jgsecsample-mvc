package jgs.bluemix.sample.monitor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Controllerの呼び出しと終了をログ出力するAOPです.
 *
 * @author ryozo
 */
@Aspect
@Component
public class ControllerMonitor {

    private static final Logger logger = Logger.getLogger(ControllerMonitor.class);

    @Around("execution(* jgs..web.*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = MethodSignature.class.cast(point.getSignature());
        logger.info(String.format("Start Controller %s#%s(%s)",
                signature.getDeclaringTypeName(),
                signature.getMethod().getName(),
                point.getArgs()));
        Object result = point.proceed();
        long start = System.currentTimeMillis();
        logger.info(String.format("End Controller %s#%s(%s): %s in %smsec",
                signature.getDeclaringTypeName(),
                signature.getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start));

        return result;
    }

}
