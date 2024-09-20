package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld {

	private static final int INITIAL_SUNCOINS = 50;

	private long seed;

	private boolean playerQuits;
	
	private Level level;
	
	private int cycle;
	
	private Random rand;
	
	private int suncoins;
	
	private int score = 0;
	
	private ZombiesManager zombiesManager;
	
	private SunsManager suns;

	private GameObjectContainer container;

	private Deque<GameAction> actions;
	
	private Record record;

	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();
		reset();
	}

	/**
	 */
	@Override
	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	@Override
	public void reset(Level level, long seed) throws GameException{
		this.cycle = 0;
		Sun.resetCounters();
		this.actions = new ArrayDeque<>();
		this.level = level;
		this.seed = seed;
		this.rand = new Random(seed);
		this.suncoins = INITIAL_SUNCOINS;
		this.playerQuits = false;
		this.suns = new SunsManager(this, this.rand);
		this.zombiesManager = new ZombiesManager(this, this.level, this.rand);
		this.container.clear();
		this.actions.clear();
		
		
		
		
		this.record = new Record();
		this.score = 0;
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
		leerRecords();
	}


	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */
	@Override
	public void update() throws GameException{

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		zombiesManager.addZombie();
		suns.update();
		

		// 3. Game object updates
		container.update();
		

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		
		//7.Record update
		record.update(score, getLevelName());

	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}
	
	public boolean isFinished() {
		boolean acabajuego = false;
		if(isPlayerDied() || allZombiesDied()) {
			acabajuego = true;
		}
		return acabajuego;
	}
	@Override
	public boolean isPlayerQuits() {
		return playerQuits == true;
	}

	@Override
	public boolean isPlayerDied() {
		return zombieArrived();
	}
	
	@Override
	public void playerQuits() {
		this.playerQuits = true;
	}

	@Override
	public boolean isObjectInPosition(int col, int row) {
		return container.isObjectInPosition(col, row);
	}
	
	private static boolean isPositionInLimits(int col, int row) {
		boolean isInLimits = true;
		if(row < 0 || row >= NUM_ROWS) {
			isInLimits = false;
		}
		if(col < 0 || col >= NUM_COLS) {
			isInLimits = false;
		}
		return isInLimits;
	}

	@Override
	public boolean attackPlant(int col, int row, int damage) {
		boolean attacked = false;
		GameItem item = container.getGameItemInPosition(col, row);
		if(item != null) {
			attacked = item.receiveZombieAttack(damage);
		}
		return attacked;
	}
	
	@Override
	public boolean attackZombie(int col, int row, int damage) {
		boolean attacked = false;
		GameItem item = container.getGameItemInPosition(col, row);
		if(item != null) {
			attacked = item.receivePlantAttack(damage);
		}
		return attacked;
	}
	
	@Override
	public boolean receiveChiliAttack(int col,int row,int damage) {
		boolean attackedbyChili = false;
		GameItem item = container.getGameItemInPosition(col, row);
		if(item != null) {
			attackedbyChili = item.receivePlantAttack(damage);
		}
		return attackedbyChili;
	}
	

	@Override
	public void addSunCoins(int suncoins) {
		this.suncoins += suncoins;
		
	}

	@Override
	public void addGameObject(GameObject object) {
		this.container.add(object);
	}
	@Override
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOcuppied(col, row);
	}

	@Override
	public void tryToBuy(int cost) throws GameException {
		if(this.suncoins < cost) {
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}
	}

	@Override
	public boolean zombieArrived() {
		int i = 0;
		boolean empty = true;
		while(i < NUM_ROWS && empty) {
			empty = !isObjectInPosition(-1, i);
			i++;
		}
		return !empty;
	}

	@Override
	public void zombieDied() {
		zombiesManager.zombieDied();
	}
	
	@Override 
	public void newZombie() {
		zombiesManager.newZombie();
	}

	@Override
	public void CheckValidPlantPosition(int col, int row) throws GameException{
		if (!isPositionInLimits(col, row) || isFullyOcuppied(col, row)) {
			throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
		}
		
	}
	
	@Override
	public void CheckValidZombiePosition(int col, int row) throws GameException{
		boolean isInLimits = true;
		if(row < 0 || row >= NUM_ROWS) {
			isInLimits = false;
		}
		if(col < 0 || col > NUM_COLS) {
			isInLimits = false;
		}
		
		if (!isInLimits || isFullyOcuppied(col, row)) {
			throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
		}
	}

	@Override
	public int getCycle() {
		return this.cycle;
	}

	@Override
	public int getSuncoins() {
		return this.suncoins;
	}
	
	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies(); 
	}

	@Override
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}

	@Override
	public boolean allZombiesDied() {
		return zombiesManager.allZombiesDied(); 
	}
	
	public boolean execute(Command command) throws GameException {
		 boolean result = command.execute(this);
		 
		 return result;
	}

	@Override
	public void addSun() {
		suns.addSun();
	}
	
	@Override
	public void addSun(int col, int row) {
		suns.addSun(col, row);
	}

	@Override
	public void tryToCatchObject(int col, int row) throws GameException{
		GameObject o = null;
		o = container.getSunInPosition(col, row);
		if(o != null) {
			o.catchObject();
		}
		else {
			throw new NotCatchablePositionException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));
		}
		
	}

	@Override
	public boolean addItem(GameObject item) {
		boolean added = false;
		int col = item.getCol();
		int row = item.getRow();
		if (!isPositionInLimits(col,row) || item.fillPosition() && isFullyOcuppied(col,row)) {
			added = false;
		}
		else {
			container.add(item);
			added = true;
		}
		
		return added;
	}
	
	@Override
	public int getGeneratedSuns() {
		return suns.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return suns.getCatchedSuns();
	}

	@Override
	public void pushAction(GameAction action) {
		this.actions.addLast(action);
	}
	
	@Override
	public String getLevelName() {
		return this.level.name();
	}
	
	
	@Override
	public int getActualRecord() {
		return record.getActualRecord(getLevelName());
	}
	
	@Override 
	public void addScore(int points) {
		this.score += points;
	}
	
	public void leerRecords() throws GameException {
		record.leerRecords();
	}
	
	public void escribirRecords() throws GameException{
		record.escribirRecords();
	}
}
