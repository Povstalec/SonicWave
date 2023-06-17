package net.povstalec.sonicwave.client.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.povstalec.sonicwave.common.init.ParticleInit;

public class SonicWaveParticle extends SpriteTexturedParticle
{

	protected SonicWaveParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
			IAnimatedSprite spriteSet, double xd, double yd, double zd)
	{
		super(level, xCoord, yCoord, zCoord, xd, yd, zd);
		
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.quadSize *= 4.0F;
		this.lifetime = 40;
		this.setSpriteFromAge(spriteSet);
		
		this.rCol = 1.0F;
		this.gCol = 1.0F;
		this.bCol = 1.0F;
	}

	@Override
	public IParticleRenderType getRenderType()
	{
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		this.alpha = - (1 / (float) lifetime) * age + 1;
	}
	
	public static void spawnParticlesInSphere(World level, double x, double y, double z, double scale)
	{
		double phi = Math.PI * (Math.sqrt(5) - 1);
		
		int numberOfParticles = 1800;
		
		for(int i = 0; i < numberOfParticles; i++)
		{
			double theta = phi * i;
			double ySpeed = 1 - (i / (float) (numberOfParticles - 1)) * 2;
			double radius = Math.sqrt(1 - ySpeed * ySpeed);
			
			double xSpeed = Math.cos(theta) * radius;
			double zSpeed = Math.sin(theta) * radius;
			
			((ServerWorld) level).sendParticles(ParticleInit.SONIC_WAVE.get(), x, y, z, 0, xSpeed * scale, ySpeed * scale, zSpeed * scale, 1);
		}
	}
	
	public static class Factory implements IParticleFactory<BasicParticleType>
	{
		private final IAnimatedSprite sprites;
		
		public Factory(IAnimatedSprite spriteSet)
		{
			this.sprites = spriteSet;
		}
		
		public Particle createParticle(BasicParticleType particleType, ClientWorld level,
				double x, double y, double z, double dx, double dy, double dz)
		{
			SonicWaveParticle particle = new SonicWaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
			particle.pickSprite(this.sprites);
			return particle;
		}
	}
}
