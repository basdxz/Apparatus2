package com.github.basdxz.paratileentity.network;

import eu.usrv.yamcore.network.PacketDispatcher;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

public class NetworkDispatcher extends PacketDispatcher {
    public static NetworkDispatcher INSTANCE;

    private NetworkDispatcher() {
        super(MODID);
        registerPackets();
    }

    public static void load() {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = new NetworkDispatcher();
    }

    @Override
    public void registerPackets() {
        registerMessage(MultiParaTileChangeMessage.ClientHandler.class, MultiParaTileChangeMessage.MultiParaTileChangeData.class);
    }
}
