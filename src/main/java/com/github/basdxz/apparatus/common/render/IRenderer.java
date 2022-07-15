package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: this should be provied when given a *VIEW*, eg needs a one to many wrapped eg IEntityRenderer
public interface IRenderer {
    IRendererInfo info();

    void render(@NonNull IRenderContext context);
}
