package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.resource.ITextureResource;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import static cpw.mods.fml.relauncher.Side.CLIENT;

@SideOnly(CLIENT)
@RequiredArgsConstructor
public class TextureResourceWrapper {
    @NonNull
    protected ITextureResource resource;
    @Getter
    protected IIcon icon;

    public void registerIcon(@NonNull IIconRegister register) {
        icon = register.registerIcon(resource.resourceDomain() + ":" + resource.resourceLocation());
    }
}
