package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie{
	
	private static final int ENDURANCE = 8;
	private static final int DAMAGE = 1;
	private static final int SPEED = 4;
	
	public BucketHead() {
		
	}
	
	public BucketHead(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE, 4);
	}

	@Override
	protected String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	public Zombie copy(GameWorld game, int col, int row) {
		BucketHead b = new BucketHead(game,col, row);
		return b;
	}

	@Override
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), SPEED, DAMAGE, ENDURANCE);
	}
	
	@Override
	public void update() {
		boolean attacked = false;
        super.update();
        if(isAlive() && game.isFullyOcuppied(this.col -1, this.row)){
           attacked = game.attackPlant(col-1, row, DAMAGE);
           this.cooldown = SPEED -1;
        }
	}
	
	@Override
	public void delayedAction() {
		if(!game.isFullyOcuppied(this.col -1, this.row) && this.col != -1) {
            this.col--;
            this.cooldown = SPEED-1;
        }
	}
	
	@Override
	protected int getSpeed() {
		return BucketHead.SPEED;
	}

	@Override
	protected int getEndurance() {
		return BucketHead.ENDURANCE;
	}

	@Override
	protected int getDamage() {
		return BucketHead.DAMAGE;
	}

}
