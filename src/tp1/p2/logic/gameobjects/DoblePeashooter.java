package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;


public class DoblePeashooter extends Shooter{
	
	private static int COST = 70;
	private static int DAMAGE = 1;
	private static int ENDURANCE = 3;
	
	public DoblePeashooter() {
	}
	
	public DoblePeashooter(GameWorld game, int col, int row){
		super(game, col, row, DAMAGE, ENDURANCE, COST);
	}

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return Messages.DOBLE_PEASHOOTER_NAME;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		DoblePeashooter dp = new DoblePeashooter(game, col, row);
		return dp;
	}

	@Override
	public int getCost() {
		return DoblePeashooter.COST;
	}

	@Override
	protected String getSymbol() {
		return Messages.DOBLE_PEASHOOTER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.DOBLE_PEASHOOTER_NAME_SHORTCUT, COST,DAMAGE, ENDURANCE);
	};
	
	@Override
	public void update() {
		for(int i = 0; i<2 ; i ++) {
			super.update();
		}
	}

}
