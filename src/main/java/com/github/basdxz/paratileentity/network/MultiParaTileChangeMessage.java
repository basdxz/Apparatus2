package com.github.basdxz.paratileentity.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import eu.usrv.yamcore.network.client.AbstractClientMessageHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minecraft.entity.player.EntityPlayer;

import static com.github.basdxz.paratileentity.network.MultiParaTileChange.bufferedMultiParaTileChange;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultiParaTileChangeMessage implements IMessage {
    private MultiParaTileChange message;

    @Override
    public void fromBytes(ByteBuf pBuffer) {
        message = pBuffer.<MultiParaTileChange>readSerializeable();
    }

    @Override
    public void toBytes(ByteBuf pBuffer) {
        pBuffer.writeSerializeable(message);
    }

    public static class ClientHandler extends AbstractClientMessageHandler<MultiParaTileChangeData> {
        @Override
        public IMessage handleClientMessage(EntityPlayer player, MultiParaTileChangeData message, MessageContext ctx) {
            bufferedMultiParaTileChange(message.getMessage());
            return null;
        }
    }

    @NoArgsConstructor
    public static class MultiParaTileChangeData extends MultiParaTileChangeMessage {
        public MultiParaTileChangeData(MultiParaTileChange multiParaTileChange) {
            super(multiParaTileChange);
        }
    }
}
