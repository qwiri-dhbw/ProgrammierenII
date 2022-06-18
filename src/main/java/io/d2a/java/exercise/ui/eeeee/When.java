package io.d2a.java.exercise.ui.eeeee;

import java.awt.event.KeyAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Consumer;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface When {

    static enum What {
        CLICK;
    }

    What[] we();

    String then();

}
