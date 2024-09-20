package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{
	
	private static final int ENDURANCE = 2;
	private static final int DAMAGE = 1;
	private static final int SPEED = 1;
	
	public Sporty() {
		
	}
	
	public Sporty(GameWorld game, int col, int row, int lives, int cooldown) {
		super(game, col, row, lives, 1);
	}

	@Override
	protected String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
	}

	@Override
	public Zombie copy(GameWorld game, int col, int row) {
		Sporty s = new Sporty(game,col, row, ENDURANCE, cooldown);
		return s;
	}

	@Override
	protected String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), SPEED, DAMAGE, ENDURANCE);
	}

	@Override
	public void update() {
		boolean attacked = false;
		super.update();
        if(isAlive() && game.isFullyOcuppied(this.col -1, this.row)) {
           attacked = game.attackPlant(col-1, row, DAMAGE);
           this.cooldown = SPEED -1;
        }
	}
	
	@Override 
	public void delayedAction() {
		if(!game.isFullyOcuppied(this.col -1, this.row) && this.col != -1) {
            this.col--;
        }
	}
	
	@Override
	protected int getSpeed() {
		return Sporty.SPEED;
	}

	@Override
	protected int getEndurance() {
		return Sporty.ENDURANCE;
	}

	@Override
	protected int getDamage() {
		return Sporty.DAMAGE;
	}

}
