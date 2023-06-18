package net.povstalec.sonicwave.common.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.povstalec.sonicwave.SonicWave;
import net.povstalec.sonicwave.client.particles.SonicWaveParticle;
import net.povstalec.sonicwave.common.event.SonicWaveEvent;

@Mod.EventBusSubscriber(modid = SonicWave.MODID, value = Dist.CLIENT)
public class EventInit
{
	private static Map<PlayerEntity, Integer> playerTimes = new HashMap<PlayerEntity, Integer>();
	
    @SubscribeEvent
    public static void onSonicWave(SonicWaveEvent event)
    {
    	PlayerEntity player = event.player;
    	
    	doSonicWave(player, 2.0);
    	
    	playerTimes.put(player, 0);
    }
    
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
    	PlayerEntity player = event.player;

    	if(event.phase == TickEvent.Phase.END && playerTimes.containsKey(player))
    	{
    		int ticks = playerTimes.get(player);
    		ticks++;
    		playerTimes.replace(player, ticks);
    		
    		if(ticks >= 20)
    		{
    			doSonicWave(player, 3.0);
    			
    			playerTimes.remove(player);
    		}
    	}
    }
    
    private static void doSonicWave(PlayerEntity player, double size)
    {
    	World level = player.level;
    	
    	AxisAlignedBB aabb = player.getBoundingBox().inflate(3 * size);
		
		List<Entity> entities = level.getEntitiesOfClass(Entity.class, aabb);
		
		entities.stream().forEach(entity ->
		{
			Vector3d vector = entity.getBoundingBox().getCenter();
			vector = vector.subtract(player.getBoundingBox().getCenter());
			
			vector.vectorTo(vector);
			vector.scale(1 / vector.length());
			
			double scale = 5 - vector.length();
			scale = scale < 0 ? 0 : scale;
			
			vector.scale(scale);
			
			System.out.println(scale);
			
			entity.push(vector.x(), vector.y(), vector.z());
			entity.hurt(DamageSource.GENERIC, Math.round(scale));
		});
    	
		if(!level.isClientSide())
			SonicWaveParticle.spawnParticlesInSphere(level, player.getX(), player.getY(), player.getZ(), size);
    }
}
