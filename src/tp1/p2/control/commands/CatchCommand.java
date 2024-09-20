package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		boolean result = false;
		if(caughtSunThisCycle) {
            throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
        }
		else {
			game.tryToCatchObject(col, row); 
			result = true;
			caughtSunThisCycle = true;
		}
		
        return result;
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		int col, row;
		Command command = null;;
		if(parameters.length < 2) {
			System.out.println(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		else {
			try {
				col = Integer.parseInt(parameters[0]);
				row = Integer.parseInt(parameters[1]);
				command = new CatchCommand(col, row);
			}catch(NumberFormatException nfe) {
				System.out.println(error(Messages.INVALID_POSITION));
			}
		}
		
		return command;
	}

}
