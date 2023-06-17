package net.povstalec.sonicwave.common.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.povstalec.sonicwave.SonicWave;
import net.povstalec.sonicwave.common.init.KeyInit;
import net.povstalec.sonicwave.common.init.PacketHandlerInit;
import net.povstalec.sonicwave.common.packets.ServerboundSonicWaveUpdatePacket;

@Mod.EventBusSubscriber(modid = SonicWave.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents
{
	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event)
	{
		Minecraft minecraft = Minecraft.getInstance();
		if(minecraft.level == null)
			return;
		
		if(minecraft.screen == null && KeyInit.key.isDown())
		{
			PacketHandlerInit.INSTANCE.sendToServer(new ServerboundSonicWaveUpdatePacket());
		}
	}
}
