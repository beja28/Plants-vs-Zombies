package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb(),
		new DoblePeashooter(),
		new Chilipeper()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) {
		boolean valid = false;
		for (Plant p : AVAILABLE_PLANTS) {
			if(plantName.equalsIgnoreCase(p.getName()) || plantName.equalsIgnoreCase(p.getSymbol())) {
				valid = true;
			}
		}

		return valid;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) throws GameException{
		Plant p = null;
		for (Plant a: AVAILABLE_PLANTS) {
			if(plantName.equalsIgnoreCase(a.getName()) || plantName.equalsIgnoreCase(a.getSymbol())) {
				p = a.copy(game, col, row);
			}
		}
		if (p == null) {
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		}
		return p;
	}

	public static Iterable<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}
}
