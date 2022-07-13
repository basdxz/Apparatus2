package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderer {
    IRendererInfo info();

    void render(@NonNull IRenderContext context);
}
