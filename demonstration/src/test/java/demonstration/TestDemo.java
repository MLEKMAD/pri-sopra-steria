package demonstration;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.exceptions.CsvValidationException;

import Annotations.Annotations;
import Annotations.RGDTIf;
import Annotations.RGDTUc;
import Annotations.RGDTUf;

@RGDTUc(rules = {"RG1", "RG2", "RG3", "RG4"})
public class TestDemo {

	@RGDTUf(rules = "RG1")
	@Test
	public void testAdition() {
		assertEquals(MainDemo.addition(2, 2), 4);		
	}
	
	@Test
	@RGDTIf(rules = "RG2")
	public void testSoustraction() {
		assertEquals(MainDemo.soustraction(3, 2), 1);		
	}
	
	@Test
	@RGDTUf(rules = {"RG3", "RG7", "RG4"})
	public void testMutiplicationDivision() {
		assertEquals(MainDemo.multiplication(3, 2), 6);	
		assertEquals(MainDemo.division(10, 5), 2);	
	}
	
	@Test
	public void testCouverture() throws CsvValidationException, FileNotFoundException, IOException {
		assert(Annotations.annotation("C:\\Users\\Maxime\\Documents\\PRO\\Scolaire\\Cours\\FISE3\\PRI\\Demo\\Rapport.csv", "C:\\Users\\Maxime\\Documents\\PRO\\Scolaire\\Cours\\FISE3\\PRI\\Demo", false, true));
	}
}
