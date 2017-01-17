package bioinfa;

import java.util.Arrays;
import java.util.List;

import bioinfa.model.*;
import bioinfa.util.BioUtils;

public class Main {
	private static final String PROFILE_EXAMPLE_FORMAT = "\nEXAMPLE PROFILE: %s";
	private static final String JOINED_PROFILE_EXAMPLE_FORMAT = "\nEXAMPLE OF TWO JOINED PROFILES: %s + %s";
	
	public static void main(String[] args){
		List<Sequence> sequencesA = Arrays.asList(new Sequence("TCCA"), 
				new Sequence("AC-A"), new Sequence("ACC-"));
		List<Sequence> sequencesB = Arrays.asList(new Sequence("TA-G"), 
				new Sequence("CAT-"), new Sequence("-CCG"));
		
		presentProfiles(sequencesA);
		presentProfiles(sequencesB);
		presentJoinedProfiles(sequencesA, sequencesB);
	}
	
	public static void presentProfiles(List<Sequence> sequences){
		System.out.println(String.format(PROFILE_EXAMPLE_FORMAT, sequences.toString()));
		ProfileService profileService = new ProfileService();	
		ProfileMatrix matrix = profileService.computeProfile(sequences);
		BioUtils.printProfileMatrix(matrix);
	}
	
	public static void presentJoinedProfiles(List<Sequence> sequencesA, List<Sequence> sequencesB){
		System.out.println(String.format(JOINED_PROFILE_EXAMPLE_FORMAT, sequencesA.toString(), sequencesB.toString()));
		ProfileService profileService = new ProfileService();	
		ProfileMatrix matrixA = profileService.computeProfile(sequencesA);
		ProfileMatrix matrixB = profileService.computeProfile(sequencesB);
		ProfileMatrix joinedMatrix = profileService.joinProfiles(matrixA, matrixB);
		BioUtils.printProfileMatrix(joinedMatrix);
	}

}
