package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Peashooter;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class FireWaveCommand extends Command{

	private String Direccion;
	
	private static final int COST = 100;
	
public FireWaveCommand() {
		
	}

	public FireWaveCommand(String Direccion) {
		this.Direccion = Direccion;
		
	
	}
	
	
	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_FIRE_WAVE_NAME;
	}

	@Override
	protected String getShortcut() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_FIRE_WAVE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		int col =0;
		int row = 0;
		int colInicio = 0;
		int direccion = 0;
		int limite = game.NUM_COLS;
		boolean attacked;
		
		game.tryToBuy(COST);
		if(this.Direccion.equalsIgnoreCase("l")) {
			colInicio = game.NUM_COLS;
			direccion = -1;
			limite = 0;
		}
		else if(this.Direccion.equalsIgnoreCase("r")) {
			colInicio = 0;
			direccion = 1;
			limite = game.NUM_COLS;
		}
		while( row < GameWorld.NUM_ROWS) {
			attacked = false;
			col = colInicio;
			while(col != limite && !attacked) {
				attacked = game.attackZombie(col, row, 1);
				if(!attacked) {
					col = col + (direccion);
				}
			}
			row++;
		}
		game.addSunCoins(-COST);
		return true;
	}
	
	@Override
	public Command create(String[] parameters) throws GameException {
		int col, row;
		Command command = null;
		String Direccion;
		if(parameters.length == 1) {
			if(parameters[0].equalsIgnoreCase("l") || parameters[0].equalsIgnoreCase("r")) {
				Direccion = parameters[0];
					command =  new FireWaveCommand(Direccion);
				}
			else {
				throw new CommandExecuteException((Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			}
				
		}
		else {
			throw new CommandExecuteException((Messages.COMMAND_PARAMETERS_MISSING));
		}
		
		return command;
	}

	
}
