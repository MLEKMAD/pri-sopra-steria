package pri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SImpleClass {

	
	private static List<String> processInputFile(String inputFilePath) {

	    List<String> inputList = new ArrayList<String>();

	    try{

	      File inputF = new File(inputFilePath);
	      InputStream inputFS = new FileInputStream(inputF);
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

	      // skip the header of the csv
	      inputList = br.lines().skip(2).collect(Collectors.toList());
	      br.close();
	    } catch (IOException e) {
	      System.out.println(e);
	    }

	    return inputList ;
	}
	private static List<String> getSpecs(List<String> doc){
		List<String> specs = new ArrayList<String>();
		doc.forEach(line -> {
			String spec = line.trim().split(";")[0];
			if(spec != "") {
				specs.add(spec);
			}
			
		});
		return specs;
	}
	public static void main(String[] args) {
		String inFile = "C:\\Users\\HamidLekmad\\Downloads\\Rapport.csv";
		System.out.println(processInputFile(inFile));
		System.out.println("specs: " + getSpecs(processInputFile(inFile)));
	}
}
