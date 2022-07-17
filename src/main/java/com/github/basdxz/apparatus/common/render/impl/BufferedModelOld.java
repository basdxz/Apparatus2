package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IBufferedModelOld;
import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import lombok.*;
import lombok.experimental.*;

import java.nio.FloatBuffer;

@Data
@Accessors(fluent = true, chain = true)
public class BufferedModelOld implements IBufferedModelOld {
    protected final IRenderBuffer renderBuffer;
    protected final FloatBuffer floatBuffer;
}
