package net.povstalec.sonicwave.client;

import net.minecraft.client.Minecraft;
import net.povstalec.sonicwave.client.particles.SonicWaveParticle;

public class ClientAccess
{
	protected static Minecraft minecraft = Minecraft.getInstance();
	
    public static void sonicWave(double x, double y, double z)
    {
    	SonicWaveParticle.spawnParticlesInSphere(minecraft.level, x, y, z, 2.0);
    }
}
