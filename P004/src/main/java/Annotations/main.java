package Annotations;

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
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class main {
	public static void main(String args[]) throws FileNotFoundException, IOException, CsvValidationException {
		String fileCSV = "D:\\Fise3\\PRI\\pri-sopra-steria\\P004\\Rapport.csv";
		List<String[]> doc;
		main testCSV = new main();
		doc = testCSV.readCSVFile(fileCSV);
		AnnotationReflection annotation = new AnnotationReflection();
		Map<String, ArrayList<String>> multiValueMap = annotation.AnnotationTu();
		
        for (int i = 2; i<doc.size() ; i++)
        {
        	String tmp = doc.get(i)[0];
        	if (tmp != "")
        	System.out.println(tmp);
        	if (multiValueMap.containsKey(tmp))
        	{
        		String[] tmp2 = doc.get(i);
        		System.out.println("tmp2[4]"+tmp2[4]);
        		System.out.println("tmp2"+tmp2);

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

		testCSV.writeToCsv(fileCSV, doc);
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
	
    public void writeToCsv(String fileCSV, List<String[]> doc){
        try{
            CSVWriter writer = new CSVWriter(new FileWriter("D:\\Fise3\\PRI\\pri-sopra-steria\\P004\\Rapport2.csv"), ';', ' ', '\t', "\n");
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
