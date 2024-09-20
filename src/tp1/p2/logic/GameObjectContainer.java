package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public void add(GameObject object) {
		List<GameObject> c2 = new ArrayList<>();
		for(GameObject o : gameObjects) {
			if(o.isAlive()) {
				c2.add(o);
			}
		}
		object.onEnter();
		c2.add(object);
		gameObjects = c2;
	}
	
	public boolean isObjectInPosition(int col, int row){  //Puede que no lo usemos. Mismo funcionamiento que isPositionEmpty
		 int i=0;
		 boolean found = false;
		 while (i<gameObjects.size() && !found) { 
			 if (gameObjects.get(i).isAlive() && gameObjects.get(i).isInPosition(col, row)) {
				 found = true;
			 }
			 else {
				 i++; 
			 }	
		 }
		 return found;
	}
	
	public void update() {
		for(GameObject o : gameObjects) {
			o.update();
		}
	}
	
	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}
	
	public GameObject getGameItemInPosition(int col, int row) {
		GameObject o = null;
		boolean empty = true;
		int i = 0;
		while (i < gameObjects.size() && empty) {
			if(gameObjects.get(i).isInPosition(col, row)) {
				empty = false;
			}
			else {
				i++;
			}
		}
		if(empty == false) {
			o = gameObjects.get(i);
		}
	
		return o;
	}
	
	public GameObject getSunInPosition(int col, int row) {
		GameObject o = null;
		boolean empty = true;
		int i = 0;
		while (i < gameObjects.size() && empty) {
			if(gameObjects.get(i).isInPosition(col, row) && !gameObjects.get(i).fillPosition()) {
				empty = false;
			}
			else {
				i++;
			}
		}
		if(empty == false) {
			o = gameObjects.get(i);
		}
	
		return o;
	}
	
	public void clear() {
		gameObjects.clear();
	}

	public boolean removeDead() {
		boolean removed = false;
		 List<GameObject> c2 = new ArrayList<>();
			for(GameObject o : gameObjects) {
				if(!o.isAlive()) {
					o.onExit();
					removed = true;
				}
				else {
					c2.add(o);
				}
			}
			gameObjects = c2;
		return removed;
	}

	public boolean isFullyOcuppied(int col, int row) {
		boolean ocuppied = false;
		int i = 0;
		while(i < gameObjects.size() && !ocuppied) {
			if(gameObjects.get(i).isInPosition(col, row) && gameObjects.get(i).fillPosition()) {
				ocuppied = true;
			}
			else {
				i++;
			}
		}
		return ocuppied;
	}
}
