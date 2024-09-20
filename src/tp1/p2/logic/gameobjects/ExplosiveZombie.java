package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie{
	
	private static final int ENDURANCE = 5;
	private static final int DAMAGE = 1;
	private static final int SPEED = 2;
	private static final int EXPLOSION_DAMAGE = 3;
	
	public ExplosiveZombie() {
		
	}

	public ExplosiveZombie(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE, 2);
	}
	
	@Override 
	public void update() {  //super.update();
		boolean attacked = false;
		super.update();
        if(isAlive() && game.isFullyOcuppied(this.col -1, this.row)) {
           attacked = game.attackPlant(col-1, row, DAMAGE);
           this.cooldown = SPEED -1;
        }
	}
	
	@Override
	protected String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}

	@Override
	public Zombie copy(GameWorld game, int col, int row) {
		ExplosiveZombie ex = new ExplosiveZombie(game, col, row);
		return ex;
	}

	@Override
	protected int getSpeed() {
		return ExplosiveZombie.SPEED;
	}

	@Override
	protected int getEndurance() {
		return ExplosiveZombie.ENDURANCE;
	}

	@Override
	protected int getDamage() {
		return ExplosiveZombie.DAMAGE;
	}

	@Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(),SPEED, DAMAGE, ENDURANCE);	
	}

	@Override
	public boolean fillPosition() {
		return true;
	}
	@Override
	public void delayedAction() {
		if(!game.isFullyOcuppied(this.col -1, this.row) && this.col != -1) {
            this.col--;
            this.cooldown = SPEED-1;
        }
	}
	@Override
	public void onExit() {
		ExplosionAction explosion = new ExplosionAction(this.col, this.row, EXPLOSION_DAMAGE, false, true);
		game.pushAction(explosion);
		super.onExit();
	}
}
