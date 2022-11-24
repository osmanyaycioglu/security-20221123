package com.training.security.security.rest.validatiton;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {ContainsStrImpl.class })
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ContainsStr {

    String[] value();

    String message() default "String içinde istenilmeyen bilgi var.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
