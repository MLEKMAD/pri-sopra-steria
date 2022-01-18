package process;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import Annotations.AnnotationReflection;

public class ProcessProgram {
	// Fonction vérifiant la correspondance TU/TI avec les RG et l'écrit dans un fichier CSV
	public Boolean processFile(String fileCSV, String outputFile) {
		List<String[]> doc;
		doc = this.readCSVFile(fileCSV);
		Boolean ok = true;
		AnnotationReflection annotation = new AnnotationReflection();
		Map<String, ArrayList<String>> multiValueMapTU = annotation.getTuDecorators();
		Map<String, ArrayList<String>> multiValueMapTI = annotation.getTiDecorators();
		// Ecriture de la couverture des RG par les TI/TU
        for (int i = 2; i<doc.size() ; i++)
        {
        	String tmp = doc.get(i)[0];
        	if (multiValueMapTU.containsKey(tmp)) // Ecriture des TU
        	{
        		String[] tmp2 = doc.get(i);
        		tmp2[5] = "OK";
        		tmp2[2] = String.join(",", multiValueMapTU.get(tmp));
        		doc.set(i, tmp2);
        	}
        	if (multiValueMapTI.containsKey(tmp)) // Ecriture des TI
        	{
        		String[] tmp2 = doc.get(i);
        		tmp2[5] = "OK";
        		tmp2[3] = String.join(",", multiValueMapTI.get(tmp));
        		doc.set(i, tmp2);
        	}
        	if (!multiValueMapTU.containsKey(tmp) && !multiValueMapTI.containsKey(tmp)) // Ecriture des KO
        	{
        		String[] tmp2 = doc.get(i);
        		tmp2[5] = "KO";
        		tmp2[2] = null;
        		doc.set(i, tmp2);
        		ok = false;
        	}
        }
		this.writeToCsv(fileCSV, doc, outputFile);
		return ok;
	}
	
	
	
	public List<String[]> readCSVFile(String fileCSV){
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
        allRows.forEach(row->{
        	row = row[0].split(";");
        	doc.add(row);
        });
        return doc;
    }
	
	
	
    public void writeToCsv(String fileCSV, List<String[]> doc, String outputFile){
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ';', ' ', '\t', "\n");
            for (int i = 0 ; i < doc.size() ; i++) {
            	String[] res = doc.get(i);
            	
            	writer.writeNext(res);
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
