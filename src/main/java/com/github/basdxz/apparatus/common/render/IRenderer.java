package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.util.List;

public interface IRenderer {
    List<IRenderBufferID<?>> bufferIDs();

    void render(@NonNull IRenderContext context);
}
