package Annotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.opencsv.exceptions.CsvValidationException;

import process.ProcessProgram;

public class Annotations {
	public static Boolean Annotation(String entree, String sortie, Boolean ok)
			throws FileNotFoundException, IOException, CsvValidationException {
		String fileCSV = entree;
		String Destination = sortie;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime date = LocalDateTime.now();
		String outputFile = Destination + "\\Rapport-" + dtf.format(date).toString() + ".csv";
		ProcessProgram testCSV = new ProcessProgram();
		Boolean isOk = testCSV.processFile(fileCSV, outputFile);
		if (!ok)
			isOk = true;
		return isOk;
	}
}