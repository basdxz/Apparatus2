package com.github.basdxz.apparatus.common.parathing;


import com.github.basdxz.apparatus.common.popoga.IRenderView;
import lombok.*;

public interface ParaItem extends ParaThing, ParaItemRender {
    @Override
    default Iterable<?> itemModels(@NonNull IRenderView view) {
        //TODO: REMOVEE
        return null;
    }
}
