package com.example.demo.config.annotation;

        import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface useDataSource {
    String name() default "";
}
