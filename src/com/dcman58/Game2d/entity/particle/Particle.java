package com.dcman58.Game2d.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.dcman58.Game2d.Graphics.Screen;
import com.dcman58.Game2d.Graphics.Sprite;
import com.dcman58.Game2d.entity.Entity;

public class Particle extends Entity {

	private List<Particle> particles = new ArrayList<Particle>();

	private int life;

	private int time;

	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.life = life + (random.nextInt(20) - 10);
		this.xx = x;
		this.yy = y;
		sprite = Sprite.particles_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);
		for (int i = 0; i < amount - 1; i++) {
			//particles.add(new Particle(x, y, life));
		}
		//particles.add(this);
	}

	public void update() {
		time++;
		if (time >= 7400 - 10)
			time = 0;
		if (time > life)
			remove();
		za -= 0.1;
		if (zz < 0) {
			zz = 0;
			za *= -0.8;
			xa *= 0.57;
			ya *= 0.2;
		}
		move((xx + xa), (yy + ya) + (zz + za));

	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0)
				ix = (int) Math.floor(xt);
			if (c / 2 == 0)
				iy = (int) Math.floor(yt);

			if (level.getTile(ix, iy).solid())
				solid = true;
		}

		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}