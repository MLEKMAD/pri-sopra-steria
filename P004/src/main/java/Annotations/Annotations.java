package Annotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.opencsv.exceptions.CsvValidationException;

import process.ProcessProgram;

public class Annotations {
	public static Boolean annotation(String entree, String sortie, Boolean falseIfKo, Boolean activateLog)
			throws IOException {
		String fileCSV = entree;
		String Destination = sortie;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd___HH-mm-ss");
		LocalDateTime date = LocalDateTime.now();
		
		String outputFile = Destination + "\\Rapport-" + dtf.format(date).toString() + ".csv";
		String outputLog = Destination + "\\log-" + dtf.format(date).toString() + ".txt";
		
		ProcessProgram testCSV = new ProcessProgram();
		
		Boolean isOk = testCSV.processFile(fileCSV, outputFile);
		if (!falseIfKo) isOk = true;
		
		testCSV.printLog(); // Ecrit le log dans la console
		if (activateLog) testCSV.writeLog(outputLog); // Ecrit le log
		
		return isOk;
	}
}