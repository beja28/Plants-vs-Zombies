package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{
		if(this.level == null){
			game.reset();
		}
		else {
			game.reset(this.level, this.seed);
		}
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		Command command = null;
		long seed = 0;
		Level level;
		
		if(parameters.length == 2) {
			try {
				seed = Long.parseLong(parameters[1]);
				
			} catch (NumberFormatException nfe){
				throw new CommandParseException(Messages.SEED_NOT_A_NUMBER, nfe);
			}
			level = Level.valueOfIgnoreCase(parameters[0]);
				if(level != null) {
					command = new ResetCommand(level, seed);
				}
		}
		else if(parameters.length == 0) {
			command = new ResetCommand();
		}
		else {
			throw new CommandExecuteException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return command;
	}

}
