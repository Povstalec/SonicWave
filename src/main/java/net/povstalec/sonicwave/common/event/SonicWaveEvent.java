package net.povstalec.sonicwave.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class SonicWaveEvent extends Event
{
	public PlayerEntity player;
	
	public SonicWaveEvent(PlayerEntity player)
	{
		this.player = player;
	}
}
