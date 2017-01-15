package bioinfa;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bioinfa.model.*;

public class Main {
	private static final String PROFILE_EXAMPLE_FORMAT = "\nEXAMPLE PROFILE: %s";
	private static final String PROFILE_SYMBOL_FORMAT = "[%s]\t- %s";
	
	public static void main(String[] args){
		presentProfiles(Arrays.asList(new Sequence("TCCA"), 
				new Sequence("AC-A"), new Sequence("ACC-")));
		
		presentProfiles(Arrays.asList(new Sequence("TA-G"), 
				new Sequence("CAT-"), new Sequence("-CCG")));
	}
	
	public static void presentProfiles(List<Sequence> sequences){
		System.out.println(String.format(PROFILE_EXAMPLE_FORMAT, sequences.toString()));
		ProfileService profileService = new ProfileService();	
		Map<DNASymbol, List<Double>> matrix = profileService.computeProfile(sequences);
		printProfileMatrix(matrix);
	}
	
	private static void printProfileMatrix(Map<DNASymbol, List<Double>> matrix){
		for(Entry<DNASymbol, List<Double>> mapEntry : matrix.entrySet()){
			System.out.println(String.format(PROFILE_SYMBOL_FORMAT, mapEntry.getKey(), mapEntry.getValue().toString()));
		}
	}
}
