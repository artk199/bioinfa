package bioinfa.tests.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import bioinfa.model.DNASymbol;
import bioinfa.model.Sequence;

public class TestSequence{
	@Test
	public void convertingToSequenceFromString(){
		String stringSequence = "TAGC";
		List<DNASymbol> listSequence = Arrays.asList(DNASymbol.T, DNASymbol.A, DNASymbol.G, DNASymbol.C);
		Sequence sequence = new Sequence();
		
		sequence.setSymbols(stringSequence);
		List<DNASymbol> result = sequence.getSymbols();
		
		Assert.assertArrayEquals(listSequence.toArray(), result.toArray());
	}
}
