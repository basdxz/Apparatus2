package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import lombok.experimental.*;

import java.util.Locale;

@Getter
@Accessors(fluent = true, chain = true)
public enum RenderBufferLayout implements IRenderBufferLayout {
    MODEL_BUFFER_LAYOUT;

    private final String renderBufferLayoutName;

    RenderBufferLayout() {
        renderBufferLayoutName = name().toLowerCase(Locale.US).intern();
    }
}
