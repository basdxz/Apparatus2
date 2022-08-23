package com.github.basdxz.apparatus.adapter;

import com.github.basdxz.apparatus.adapter.resource.impl.ResourceContainerHandlerAdapter;
import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.MinecraftForge;


public class ClientProxy extends CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
//        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(ResourceContainerHandlerAdapter.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ResourceContainerHandlerAdapter.INSTANCE);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ResourceContainerHandlerAdapter.INSTANCE.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        super.serverAboutToStart(event);
    }

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {
        super.serverStarting(event);
    }

    public void serverStarted(FMLServerStartedEvent event) {
        super.serverStarted(event);
    }

    public void serverStopping(FMLServerStoppingEvent event) {
        super.serverStopping(event);
    }

    public void serverStopped(FMLServerStoppedEvent event) {
        super.serverStopped(event);
    }
}