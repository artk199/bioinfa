package bioinfa;

import java.util.ArrayList;
import java.util.List;

import bioinfa.consensus.ConsensusWordResolver;
import bioinfa.consensus.SimpleConsensusWordResolver;
import bioinfa.model.BestUPGMAPair;
import bioinfa.model.DNASymbol;
import bioinfa.model.Multialigment;
import bioinfa.model.Sequence;

public class UGMAAlignerService {
	private ProgressiveAligner aligner = new ProgressiveAligner();
	private ConsensusWordResolver consensusWordResolver = new SimpleConsensusWordResolver();
	
	// Returns multialigment with method UPGMA
	public Multialigment alignProgressiveWithUPGMA(List<Multialigment> aligments){
		double upgmaMatrix[][] = initUPGMA(aligments);//initExampleUGMAMatrix();//
		List<Multialigment> multialigments = new ArrayList<>(aligments);
			
		while(multialigments.size() > 1){
			printUPGMAMatrix(upgmaMatrix, aligments);
			BestUPGMAPair bestPair = findBestAligment(upgmaMatrix);
			upadeAlignedSequences(multialigments, bestPair);
			upgmaMatrix = updateUGMAMatrix(upgmaMatrix, bestPair);	
		}
		
		return multialigments.get(0);
	}
	
	// Updates first sequence with aligned one and removes second from list
	private void upadeAlignedSequences(List<Multialigment> alignedSequences, BestUPGMAPair bestPair){
		Multialigment firstSeq = alignedSequences.get(bestPair.getFirstPosition());
		Multialigment secondSeq = alignedSequences.get(bestPair.getSecondPosition());
		Multialigment multialigment = aligner.alignByProfiles(firstSeq, secondSeq);
		alignedSequences.set(bestPair.getFirstPosition(), multialigment);
		alignedSequences.remove(bestPair.getSecondPosition());
	}

	// Init UGMA table with n x n dimension and compute differencies between multialigments
	private double[][] initUPGMA(List<Multialigment> aligments){
		int n = aligments.size();
		double result[][] = new double [n][n];
		
		for(int i = 0; i < n - 1; i++){
			for(int j = 0; j < n; j++){
				if(i == j){
					result[i][j] = 0;
					result[j][i] = 0;
				}
				else{
					Multialigment m1 = aligments.get(i);
					Multialigment m2 = aligments.get(j);
					double diff = computeDifferenceBetweenSequences(m1, m2);
					
					result[i][j] = result[j][i] = diff;
				}
			}
		}
		
		return result;
	}
	
	// Returns pair with lowest difference
	private BestUPGMAPair findBestAligment(double matrix[][]){
		BestUPGMAPair bestPair = new BestUPGMAPair();
		bestPair.setDifference(Double.MAX_VALUE);
		
		int n = matrix.length;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n && i != j; j++){
				double difference = matrix[i][j];
				if(bestPair.getDifference() > difference){
					bestPair.setDifference(difference);
					bestPair.setFirstPosition(i < j ? i : j);
					bestPair.setSecondPosition(i < j ? j : i);
				}
			}
		}
		
		return bestPair;
	}
	
	// Updates every other cell with average between distances
	private double[][] updateUGMAMatrix(double matrix[][], BestUPGMAPair bestPair){	
		int n = matrix.length;
		int a = bestPair.getFirstPosition();
		int b = bestPair.getSecondPosition();
		
		// Because we join one column and verse, we should return smaller array
		double result[][] = new double[n - 1][n - 1];

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i != b && j != b && i != j){
					int x = i > b ? i - 1 : i;
					int y = j > b ? j - 1 : j;
					
					double newValue = matrix[i][j];
					
					if(x == y){
						newValue = 0;
					}
					else if(i == a){
						newValue = (matrix[a][j] + matrix[b][j]) / 2;
					}
					else if(j==a){
						newValue = (matrix[i][a] + matrix[i][b]) / 2;
					}
					result[x][y] = newValue;  
				}
			}
		}
	
		return result;
	}
	
	// Returns double - information about how different are sequences (the less result, the less different)
	public double computeDifferenceBetweenSequences(Multialigment m1, Multialigment m2){
		Sequence cons1 = consensusWordResolver.resolve(m1.getSequences());
		Sequence cons2 = consensusWordResolver.resolve(m2.getSequences());

		int length = cons1.getLength() < cons2.getLength()?cons1.getLength():cons2.getLength();
		int score = cons1.getLength() > cons2.getLength()?cons1.getLength():cons2.getLength();

		for (int i = 0; i < length; i++) {
			DNASymbol s1 = cons1.getSymbol(i);
			DNASymbol s2 = cons2.getSymbol(i);
			if(s1 == s2)
				score--;
		}

		return score;
	}
	
	private double[][] initExampleUGMAMatrix(){
		double result[][] = {
			{0.0, 17.0, 21.0, 31.0, 23.0}, 
			{17.0, 0.0, 30.0, 34.0, 21.0}, 
			{21.0, 30.0, 0.0, 28.0, 39.0},
			{31.0, 34.0, 28.0, 0.0, 43.0},
			{23.0, 21.0, 39.0, 43.0, 0.0}
		};
		return result;
	}
	
	private void printUPGMAMatrix(double matrix[][], List<Multialigment> sequences){
		System.out.println("\nUGMA MATRIX: \n");
		
		int n = matrix.length;
		//System.out.print(" \t");
		//for(int i = 0; i < n; i++){
		//	System.out.print(sequences.get(i) + "\t");
		//}
		System.out.println("");
		for(int i = 0; i < n; i++){
			//System.out.print(sequences.get(i) + "\t");
			for(int j = 0; j < n; j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println("");
		}
	}
}
