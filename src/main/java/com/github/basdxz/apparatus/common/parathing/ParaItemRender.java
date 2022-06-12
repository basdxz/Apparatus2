package com.github.basdxz.apparatus.common.parathing;

import com.github.basdxz.apparatus.common.render.IRenderView;
import lombok.*;

public interface ParaItemRender {
    Iterable<?> itemModels(@NonNull IRenderView view);
}
