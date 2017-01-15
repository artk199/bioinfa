package bioinfa.tests.services;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bioinfa.ProfileService;
import bioinfa.model.DNASymbol;
import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;

public class TestProfileService {
	private ProfileService service;
	
	@Before
	public void init(){
		service = new ProfileService();
	}
	
	@Test
	public void testFirstExample(){
		List<Double> AValues = Arrays.asList(0.0, 2.0/3, 0.0, 0.0);
		List<Double> TValues = Arrays.asList(1.0/3, 0.0, 1.0/3, 0.0);
		List<Double> GValues = Arrays.asList(0.0, 0.0, 0.0, 2.0/3);
		List<Double> CValues = Arrays.asList(1.0/3, 1.0/3, 1.0/3, 0.0);
		List<Double> EmptyValues = Arrays.asList(1.0/3, 0.0, 1.0/3, 1.0/3);
		
		Sequence firstSequence = new Sequence("TA-G");
		Sequence secondSequence = new Sequence("CAT-");
		Sequence thirdSequence = new Sequence("-CCG");
		
		ProfileMatrix matrix = service.computeProfile(Arrays.asList(firstSequence, secondSequence, thirdSequence));
		
		Assert.assertArrayEquals(AValues.toArray(), matrix.getValuesForSymbol(DNASymbol.A).toArray());
		Assert.assertArrayEquals(TValues.toArray(), matrix.getValuesForSymbol(DNASymbol.T).toArray());
		Assert.assertArrayEquals(GValues.toArray(), matrix.getValuesForSymbol(DNASymbol.G).toArray());
		Assert.assertArrayEquals(CValues.toArray(), matrix.getValuesForSymbol(DNASymbol.C).toArray());
		Assert.assertArrayEquals(EmptyValues.toArray(), matrix.getValuesForSymbol(DNASymbol.EMPTY).toArray());
	}
	
	@Test
	public void testSecondExample(){
		List<Double> AValues = Arrays.asList(2.0/3, 0.0, 0.0, 2.0/3);
		List<Double> TValues = Arrays.asList(1.0/3, 0.0, 0.0, 0.0);
		List<Double> GValues = Arrays.asList(0.0, 0.0, 0.0, 0.0);
		List<Double> CValues = Arrays.asList(0.0, 1.0, 2.0/3, 0.0);
		List<Double> EmptyValues = Arrays.asList(0.0, 0.0, 1.0/3, 1.0/3);
		
		Sequence firstSequence =  new Sequence("TCCA");
		Sequence secondSequence = new Sequence("AC-A");
		Sequence thirdSequence =  new Sequence("ACC-");
		
		ProfileMatrix matrix = service.computeProfile(Arrays.asList(firstSequence, secondSequence, thirdSequence));
		
		Assert.assertArrayEquals(AValues.toArray(), matrix.getValuesForSymbol(DNASymbol.A).toArray());
		Assert.assertArrayEquals(TValues.toArray(), matrix.getValuesForSymbol(DNASymbol.T).toArray());
		Assert.assertArrayEquals(GValues.toArray(), matrix.getValuesForSymbol(DNASymbol.G).toArray());
		Assert.assertArrayEquals(CValues.toArray(), matrix.getValuesForSymbol(DNASymbol.C).toArray());
		Assert.assertArrayEquals(EmptyValues.toArray(), matrix.getValuesForSymbol(DNASymbol.EMPTY).toArray());
	}
}
