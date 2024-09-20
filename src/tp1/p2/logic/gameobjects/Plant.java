package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Plant extends GameObject{
	
	public boolean attackedbyChili;
	
	protected Plant() {
		
	}
	
	public Plant(GameWorld game, int col, int row, int lives, int cooldown) {
		super(game,col,row,lives,cooldown);
		this.attackedbyChili = false;
	}
	
	public Plant(GameWorld game, int col, int row, int lives) {
		super(game, col, row, lives);
	}
	
	protected abstract String getName();
	
	public abstract Plant copy(GameWorld game, int col, int row);
	
	public abstract int getCost();
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	public void zombieDied() {
		
	}
	
	@Override
	public void onEnter() {
	
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	
	@Override
	public void onExit() {
	
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		boolean damaged = false;
		if(isAlive()) {
			receiveDamaged(damage);
			damaged = true;
		}
		return damaged;
	}
	
	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
}
