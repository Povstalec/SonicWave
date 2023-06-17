package net.povstalec.sonicwave.common.init;

import java.awt.event.KeyEvent;

import net.minecraft.client.settings.KeyBinding;
import net.povstalec.sonicwave.SonicWave;

public class KeyInit
{
	public static KeyBinding key;
	
	private static KeyBinding create(String name, int key)
	{
		return new KeyBinding("key." + SonicWave.MODID + "." + name, key, "key.category." + SonicWave.MODID);
	}
	
	public static void register()
	{
		key = create("do_sonic_wave", KeyEvent.VK_G);
	}
}
