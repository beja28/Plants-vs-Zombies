package tp1.p2.logic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Record {

	private int[] array_records = new int[3];
	
	public Record() {
		Arrays.fill(this.array_records, 0);
	}
	
	public void leerRecords() throws GameException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Messages.RECORD_FILENAME));
			String linea = br.readLine(); 
			while(linea != null) {
				String palabras[] = linea.split(":");
				if(palabras[0].equalsIgnoreCase("easy")) {
					array_records[0] = Integer.parseInt(palabras[1]);
				}
				else if(palabras[0].equalsIgnoreCase("hard")) {
					array_records[1] = Integer.parseInt(palabras[1]);
				}
				else if(palabras[0].equalsIgnoreCase("insane")) {
					array_records[2] = Integer.parseInt(palabras[1]);
				}
				linea = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			throw new RecordException(Messages.RECORD_READ_ERROR,e);	
		} catch(NumberFormatException nfe) {
			throw new CommandParseException(nfe);
		}
	}
	
	public void escribirRecords() throws GameException {
		BufferedWriter wr = null;
			try {
				wr = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME));
				wr.write("Easy:" + array_records[0]);
				wr.newLine();
				wr.write("Hard:" + array_records[1]);
				wr.newLine();
				wr.write("Insane:" + array_records[2]);
				wr.close();
			} catch (IOException e) {
				throw new RecordException(Messages.RECORD_WRITE_ERROR,e);
			}
		
	}
	
	public int getActualRecord(String levelName) {
		int actualRecord = 0;
		if(levelName.equalsIgnoreCase("easy")) {
			actualRecord = array_records[0];
		}
		else if(levelName.equalsIgnoreCase("hard")) {
			actualRecord = array_records[1];
		}
		else if(levelName.equalsIgnoreCase("insane")) {
			actualRecord = array_records[2];
		}
		return actualRecord;
	}
	
	public void update(int score, String levelName) {
		int actualRecord = getActualRecord(levelName);
		if(actualRecord < score) {
			if(levelName.equalsIgnoreCase("easy")) {
				array_records[0] = score;
			}
			else if(levelName.equalsIgnoreCase("hard")) {
				array_records[1] = score;
			}
			else if(levelName.equalsIgnoreCase("insane")) {
				array_records[2] = score;
			}
		}
	}
	
}
