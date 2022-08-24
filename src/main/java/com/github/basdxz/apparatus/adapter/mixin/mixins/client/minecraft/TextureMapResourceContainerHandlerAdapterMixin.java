package com.github.basdxz.apparatus.adapter.mixin.mixins.client.minecraft;

import com.github.basdxz.apparatus.adapter.resource.impl.ResourceContainerHandlerAdapter;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(TextureMap.class)
public abstract class TextureMapResourceContainerHandlerAdapterMixin
        extends AbstractTexture implements ITickableTextureObject, IIconRegister {
    private static final int ITEM_ATLAS_TEXTURE_TYPE = 1;

    @Shadow
    @Final
    private int textureType;

    @Inject(method = "registerIcons()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;registerDestroyBlockIcons" +
                                                "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V"),
            require = 1)
    private void loadBlockIcons(CallbackInfo ci) {
        ResourceContainerHandlerAdapter.INSTANCE.loadBlockIcons(thiz());
    }

    @Inject(method = "registerIcons()V",
            at = @At(value = "RETURN"),
            require = 1)
    private void loadItemIcons(CallbackInfo ci) {
        if (isItemAtlas())
            ResourceContainerHandlerAdapter.INSTANCE.loadItemIcons(thiz());
    }

    private boolean isItemAtlas() {
        return textureType == ITEM_ATLAS_TEXTURE_TYPE;
    }

    private TextureMap thiz() {
        return (TextureMap) ((Object) this);
    }
}
