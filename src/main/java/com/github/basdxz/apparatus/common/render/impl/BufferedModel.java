package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IBufferedModel;
import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import lombok.*;
import lombok.experimental.*;

import java.nio.FloatBuffer;

@Data
@Accessors(fluent = true, chain = true)
public class BufferedModel implements IBufferedModel {
    protected final IRenderBuffer renderBuffer;
    protected final FloatBuffer floatBuffer;
}
