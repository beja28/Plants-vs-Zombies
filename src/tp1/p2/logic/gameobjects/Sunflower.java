package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {
	
	
	public static final int COST = 20;				//Constantes
	public static final int DAMAGE = 0;
	public static final int ENDURANCE = 1;
	public static final int COOLDOWN = 3;
	public static final int SUNCOINS = 10;
	
	
	
	public Sunflower() {
		
	}

	public Sunflower(GameWorld game, int col, int row) {
		super(game,col,row,ENDURANCE,COOLDOWN);
		this.attackedbyChili = false;
	}
	
	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.SUNFLOWER_NAME_SHORTCUT,COST,DAMAGE, ENDURANCE);
	}
	
	@Override
	protected String getName() {
		return Messages.SUNFLOWER_NAME;
	}
	
	@Override
	public int getCost() {
		return Sunflower.COST;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void delayedAction() {
		game.addSun(this.col, this.row);
		this.cooldown = COOLDOWN - 1;
	}
	
	@Override
	public Sunflower copy(GameWorld game, int col, int row) {
		Sunflower s = new Sunflower(game, col, row);
		return s;
	}
	
	public void OnExit() {
		ExplosionAction explosion = new ExplosionAction(this.col, this.row, DAMAGE, true, attackedbyChili);
		game.pushAction(explosion);
	}

}
