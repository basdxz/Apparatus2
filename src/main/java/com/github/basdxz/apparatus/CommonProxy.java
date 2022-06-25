package com.github.basdxz.apparatus;

import com.github.basdxz.apparatus.common.domain.impl.Domain;
import com.github.basdxz.apparatus.common.registry.impl.ParaRegistry;
import com.github.basdxz.apparatus.tiger.RegistryAdapter;
import cpw.mods.fml.common.event.*;
import lombok.*;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    @SneakyThrows
    public void preInit(FMLPreInitializationEvent event) {
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());

        val domain = new Domain("apparatus");
        val registry = new ParaRegistry(
                domain,
                "test_registry",
                "com.github.basdxz.apparatus.common.parathing.impl"
        );

        val registryAdapter = new RegistryAdapter(registry);
        registryAdapter.preInit();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {

    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {

    }

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public void serverStarted(FMLServerStartedEvent event) {

    }

    public void serverStopping(FMLServerStoppingEvent event) {

    }

    public void serverStopped(FMLServerStoppedEvent event) {

    }
}
