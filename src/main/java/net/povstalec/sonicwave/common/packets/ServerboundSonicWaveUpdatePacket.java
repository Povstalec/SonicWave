package net.povstalec.sonicwave.common.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.povstalec.sonicwave.common.event.SonicWaveEvent;

public class ServerboundSonicWaveUpdatePacket
{
    public ServerboundSonicWaveUpdatePacket() {}

    public ServerboundSonicWaveUpdatePacket(PacketBuffer buffer) {}

    public void encode(PacketBuffer buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> ctx)
    {
    	Context context = ctx.get();
    	context.enqueueWork(() ->
    	{
    		ServerPlayerEntity player = context.getSender();
    			
    		SonicWaveEvent event = new SonicWaveEvent(player);
    		MinecraftForge.EVENT_BUS.post(event);
    	});
        return true;
    }
}


