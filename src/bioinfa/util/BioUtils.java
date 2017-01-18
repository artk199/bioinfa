package bioinfa.util;

import java.util.ArrayList;
import java.util.List;

import bioinfa.model.DNASymbol;
import bioinfa.model.Multialigment;
import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;

/**
 * Created by Artur on 15.01.2017.
 */
public class BioUtils {

    private static final String PROFILE_SYMBOL_FORMAT = "[%s]\t- %s";

    public static void printProfileMatrix(ProfileMatrix matrix){
        List<DNASymbol> symbols = matrix.getSymbols();
        for(DNASymbol symbol : symbols){
            String symbolValues = matrix.getValuesForSymbol(symbol).toString();
            System.out.println(String.format(PROFILE_SYMBOL_FORMAT, symbol, symbolValues));
        }
    }

    public static void printNeedlemanWunschGrid(Multialigment firstSequence, Multialigment secondSequence, double[][] matrix) {
        System.out.println("\n\n Neddleman-Wunsch Grid\n\n");
        /*System.out.println("  "+firstSequence.toString());
        System.out.print(" ");
        for (int i = 0; i < firstSequence.getLength(); i++) {
            System.out.print(matrix[0][i]);
        }
        System.out.print("\n");
        for (int i = 0; i < secondSequence.getLength(); i++) {
            System.out.print(secondSequence.getSymbol(i).getSymbol());
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }*/
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%4.4f ", matrix[row][col]);
            }
            System.out.println();
        }
    }
    
    public static Multialigment getMultialigmentFromSequencies(String ... sequences){
    	Multialigment multialigment = new Multialigment();
    	for(String seq : sequences){
    		multialigment.getSequences().add(new Sequence(seq));
		}
    	return multialigment;
    }
    
    public static List<Sequence> createSequencies(String ... sequences){
    	List<Sequence> sequencesList = new ArrayList<>();
    	for(String seq : sequences){
    		sequencesList.add(new Sequence(seq));
    	}
    	return sequencesList;
    }
    
    public static String writeMutlialigments(List<Multialigment> multialigments){
    	StringBuilder sb = new StringBuilder();
    	
    	for(Multialigment m : multialigments){
    		sb.append("[").append(writeMultialigment(m)).append("]\n");
    	}
    	
    	return sb.toString();
    }
    
    public static String writeMultialigment(Multialigment multialigment){
    	StringBuilder str = new StringBuilder();
		for(int i = 0; i < multialigment.getSequences().size(); i++){
			str.append(multialigment.getSequences().get(i));
			if(i < multialigment.getSequences().size() - 1){
				str.append(", ");
			}
		}
		return str.toString();
    }
}
