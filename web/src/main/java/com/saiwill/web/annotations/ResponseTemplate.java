package com.saiwill.web.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface ResponseTemplate {
    //模板路径
    String value();
}
