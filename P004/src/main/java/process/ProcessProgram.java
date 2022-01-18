package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import Annotations.AnnotationReflection;

public class ProcessProgram {

	List<String> textLog = new ArrayList<String>();

	// Fonction vérifiant la correspondance TU/TI avec les RG et l'écrit dans un
	// fichier CSV
	public Boolean processFile(String fileCSV, String outputFile) throws IOException {

		textLog.add("/!\\ -- Règle des gestions présentent dans les annotations mais pas dans le CSV -- /!\\ \n");

		List<String[]> doc;
		doc = this.readCSVFile(fileCSV);
		Boolean noKO = true;
		AnnotationReflection annotation = new AnnotationReflection();
		Map<String, ArrayList<String>> multiValueMapTU = annotation.getDecorators(true);
		Map<String, ArrayList<String>> multiValueMapTI = annotation.getDecorators(false);
		List<String> rgName = new ArrayList<String>();
		int docSize = doc.size();
		for (int i = 2; i < docSize; i++)
			rgName.add(doc.get(i)[0]); // Récupération des régles de gestion dans une varriable locale Ecriture de la
										// couverture des RG par les TI/TU
		for (int i = 2; i < docSize; i++) // On commence a 2 pour passer les deux premières lignes du fichier
		{
			List<String> inputLine = new ArrayList<String>();
			inputLine = new ArrayList<>(Arrays.asList(doc.get(i)));
			int inputSize = inputLine.size();
			if (multiValueMapTU.containsKey(rgName.get(i-2))) // Ecriture des TU
			{
				for (int j = inputSize; j < 6; j++) { // Remplis les case par null jusqu'à la colone contenant les KO/OK
					inputLine.add(null);
				}
				inputLine.set(5, "OK");
				inputLine.set(2, String.join(",", multiValueMapTU.get(rgName.get(i-2))));
				doc.set(i, inputLine.toArray(new String[0]));
			}
			if (multiValueMapTI.containsKey(rgName.get(i-2))) // Ecriture des TI
			{
				for (int j = inputSize; j < 6; j++) { // Remplis les case par null jusqu'à la colone contenant les KO/OK
					inputLine.add(null);
				}
				inputLine.set(5, "OK");
				inputLine.set(3, String.join(",", multiValueMapTI.get(rgName.get(i-2))));
				doc.set(i, inputLine.toArray(new String[0]));
			}
			if (!multiValueMapTU.containsKey(rgName.get(i-2)) && !multiValueMapTI.containsKey(rgName.get(i-2))) // Ecriture des KO
			{
				for (int j = inputSize; j < 6; j++) { // Remplis les case par null jusqu'à la colone contenant les KO/OK
					inputLine.add(null);
				}
				inputLine.set(5, "KO");
				inputLine.set(2, null);
				doc.set(i, inputLine.toArray(new String[0]));
				noKO = false;
			}
		}
		this.writeToCsv(fileCSV, doc, outputFile);

		for (int i = 0; i < multiValueMapTU.size(); i++) {
			String rgToIndexZ = (String) multiValueMapTU.keySet().toArray()[i];
			if (!rgName.contains((CharSequence) rgToIndexZ))
				textLog.add(rgToIndexZ);
		}
		return noKO;
	}

	public List<String[]> readCSVFile(String fileCSV) {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(fileCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String[]> allRows = null;
		try {
			allRows = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		List<String[]> doc = new ArrayList();
		allRows.forEach(row -> {
			row = row[0].split(";");
			doc.add(row);
		});
		return doc;
	}

	public void writeToCsv(String fileCSV, List<String[]> doc, String outputFile) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ';', ' ', '\t', "\n");
			for (int i = 0; i < doc.size(); i++) {
				String[] res = doc.get(i);

				writer.writeNext(res);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeLog(String outputFile) throws IOException {
		File logFile = new File(outputFile);
		if (!logFile.exists())
			logFile.createNewFile();

		PrintWriter writer = new PrintWriter(logFile, "UTF-8");

		for (String line : textLog)
			writer.println(line);

		writer.close();
	}

	public void printLog() {
		if (textLog.size() > 1) {
			System.out.println("\n \n");
			for (String line : textLog)
				System.out.println(line);
		}

	}
}