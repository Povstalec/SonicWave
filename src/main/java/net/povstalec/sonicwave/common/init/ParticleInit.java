package net.povstalec.sonicwave.common.init;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.povstalec.sonicwave.SonicWave;

public class ParticleInit
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SonicWave.MODID);
	
	public static final RegistryObject<BasicParticleType> SONIC_WAVE = PARTICLES.register("sonic_wave", 
			() -> new BasicParticleType(true));
	
	public static void register(IEventBus eventBus)
	{
		PARTICLES.register(eventBus);
	}
}
