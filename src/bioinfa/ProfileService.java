package bioinfa;

import java.util.List;

import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;

public class ProfileService {
	public ProfileMatrix computeProfile(List<Sequence> sequences){
		int sequencesLength = sequences.get(0).getSymbols().size();
		ProfileMatrix matrix = new ProfileMatrix();
		matrix.initMatrix(sequencesLength);

		for(Sequence sequence : sequences){
			matrix.addSequence(sequence);
		}
		
		return matrix;
	}
}
