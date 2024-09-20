package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Zombie extends GameObject{
	
	private boolean damagedByExpl;
	
	protected Zombie() {
		
	}
	
	public Zombie(GameWorld game, int col, int row, int lives, int cooldown) {
		super(game,col,row,lives,cooldown);
		this.damagedByExpl = false;
	}
		
	protected abstract String getName();
	
	public abstract Zombie copy(GameWorld game, int col, int row);
		
	protected abstract int getSpeed();
	
	protected abstract int getEndurance();
	
	protected abstract int getDamage();
	
	
	@Override
	public void onEnter() {
	
	}
	
	@Override
	public void onExit() {
		int points = 10;
		game.zombieDied();
		if(damagedByExpl == true) {
			points = 20;
		}
		game.addScore(points);
	}
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	@Override
	public boolean catchObject() {
		
		return false;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}
	
	@Override
	public boolean receivePlantAttack(int damage) {
		boolean damaged = false;
		if(isAlive()) {
			receiveDamaged(damage);
			if(damage == 10) {
				damagedByExpl = true;
			}
			damaged = true;
		}
		return damaged;
	}

}
