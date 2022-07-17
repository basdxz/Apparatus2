package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.util.List;

//TODO: this should be provied when given a *VIEW*, eg needs a one to many wrapped eg IEntityRenderer
public interface IRenderer {
    List<IRenderBufferID<?>> bufferIDs();

    void render(@NonNull IRenderContext context);
}
