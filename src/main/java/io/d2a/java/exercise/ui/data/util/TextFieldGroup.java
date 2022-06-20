package io.d2a.java.exercise.ui.data.util;

import io.d2a.java.exercise.ui.data.util.builder.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFieldGroup {

    private final List<TextField> fields = new ArrayList<>();

    public TextFieldGroup(final TextField ... fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    public void setText(final String newText) {
        for (final TextField field : this.fields) {
            field.setText(newText);
        }
    }

    public void clear() {
        this.setText("");
    }

}
