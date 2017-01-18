package bioinfa;

import java.util.List;

import bioinfa.model.DNASymbol;
import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;

public class ProfileService {
	
	// Returns profile of multialigment (list of sequences)
	public ProfileMatrix computeProfile(List<Sequence> sequences){
		ProfileMatrix simpleMatrix = getSimpleProfileMatrix(sequences);
		ProfileMatrix result = computeFrequenciesForMatrix(simpleMatrix, sequences.size());
		return result;
	}
	
	// Initializes profile matrix for list of sequences (in each cell there are counts of each symbol)
	private ProfileMatrix getSimpleProfileMatrix(List<Sequence> sequences){
		int sequencesLength = sequences.get(0).getSymbols().size();
		ProfileMatrix matrix = new ProfileMatrix();
		matrix.initMatrix(sequencesLength);
		matrix.setSequencesCount(sequences.size());
		
		// Count occurencies for each symbol
		for(Sequence sequence : sequences){
			List<DNASymbol> symbols = sequence.getSymbols();
			int i = 0;
			for(DNASymbol symbol : symbols){
				List<Double> values = matrix.getValuesForSymbol(symbol);
				values.set(i, values.get(i) + 1);
				i++;
			}
		}
		return matrix;
	}
	
	// For every symbol's cell sets count / sequenciesCount (frequency)
	private ProfileMatrix computeFrequenciesForMatrix(ProfileMatrix matrix, int sequenciesCount){
		ProfileMatrix result = new ProfileMatrix();
		result.initMatrix(matrix.getSequencesLength());
		result.setSequencesCount(matrix.getSequencesCount());
		List<DNASymbol> symbols = matrix.getSymbols();
		
		// Change occurencies for each symbol with frequencies
		for(DNASymbol symbol : symbols){
			List<Double> values = matrix.getValuesForSymbol(symbol);
			for(int i = 0; i < values.size(); i++){
				result.setValue(symbol, i, values.get(i) / sequenciesCount);
			}
		}
		return result;
	}
	
	// Returns new profile matrix by joining two profiles
	public ProfileMatrix joinProfiles(ProfileMatrix profileA, ProfileMatrix profileB){
		ProfileMatrix matrix = new ProfileMatrix();
		matrix.initMatrix(profileA.getSequencesLength());
		
		List<DNASymbol> symbols = profileA.getSymbols();
		for(DNASymbol symbol : symbols){
			List<Double> valuesA = profileA.getValuesForSymbol(symbol);
			List<Double> valuesB = profileB.getValuesForSymbol(symbol);
			
			// Use freq = (freqA * numA + freqB + numB)/(numA + numB)
			for(int i = 0; i < valuesA.size(); i++){
				Double freqSum = valuesA.get(i) * profileA.getSequencesCount() + 
						 valuesB.get(i) * profileB.getSequencesCount();
				Double value = freqSum / (profileA.getSequencesCount() + profileB.getSequencesCount());
				matrix.setValue(symbol, i, value);
			}
		}
		return matrix;
	}
}
