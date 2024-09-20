package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.status;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {
	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int lives;
	
	protected int cooldown;

	GameObject() {
	
	}

	GameObject(GameWorld game, int col, int row, int lives) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.lives = lives;
	}
	
	public GameObject(GameWorld game, int col, int row, int lives, int cooldown) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.lives = lives;
		this.cooldown = cooldown;
	}
	
	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
	
	public boolean isAlive() {
		boolean alive = false;
		if(this.lives > 0) {
			alive = true;
		}
		return alive;
	}
	
	public void receiveDamaged(int damage) {
		this.lives -= damage;
	}
	

	public String toString() {
		String str = null;
		if (isAlive()) {
			str = Messages.status(getSymbol(), this.lives);
		} 
		else {
			str = "";
		}
		return str;
	}

	abstract protected String getSymbol();

	abstract public String getDescription();
	
	abstract public boolean fillPosition();

	 public void update() {
		 if(isAlive() && cooldown >= 0) {
			 if(cooldown == 0) {
				 delayedAction();
				 cooldown = getCooldown();
			 }
			 else {
				 cooldown--;
			 }
		 }
		 else
		 {
			 
		 }
	 }
	
	protected int getCooldown() {
		return this.cooldown;
	}
	
	protected void delayedAction() {
		
	}
	
	abstract public void onEnter();
	
	abstract public void onExit();
}
