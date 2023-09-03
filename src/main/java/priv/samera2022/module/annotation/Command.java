package priv.samera2022.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Command {
        String name() default "default";
        boolean delete() default true;//是否需要在执行之后删除原本的指令文本
        boolean hasTextOutput() default false;//是否有文本输出
}
