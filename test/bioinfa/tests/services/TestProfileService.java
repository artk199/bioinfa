package bioinfa.tests.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bioinfa.ProfileService;
import bioinfa.model.DNASymbol;
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
		
		Map<DNASymbol, List<Double>> matrix = service.computeProfile(Arrays.asList(firstSequence, secondSequence, thirdSequence));
		
		Assert.assertArrayEquals(AValues.toArray(), matrix.get(DNASymbol.A).toArray());
		Assert.assertArrayEquals(TValues.toArray(), matrix.get(DNASymbol.T).toArray());
		Assert.assertArrayEquals(GValues.toArray(), matrix.get(DNASymbol.G).toArray());
		Assert.assertArrayEquals(CValues.toArray(), matrix.get(DNASymbol.C).toArray());
		Assert.assertArrayEquals(EmptyValues.toArray(), matrix.get(DNASymbol.EMPTY).toArray());
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
		
		Map<DNASymbol, List<Double>> matrix = service.computeProfile(Arrays.asList(firstSequence, secondSequence, thirdSequence));
		
		Assert.assertArrayEquals(AValues.toArray(), matrix.get(DNASymbol.A).toArray());
		Assert.assertArrayEquals(TValues.toArray(), matrix.get(DNASymbol.T).toArray());
		Assert.assertArrayEquals(GValues.toArray(), matrix.get(DNASymbol.G).toArray());
		Assert.assertArrayEquals(CValues.toArray(), matrix.get(DNASymbol.C).toArray());
		Assert.assertArrayEquals(EmptyValues.toArray(), matrix.get(DNASymbol.EMPTY).toArray());
	}
}
