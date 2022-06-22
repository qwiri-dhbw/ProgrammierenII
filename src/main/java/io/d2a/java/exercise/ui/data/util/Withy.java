package io.d2a.java.exercise.ui.data.util;

import io.d2a.java.exercise.ui.data.util.builder.ComponentBuilder;
import java.awt.Component;

public interface Withy<T> {

    T with(final Component component);
    T with(final ComponentBuilder<?> builder);

    T all (final Component ... components);

}
