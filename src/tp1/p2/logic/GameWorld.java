package tp1.p2.logic;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void playerQuits();
	
	void update() throws GameException;
	
	void reset() throws GameException;
	
	void reset(Level level, long seed) throws GameException;
	
	boolean isObjectInPosition(int col, int row);
	
	boolean attackPlant(int col, int row, int damage);
	
	boolean attackZombie(int col, int row, int damage);
	
	boolean receiveChiliAttack(int col, int row, int damage);
	
	void addSunCoins(int suncoins);
	
	void addGameObject(GameObject object);
	
	void tryToBuy(int cost) throws GameException;
	
	boolean zombieArrived();
	
	void zombieDied();
	
	void newZombie();
	
	void CheckValidPlantPosition(int col, int row) throws GameException;
	
	void CheckValidZombiePosition(int col, int row) throws GameException;
	
	void addSun();
	
	void addSun(int col, int row);
	
	public boolean isFullyOcuppied(int col, int row);

	void tryToCatchObject(int col, int row) throws GameException;

	boolean addItem(GameObject item);
	
	void pushAction(GameAction action);

	String getLevelName();

	int getActualRecord();
	
	void addScore(int points);

}
