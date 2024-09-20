package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	public AddPlantCommand() {
		
	}

	public AddPlantCommand(String plantName, int col, int row) {
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	
	}
	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}


	@Override
	public boolean execute(GameWorld game) throws GameException{
		boolean result = false;
				game.CheckValidPlantPosition(this.col, this.row);
				Plant plant  = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
				game.tryToBuy(plant.getCost());
				game.addGameObject(plant);
				game.addSunCoins(-plant.getCost());
				game.update();
				result = true;
		return result;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		int col, row;
		Command command = null;
		if(parameters.length == 3) {
			if(PlantFactory.isValidPlant(parameters[0])) {
				try {
					col = Integer.parseInt(parameters[1]);
					row = Integer.parseInt(parameters[2]);
					command =  new AddPlantCommand(parameters[0], col, row);
				} catch (NumberFormatException nfe) {
					throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), nfe);
				}
			}
			else {
				throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
			}
		}
		else {
			throw new CommandExecuteException((Messages.COMMAND_PARAMETERS_MISSING));
		}
		
		return command;
	}

}


