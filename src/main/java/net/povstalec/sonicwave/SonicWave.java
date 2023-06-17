package net.povstalec.sonicwave;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.povstalec.sonicwave.client.particles.SonicWaveParticle;
import net.povstalec.sonicwave.common.init.KeyInit;
import net.povstalec.sonicwave.common.init.PacketHandlerInit;
import net.povstalec.sonicwave.common.init.ParticleInit;

@Mod(SonicWave.MODID)
public class SonicWave
{
    public static final String MODID = "sonicwave";
    
    public static final Logger LOGGER = LogManager.getLogger();

    public SonicWave()
    {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	
        eventBus.addListener(this::commonSetup);
        
		MinecraftForge.EVENT_BUS.register(this);
    	ParticleInit.register(eventBus);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    	event.enqueueWork(() -> 
    	{
    		PacketHandlerInit.register();
    	});
    }
    
    @Mod.EventBusSubscriber(modid = SonicWave.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
    	@SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        	KeyInit.register();
        }
        
    	@SubscribeEvent
    	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
    	{
    		Minecraft minecraft = Minecraft.getInstance();
    		minecraft.particleEngine.register(ParticleInit.SONIC_WAVE.get(), SonicWaveParticle.Factory::new);
    	}
    }
    
}
