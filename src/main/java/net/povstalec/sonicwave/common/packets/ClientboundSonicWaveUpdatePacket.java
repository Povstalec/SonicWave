package net.povstalec.sonicwave.common.packets;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.povstalec.sonicwave.client.ClientAccess;

public class ClientboundSonicWaveUpdatePacket
{
	private double x;
	private double y;
	private double z;
	
    public ClientboundSonicWaveUpdatePacket(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ClientboundSonicWaveUpdatePacket(PacketBuffer buffer)
    {
        this(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
        	ClientAccess.sonicWave(x, y, z);
        });
        return true;
    }
}


