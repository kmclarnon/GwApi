package us.gameandwatching.gwapi.service.auth.stash;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@BindingAnnotation
@Target({ ElementType.PARAMETER, ElementType.METHOD })
public @interface Stashed {
}
