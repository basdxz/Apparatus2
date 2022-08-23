package com.github.basdxz.apparatus.adapter.mixin.mixins.client.minecraft;

import com.github.basdxz.apparatus.adapter.resource.impl.ResourceContainerHandlerAdapter;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SimpleReloadableResourceManager.class)
public abstract class SimpleReloadableResourceManagerResourceContainerHandlerAdapterMixin implements
                                                                                          IReloadableResourceManager {
    @Inject(method = "clearResources()V",
            at = @At(value = "HEAD"),
            require = 1)
    private void resetResources(CallbackInfo ci) {
        ResourceContainerHandlerAdapter.INSTANCE.resetResources();
    }

    @Inject(method = "notifyReloadListeners()V",
            at = @At(value = "HEAD"),
            require = 1)
    private void loadResources(CallbackInfo ci) {
        ResourceContainerHandlerAdapter.INSTANCE.loadProperties(this);
        ResourceContainerHandlerAdapter.INSTANCE.loadMeshes(this);
        ResourceContainerHandlerAdapter.INSTANCE.loadTextures(this);
    }

    @Inject(method = "notifyReloadListeners()V",
            at = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/ProgressManager;pop" +
                                                "(Lcpw/mods/fml/common/ProgressManager$ProgressBar;)V"),
            require = 1)
    private void ensureAllResourcesLoaded(CallbackInfo ci) {
        ResourceContainerHandlerAdapter.INSTANCE.ensureAllResourcesLoaded();
    }
}
