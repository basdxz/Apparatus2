package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IVertex;
import lombok.*;
import lombok.experimental.*;
import org.joml.*;

@Data
@Accessors(fluent = true, chain = true)
public class Vertex implements IVertex {
    protected final Vector3fc position;
    protected final Vector3fc normal;
    protected final Vector4fc color;
    protected final Vector2fc texture;

    public Vertex(@NonNull Vector3fc position,
                  @NonNull Vector3fc normal,
                  @NonNull Vector4fc color,
                  @NonNull Vector2fc texture) {
        this.position = new Vector3f(position);
        this.normal = new Vector3f(normal);
        this.color = new Vector4f(color);
        this.texture = new Vector2f(texture);
    }
}
