package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject{
	
	public static final int  SUN_LIVES = 10+1;
	private static final int SUN_COINS = 10;
	private static int generatedSuns;
	private static int catchedSuns;
	
	
	public Sun(GameWorld game, int col, int row, int lives) {
		super(game, col, row, lives);
	}
	
	public static boolean resetCounters() {
		generatedSuns = 0;
		catchedSuns = 0;
		return true;
	}
	
	public static int getGeneratedSuns() {
		return generatedSuns;
	}
	
	public static int getCatchedSuns() {
		return catchedSuns;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}

	@Override
	public boolean catchObject() {
		game.addSunCoins(SUN_COINS);
		this.lives = 0;
		catchedSuns++;
		return true;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	@Override
	public void onEnter() {
		generatedSuns++;
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void update() {
		this.lives--;
	}
	
	@Override
	public boolean fillPosition() {
		return false;
	}
	
}
