package bioinfa;

import java.util.ArrayList;
import java.util.List;
import bioinfa.model.BestUGMAPair;
import bioinfa.model.DNASymbol;
import bioinfa.model.Multialigment;
import bioinfa.model.Sequence;
import bioinfa.model.SimilarityMatrix;

public class UGMAAlignerService {
	public Multialigment alignProgressiveWithUGMA(List<Sequence> sequences){
		int size = sequences.size();
		double UGMAMatrix[][] = initExampleUGMAMatrix();//initUGMA(sequences);
		List<BestUGMAPair> bestPairsInOrder = new ArrayList<>();
			
//		while(UGMAMatrix.length > 1){
			printUGMAMatrix(UGMAMatrix, sequences);
			
			BestUGMAPair bestPair = findBestAligment(UGMAMatrix);
			System.out.println("\nBEST PAIR: " + bestPair.toString());
			bestPairsInOrder.add(bestPair);
			UGMAMatrix = updateUGMAMatrix(UGMAMatrix, bestPair);
			
			printUGMAMatrix(UGMAMatrix, sequences);
			
			 bestPair = findBestAligment(UGMAMatrix);
			System.out.println("\nBEST PAIR: " + bestPair.toString());
			bestPairsInOrder.add(bestPair);
			UGMAMatrix = updateUGMAMatrix(UGMAMatrix, bestPair);
			
			printUGMAMatrix(UGMAMatrix, sequences);
//		}
		
		return null;
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
	
	private void printUGMAMatrix(double matrix[][], List<Sequence> sequences){
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
	
	// Init UGMA table with n x n dimension and write 0 to every cell
	private double[][] initUGMA(List<Sequence> sequences){
		int n = sequences.size();
		double result[][] = new double [n][n];
		
		for(int i = 0; i < n - 1; i++){
			for(int j = 0; j < n; j++){
				if(i == j){
					result[i][j] = 0;
					result[j][i] = 0;
				}
				else{
					Sequence seqA = sequences.get(i);
					Sequence seqB = sequences.get(j);
					double diff = computeDifferenceBetweenSequences(seqA, seqB);
					
					result[i][j] = result[j][i] = diff;
				}
			}
		}
		
		return result;
	}
	
	// Returns pair with lowest difference
	private BestUGMAPair findBestAligment(double matrix[][]){
		BestUGMAPair bestPair = new BestUGMAPair();
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
	private double[][] updateUGMAMatrix(double matrix[][], BestUGMAPair bestPair){	
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
	private double computeDifferenceBetweenSequences(Sequence sequenceA, Sequence sequenceB){
		Sequence longerSeq = sequenceA.getLength() > sequenceB.getLength() ? sequenceA : sequenceB;
		Sequence smallerSeq = sequenceA.getLength() > sequenceB.getLength() ? sequenceB : sequenceA;
		
		double diff = 0;
		for(int i = 0; i < longerSeq.getLength(); i++){
			DNASymbol firstSymbol = longerSeq.getSymbol(i);
			DNASymbol secondSymbol = smallerSeq.getLength() > i ? smallerSeq.getSymbol(i) : DNASymbol.EMPTY;
			
			diff += -1 * SimilarityMatrix.get(firstSymbol, secondSymbol);
		}
		
		return diff;
	}
}
