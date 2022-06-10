package com.github.basdxz.apparatus.common.parathing;

import com.github.basdxz.apparatus.common.popoga.IRenderView;
import lombok.*;

public interface ParaItemRender {
    Iterable<?> itemModels(@NonNull IRenderView view);
}
