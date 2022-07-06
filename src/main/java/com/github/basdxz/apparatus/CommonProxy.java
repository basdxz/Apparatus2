package com.github.basdxz.apparatus;

import com.github.basdxz.apparatus.common.IInitializeable;
import com.github.basdxz.apparatus.common.domain.impl.Domain;
import com.github.basdxz.apparatus.tiger.DomainAdapter;
import cpw.mods.fml.common.event.*;
import lombok.*;

public class CommonProxy {
    IInitializeable registryAdapter;

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    @SneakyThrows
    public void preInit(FMLPreInitializationEvent event) {
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());

        registryAdapter = new DomainAdapter(Domain.get("apparatus"), "com.github.basdxz.apparatus.common.entity.impl");
        registryAdapter.preInit();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        registryAdapter.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        registryAdapter.postInit();
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
