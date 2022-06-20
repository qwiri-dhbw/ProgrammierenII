package io.d2a.java.exercise.ui.data.util;

import io.d2a.java.exercise.ui.data.util.presets.Geritt;
import java.awt.Component;

public interface Withy<T> {

    T with(final Component component);
    T with(final Geritt geritt);

    T all (final Component ... components);

}
