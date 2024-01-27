package priv.samera2022.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Command {
        //原本是设想用@Command注解里面的内容来解决该指令的一些特性的，比方说在执行完之后是否需要留下执行时的命令文本，
        //但是后来发现，其实在调用方法的时候再填入更方便一些。
        String name() default "default";
        boolean delete() default true;//是否需要在执行之后删除原本的指令文本
        boolean hasTextOutput() default false;//是否有文本输出
}
