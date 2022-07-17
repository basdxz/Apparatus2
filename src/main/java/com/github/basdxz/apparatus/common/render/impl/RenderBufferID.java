package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferID;
import com.github.basdxz.apparatus.common.render.IRenderBufferInfo;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class RenderBufferID<LAYOUT extends IRenderBufferLayout> implements IRenderBufferID<LAYOUT> {
    protected final IRenderBufferInfo<LAYOUT> bufferInfo;
    protected final String bufferName;

    //TODO: Done to keep this unique by reference
    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    //TODO: Done to keep this unique by reference
    @Override
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }
}
