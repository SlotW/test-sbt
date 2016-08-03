package FileReader;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class FileReader {

	public static List<String[]> readFile(String pathFile){
		BufferedReader buffer = null;
		List<String[]> operationsList = new ArrayList<String[]>();
		String line = "";
		final String split = ";";
		
		try {
			buffer = new BufferedReader(new java.io.FileReader(pathFile));
			try {
				while ((line = buffer.readLine()) != null) {
				    final String[] row = line.split(split);
				    operationsList.add(row);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return operationsList;
	}
}
