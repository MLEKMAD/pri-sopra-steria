package process;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import Annotations.AnnotationReflection;

public class ProcessProgram {
	public void processFile(String fileCSV, String outputFile) {
		List<String[]> doc;
		doc = this.readCSVFile(fileCSV);
		AnnotationReflection annotation = new AnnotationReflection();
		Map<String, ArrayList<String>> multiValueMap = annotation.getTuDecorators();
		
        for (int i = 2; i<doc.size() ; i++)
        {
        	String tmp = doc.get(i)[0];
        	if (tmp != "")
        	System.out.println(tmp);
        	if (multiValueMap.containsKey(tmp))
        	{
        		String[] tmp2 = doc.get(i);
        		tmp2[5] = "OK";
        		
        		tmp2[2] = String.join(",", multiValueMap.get(tmp));
        		doc.set(i, tmp2);
        	}
        	else
        	{
        		String[] tmp2 = doc.get(i);
        		System.out.println("test");
        		System.out.println(tmp2);
        		tmp2[5] = "KO";
        		tmp2[2] = null;
        		doc.set(i, tmp2);

        	}
        }

        for(String[] row : doc){
            System.out.println(Arrays.toString(row));
        }

		this.writeToCsv(fileCSV, doc, outputFile);
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
        
        for(String[] row : allRows){
            System.out.println(Arrays.toString(row));
        }
        List<String[]> doc = new ArrayList();
        allRows.forEach(row->{
        	row = row[0].split(";");
        	doc.add(row);
        });
        for(String[] row : doc){
            System.out.println(Arrays.toString(row));
        }
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
