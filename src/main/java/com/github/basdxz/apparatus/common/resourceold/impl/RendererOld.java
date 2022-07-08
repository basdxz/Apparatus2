package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resourceold.IModel;
import com.github.basdxz.apparatus.common.resourceold.IRendererOld;
import lombok.*;
import lombok.experimental.*;

import java.util.Collections;
import java.util.List;

import static com.github.basdxz.apparatus.common.resourceold.impl.BlockModel.newDefaultBlockModel;
import static com.github.basdxz.apparatus.common.resourceold.impl.SpriteModel.newDefaultSpriteModel;

@Data
@Accessors(fluent = true, chain = true)
public class RendererOld implements IRendererOld {
    protected final List<IModel> models;

    public RendererOld(@NonNull List<IModel> models) {
        this.models = Collections.unmodifiableList(models);
    }

    public static IRendererOld newDefaultSpriteRenderer(@NonNull IDomain domain, @NonNull String path) {
        return new RendererOld(Collections.singletonList(newDefaultSpriteModel(domain, path)));
    }

    public static IRendererOld newDefaultBlockRenderer(@NonNull IDomain domain, @NonNull String path) {
        return new RendererOld(Collections.singletonList(newDefaultBlockModel(domain, path)));
    }
}
