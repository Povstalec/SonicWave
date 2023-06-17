package net.povstalec.sonicwave.common.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.povstalec.sonicwave.SonicWave;
import net.povstalec.sonicwave.common.packets.ClientboundSonicWaveUpdatePacket;
import net.povstalec.sonicwave.common.packets.ServerboundSonicWaveUpdatePacket;

public final class PacketHandlerInit
{
	private static final String PROTOCOL_VERSION = "1";
	
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SonicWave.MODID, "main_network"), 
			() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	
	private PacketHandlerInit(){}
	
	public static void register()
	{
		int index = 0;
		
		//============================================================================================
		//****************************************Client-bound****************************************
		//============================================================================================
		
		INSTANCE.messageBuilder(ClientboundSonicWaveUpdatePacket.class, index++, NetworkDirection.LOGIN_TO_CLIENT)
		.encoder(ClientboundSonicWaveUpdatePacket::encode)
		.decoder(ClientboundSonicWaveUpdatePacket::new)
		.consumer(ClientboundSonicWaveUpdatePacket::handle)
		.add();
		
		//============================================================================================
		//****************************************Server-bound****************************************
		//============================================================================================
		
		INSTANCE.messageBuilder(ServerboundSonicWaveUpdatePacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
		.encoder(ServerboundSonicWaveUpdatePacket::encode)
		.decoder(ServerboundSonicWaveUpdatePacket::new)
		.consumer(ServerboundSonicWaveUpdatePacket::handle)
		.add();
	}
}
