package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieCommon extends Zombie {
	
	private static final int ENDURANCE = 5;
	private static final int DAMAGE = 1;
	private static final int SPEED = 2;
	
	public ZombieCommon() {
		
	}
	
	public ZombieCommon(GameWorld game, int col, int row)	{
		super(game, col, row, ENDURANCE, 2); 
	}
	
	@Override
	public void update() {
		boolean attacked = false;
		super.update();
        if(isAlive() && game.isFullyOcuppied(this.col -1, this.row)) {
        	attacked = game.attackPlant(col-1, row, DAMAGE);
        	this.cooldown = SPEED-1;
        }
	}
	
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}
	
	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(),SPEED, DAMAGE, ENDURANCE);	
	}
	
	public Zombie copy(GameWorld game, int col, int row) {
		Zombie z = new ZombieCommon(game, col, row);
		return z;
	}
	@Override
	public void delayedAction() {
		 if(!game.isFullyOcuppied(this.col -1, this.row) && this.col != -1) {
             this.col--;
             this.cooldown = SPEED-1;
         }
	}
	
	protected int getSpeed() {
		return ZombieCommon.SPEED;
	}
	
	protected int getDamage() {
		return ZombieCommon.DAMAGE;
	}
	
	protected int getEndurance() {
		return ZombieCommon.ENDURANCE;
	}

}
