package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.resource.IModel;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RendererAdapter implements IRendererAdapter {
    public final static RendererAdapter EMPTY_INSTANCE = new RendererAdapter();

    protected final List<ModelAdapter> modelAdapters;

    public RendererAdapter(@NonNull IRenderer renderer, @NonNull IIconRegister iconRegister) {
        modelAdapters = Collections.unmodifiableList(renderer.models().stream()
                .map((m) -> adapt(m, iconRegister))
                .collect(Collectors.toList()));
    }

    public static ModelAdapter adapt(@NonNull IModel model, @NonNull IIconRegister iconRegister) {
        if (model instanceof ISpriteModel)
            return new SpriteModelAdapter((ISpriteModel) model, iconRegister);
        return new ModelAdapter.EmptyModelAdapter();
    }

    protected RendererAdapter() {
        modelAdapters = Collections.emptyList();
    }

    @Override
    public void render() {
        modelAdapters.forEach(ModelAdapter::render);
    }
}
