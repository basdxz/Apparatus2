package com.github.basdxz.apparatus;

import com.github.basdxz.apparatus.common.domain.impl.Domain;
import com.github.basdxz.apparatus.common.registry.impl.ParaRegistry;
import cpw.mods.fml.common.event.*;
import lombok.*;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    @SneakyThrows
    public void preInit(FMLPreInitializationEvent event) {
//        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());
//        Apparatus.info(Config.greeting);
//        Apparatus.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
        //        DumbassManagerOld.register(new ParaItem("cool_thing_id", "Cool Thing!"));
//        Class<?> cl = Class.forName("com.github.basdxz.apparatus.common.parathing.impl.ParaItemLoader");
        // Call no-args constructor
//        ILoader<IParaThing> newInstance = (ILoader<IParaThing>) cl.newInstance();


        val domain = new Domain("apparatus");
        val registry = new ParaRegistry(
                domain,
                "test_registry",
                "com.github.basdxz.apparatus.common.parathing.impl"
        );

        registry.preInit();

        for (val paraThing : registry.paraThings()) {
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
            System.out.println(paraThing);
        }
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
