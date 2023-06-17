package net.povstalec.sonicwave.common.init;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.povstalec.sonicwave.SonicWave;
import net.povstalec.sonicwave.client.particles.SonicWaveParticle;
import net.povstalec.sonicwave.common.event.SonicWaveEvent;

@Mod.EventBusSubscriber(modid = SonicWave.MODID, value = Dist.CLIENT)
public class EventInit
{
    @SubscribeEvent
    public static void onSonicWave(SonicWaveEvent event)
    {
    	PlayerEntity player = event.player;
    	World level = event.player.level;
    	
    	AxisAlignedBB aabb = player.getBoundingBox().inflate(6.0);
		
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
    	
    	SonicWaveParticle.spawnParticlesInSphere(level, player.getX(), player.getY(), player.getZ(), 2.0);
    }
}
