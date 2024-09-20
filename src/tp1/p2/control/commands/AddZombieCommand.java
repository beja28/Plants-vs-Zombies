package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}
	
	private AddZombieCommand(int zombieIdx) {
		this.zombieIdx = zombieIdx;
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{
		boolean result = false;
		game.CheckValidZombiePosition(this.col, this.row);
		Zombie zombie  = ZombieFactory.spawnZombie(this.zombieIdx, game, this.col, this.row);
		game.addGameObject(zombie);
		game.newZombie();
		game.update();
		result = true;
			
		return result;
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		int col = 0, row = 0, zombieType;
		Command command = null;
		
		if(parameters.length == 3) {
			try {
				zombieType = Integer.parseInt(parameters[0]);
				col = Integer.parseInt(parameters[1]);
				row = Integer.parseInt(parameters[2]);
				if(ZombieFactory.isValidZombie(zombieType)) {
					command =  new AddZombieCommand(zombieType, col, row);
				}
				else {
					throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
				}
			} catch (NumberFormatException nfe) {
				throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), nfe);
			}
			
		}
		else {
			throw new CommandExecuteException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		
		return command;
	}

}
