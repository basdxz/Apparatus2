package com.github.basdxz.apparatus;

import com.github.basdxz.apparatus.adapter.registry.impl.RegistryAdapter;
import com.github.basdxz.apparatus.common.domain.impl.DomainRegistry;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import cpw.mods.fml.common.event.*;
import lombok.*;

public class CommonProxy {
    protected IDomainLoader domainLoader;

    public void preInit(FMLPreInitializationEvent event) {
        DomainRegistry.INSTANCE.add(RegistryAdapter.INSTANCE);

        val domain = DomainRegistry.INSTANCE.getDomain("apparatus");
        domain.addLoaderPackages("com.github.basdxz.apparatus.common.entity.impl");

        domainLoader = domain.newLoader();
        domainLoader.preInit();
    }

    public void init(FMLInitializationEvent event) {
        domainLoader.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        domainLoader.postInit();
        domainLoader = null;
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
