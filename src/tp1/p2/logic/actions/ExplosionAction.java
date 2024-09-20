package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	private boolean attackZombies;
	
	private boolean killedByChili;

	public ExplosionAction(int col, int row, int damage) {
		this.col = col;
		this.row = row;
		this.damage = damage;
	}

	public ExplosionAction(int col, int row, int damage, boolean attackZombies, boolean killedByChili) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.attackZombies = attackZombies;
		this.killedByChili = killedByChili;
	}
	
	@Override
	public void execute(GameWorld game) {
		boolean attacked;
		if(killedByChili)
		{
		for(int i = col-1; i<= col+1; i++){
            for(int j = row-1; j<= row+1; j++){
                if(i == col && j ==row){
                	attacked = false;
                }
                else {
                	if(attackZombies) {
                		attacked = game.attackZombie(i, j, damage);
                	}
                	else {
                		attacked = game.attackPlant(i, j, damage);
                	}
                }
            }
        }
		}
	}

}
