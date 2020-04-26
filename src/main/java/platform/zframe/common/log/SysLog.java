package platform.zframe.common.log;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author zhangyantao
 * @date 2017年3月8日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
