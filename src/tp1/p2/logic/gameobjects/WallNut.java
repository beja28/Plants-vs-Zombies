package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	
	public static final int COST = 50;				//Constantes
	public static final int DAMAGE = 0;
	public static final int ENDURANCE = 10;
	
	public WallNut() {
		
	}
	
	public WallNut(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE);
	}
	
	@Override
	protected String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		WallNut w = new WallNut(game, col, row);
		return w;
	}

	@Override
	public int getCost() {
		return WallNut.COST;
	}

	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.WALL_NUT_NAME_SHORTCUT,COST,DAMAGE, ENDURANCE);
	}
	
	@Override
	public void update() {
	
		
	}
	
}
