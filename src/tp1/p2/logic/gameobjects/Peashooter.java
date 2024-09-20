package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Shooter{
	
	private static final int COST = 50;
	private static final int ENDURANCE = 3;
	private static final int DAMAGE = 1;
	
	public Peashooter() {
		
	}

	public Peashooter(GameWorld game, int col, int row) {
		super(game, col, row, DAMAGE, ENDURANCE, COST);
	}
	
	@Override
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.PEASHOOTER_NAME_SHORTCUT,COST,DAMAGE, ENDURANCE);
	}
	
	@Override
	protected String getName() {
		return Messages.PEASHOOTER_NAME;
	}
	
	@Override
	public int getCost() {
		return Peashooter.COST;
	}
	
	/*@Override
	public void update() {
		boolean attacked = false;
		int i = this.col+1;
		if(isAlive()){		//Le pide al game que lo ataque
			while(i < GameWorld.NUM_COLS && !attacked) {
				attacked = game.attackZombie(i, this.row, Peashooter.DAMAGE);
				if(!attacked) {
					i++;
				}
			}
		}
	}
	*/
	@Override
	public Peashooter copy(GameWorld game, int col, int row) {
		Peashooter p = new Peashooter(game, col, row);
		return p;
	}
	
}



