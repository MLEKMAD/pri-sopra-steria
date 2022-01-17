package Annotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.opencsv.exceptions.CsvValidationException;

import process.ProcessProgram;

public class Main {
	public static void main(String args[]) throws FileNotFoundException, IOException, CsvValidationException {
		String fileCSV = "C:\\Users\\Maxime\\Documents\\PRO\\Scolaire\\Cours\\FISE3\\PRI\\Rapport\\Rapport.csv";
		String Destination = "C:\\Users\\Maxime\\Documents\\PRO\\Scolaire\\Cours\\FISE3\\PRI\\Rapport";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime date = LocalDateTime.now();
		String outputFile = Destination + "\\Rapport-" + dtf.format(date).toString() + ".csv";
		ProcessProgram testCSV = new ProcessProgram();
		testCSV.processFile(fileCSV, outputFile);
	}
}
