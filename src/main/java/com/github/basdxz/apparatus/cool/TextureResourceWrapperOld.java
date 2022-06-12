package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.resource.IResource;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import static cpw.mods.fml.relauncher.Side.CLIENT;

@SideOnly(CLIENT)
@RequiredArgsConstructor
public class TextureResourceWrapperOld {
    @NonNull
    protected IResource IResource;
    @Getter
    protected IIcon icon;

    public void registerIcon(@NonNull IIconRegister register) {
        icon = register.registerIcon(IResource.domain() + ":" + IResource.location());
    }
}
