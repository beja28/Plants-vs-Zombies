package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public class Shooter extends Plant {

	protected int damage;
	protected int endurance;
	protected int cost;
	
	
	public Shooter() {
		
	}

	public Shooter(GameWorld game, int col, int row, int damage, int endurance, int cost) {
		super(game, col, row, endurance);
		this.damage = damage;
		this.endurance = endurance;
		this.cost = cost;
	}
	
	
	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		return null;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	protected String getSymbol() {
		return null;
	}

	@Override
	public  String getDescription() {
		return null;
	}
	
	@Override
	public void update() {
		boolean attacked = false;
		int i = this.col+1;
		if(isAlive()){		
			while(i < GameWorld.NUM_COLS && !attacked) {
				attacked = game.attackZombie(i, this.row, this.damage);
				if(!attacked) {
					i++;
				}
			}
		}
	}
	
	
	

}
