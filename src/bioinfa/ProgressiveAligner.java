package bioinfa;

import bioinfa.model.*;
import bioinfa.util.BioUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 17.01.2017.
 */
public class ProgressiveAligner {

    public void alignByProfiles(Multialigment malign1, Multialigment malign2, SimilarityMatrix sim){
        ProfileService profileService = new ProfileService();

        //Wyliczamy profile wielodopasowań
        ProfileMatrix profile1 = profileService.computeProfile(malign1.getSequences());
        ProfileMatrix profile2 = profileService.computeProfile(malign2.getSequences());
        BioUtils.printProfileMatrix(profile1);
        BioUtils.printProfileMatrix(profile2);

        //Prosta wersja Needleman–Wunsch bez profili
        Sequence firstSequence = malign1.getResult();
        Sequence secondSequence = malign2.getResult();

        int[][] matrix = new int[secondSequence.getLength()+1][firstSequence.getLength()+1];

        for (int i = 0; i <= firstSequence.getLength(); i++) {
            matrix[0][i] = i*-1;
        }

        for (int i = 0; i <= secondSequence.getLength(); i++) {
            matrix[i][0] = i*-1;
        }

        for (int i = 1; i <= secondSequence.getLength(); i++) {
            for (int j = 1; j <= firstSequence.getLength(); j++) {
                int s = 1;
                int w = 0;
                if(firstSequence.getSymbol(j-1) != secondSequence.getSymbol(i-1)) {
                    s = -1;
                }
                matrix[i][j] = getMax(matrix[i-1][j-1]+s,matrix[i][j-1]+w,matrix[i-1][j]+w);
            }
        }

        LinkedList<DNASymbol> firstList = new LinkedList<>();
        LinkedList<DNASymbol> secondList = new LinkedList<>();

        BioUtils.printNeedlemanWunschGrid(firstSequence,secondSequence,matrix);
        int i = secondSequence.getLength(),j = firstSequence.getLength();
        while(i != 0 || j!= 0){
            int direction = 0;
            if(i > 0 && j > 0) {
                direction = getDirection(matrix[i - 1][j - 1], matrix[i][j - 1], matrix[i - 1][j]);
            }
            if(i == 0){
                direction = 1;
            }
            if(j == 0){
                direction = 2;
            }
            switch (direction){
                case 0:
                    firstList.addFirst(firstSequence.getSymbol(j-1));
                    secondList.addFirst(secondSequence.getSymbol(i-1));
                    i--;
                    j--;
                    break;
                case 1:
                    firstList.addFirst(firstSequence.getSymbol(j-1));
                    secondList.addFirst(DNASymbol.EMPTY);
                    j--;
                    break;
                case 2:
                    firstList.addFirst(DNASymbol.EMPTY);
                    secondList.addFirst(secondSequence.getSymbol(i-1));
                    i--;
                    break;
            }
        }

        Sequence aligned = new Sequence();
        aligned.setSymbols(firstList);
        System.out.println(aligned);
        aligned.setSymbols(secondList);
        System.out.println(aligned);
    }


    private int getDirection(int i, int i1, int i2) {
        if(i >= i1 && i >= i2)
            return 0;
        if(i1 >= i2)
            return 1;
        return 2;
    }

    private int getMax(int i, int i1, int i2) {
        int max = Math.max(i,i1);
        return  Math.max(max,i2);
    }

}
