package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;

public class Chilipeper extends Plant{

	public static int DAMAGE = 3;
	public static int ENDURANCE = 1;
	public static int COST = 100;
	
	public Chilipeper() {
		
	}
	
	public Chilipeper(GameWorld game, int col, int row) {
		super(game, col,row, ENDURANCE);
	}
	
	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onExit() {
		
		for(int i = 0; i<game.NUM_COLS; i++) {
			//GameItem item = container.getGameItemInPosition(col, row);
		}
	
	}
	
}
