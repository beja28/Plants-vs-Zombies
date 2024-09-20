package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant{
	
	public static final int COST = 50;				//Constantes
	public static final int DAMAGE = 10;
	public static final int ENDURANCE = 2;
	public static final int DELAY_TO_EXPLODE = 2; //Hay que corregirlo
	
	public CherryBomb() {
		
	}
	
	public CherryBomb(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE, DELAY_TO_EXPLODE);
	}
	
	@Override
	protected String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		CherryBomb c = new CherryBomb(game, col, row);
		return c;
	}

	@Override
	public int getCost() {
		return CherryBomb.COST;
	}

	@Override
	protected String getSymbol() {
		String symbol = null;
		if(cooldown != 0) {
			symbol = Messages.CHERRY_BOMB_SYMBOL;
		}
		else {
			symbol = Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
		}
		
		return symbol;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.CHERRY_BOMB_NAME_SHORTCUT,COST,DAMAGE, ENDURANCE);
	}
	
	@Override
	public void update() {
		super.update();
	}

	@Override
	public void delayedAction() {
		ExplosionAction explosion = new ExplosionAction(this.col, this.row, DAMAGE, true, true);
		game.pushAction(explosion);
		this.lives = 0;
	}
}
