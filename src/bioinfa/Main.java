package bioinfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bioinfa.consensus.ConsensusWordResolver;
import bioinfa.consensus.SimpleConsensusWordResolver;
import bioinfa.model.*;
import bioinfa.util.BioUtils;

public class Main {
	private static final String PROFILE_EXAMPLE_FORMAT = "\nEXAMPLE PROFILE: %s";
	private static final String JOINED_PROFILE_EXAMPLE_FORMAT = "\nEXAMPLE OF TWO JOINED PROFILES: %s + %s";
	private static final String CONSENSUS_EXAMPLE_FORMAT = "\nEXAMPLE CONSENSUS OF: %s";
	private static final String MULTIALIGMENT_EXAMPLE_FORMAT = "\nEXAMPLE MULTIALIGMENT:";
	
	public static void main(String[] args){
		// Init example sequences
		List<Sequence> sequencesA = Arrays.asList(
				new Sequence("TCCA"), 
				new Sequence("AC-A"), 
				new Sequence("ACC-")
		);
		List<Sequence> sequencesB = Arrays.asList(
				new Sequence("TA-G"), 
				new Sequence("CAT-"), 
				new Sequence("-CCG")
		);
		List<Sequence> sequencesC = Arrays.asList(
				new Sequence("TAACG"),
				new Sequence("CATT"),
				new Sequence("ACCG")
		);

		// Init example multialigments
		Multialigment m1 = BioUtils.getMultialigmentFromSequencies("AAAAAGCA");
		Multialigment m2 = BioUtils.getMultialigmentFromSequencies("TCAAAGAGAT");
		Multialigment m3 = BioUtils.getMultialigmentFromSequencies("AAACATAG", "CCAAATGC");
		Multialigment m4 = BioUtils.getMultialigmentFromSequencies("ATAG", "CCAA");
		
		// Display results of examples
		presentProfiles(sequencesA);
		presentProfiles(sequencesB);
		presentJoinedProfiles(sequencesA, sequencesB);
		presentConsensus(sequencesA);
		presentConsensus(sequencesB);
		presentAligmentByProfile(m1,m2);
		presentUGMAAligment(sequencesC);
		presentUGMAAligmentForMutlialigments(Arrays.asList(m1, m2, m3, m4));
	}
	
	private static void presentUGMAAligment(List<Sequence> sequences){
		System.out.println("\nPROGRESSIVE ALIGMENT EXAMPLE");
		System.out.println("... for sequences: " + sequences + "\n");
		UGMAAlignerService service = new UGMAAlignerService();
		List<Multialigment> multialigments = new ArrayList<>();
		for(Sequence seq : sequences){
			Multialigment m = new Multialigment();
			m.getSequences().add(seq);
			multialigments.add(m);
		}
		Multialigment result = service.alignProgressiveWithUPGMA(multialigments);
		System.out.println(result);
	}
	
	private static void presentUGMAAligmentForMutlialigments(List<Multialigment> multialigments){
		System.out.println("\nPROGRESSIVE ALIGMENT EXAMPLE");
		System.out.println("... for multialigments: " + multialigments + "\n");
		UGMAAlignerService service = new UGMAAlignerService();
		Multialigment result = service.alignProgressiveWithUPGMA(multialigments);
		System.out.println(result);
	}

	private static void presentAligmentByProfile(Multialigment m1, Multialigment m2) {
		System.out.println(MULTIALIGMENT_EXAMPLE_FORMAT);
		ProgressiveAligner aligner = new ProgressiveAligner();
		System.out.println();
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(aligner.alignByProfiles(m1,m2));
	}

	private static void presentConsensus(List<Sequence> sequences) {
		System.out.println(String.format(CONSENSUS_EXAMPLE_FORMAT, sequences.toString()));
		ConsensusWordResolver resolver = new SimpleConsensusWordResolver();
		Sequence consensus = resolver.resolve(sequences);
		System.out.println();
		sequences.forEach(s -> System.out.println(s));
		System.out.println();
		System.out.println(consensus);
	}

	private static void presentProfiles(List<Sequence> sequences){
		System.out.println(String.format(PROFILE_EXAMPLE_FORMAT, sequences.toString()));
		ProfileService profileService = new ProfileService();	
		ProfileMatrix matrix = profileService.computeProfile(sequences);
		BioUtils.printProfileMatrix(matrix);
	}
	
	private static void presentJoinedProfiles(List<Sequence> sequencesA, List<Sequence> sequencesB){
		System.out.println(String.format(JOINED_PROFILE_EXAMPLE_FORMAT, sequencesA.toString(), sequencesB.toString()));
		ProfileService profileService = new ProfileService();	
		ProfileMatrix matrixA = profileService.computeProfile(sequencesA);
		ProfileMatrix matrixB = profileService.computeProfile(sequencesB);
		ProfileMatrix joinedMatrix = profileService.joinProfiles(matrixA, matrixB);
		BioUtils.printProfileMatrix(joinedMatrix);
	}

}
