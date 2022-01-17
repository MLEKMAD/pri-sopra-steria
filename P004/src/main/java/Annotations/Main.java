package Annotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.opencsv.exceptions.CsvValidationException;

import process.ProcessProgram;

public class Main {
	public static void main(String args[]) throws FileNotFoundException, IOException, CsvValidationException {
		String fileCSV = "Rapport.csv";
		String outputFile = "Rapport2.csv";
		ProcessProgram testCSV = new ProcessProgram();
		testCSV.processFile(fileCSV, outputFile);

}
}
