package bioinfa.model;

import java.util.HashMap;
import java.util.Map;

public class SimilarityMatrix {

    private static Map<DNASymbol,Map<DNASymbol,Integer>> similarity;

    /*

        AT77

           A  C  G  T
        A  2 -3 -2 -3
        C -3  5 -3 -2
        G -2 -3  5 -3
        T -3 -2 -3  2

     */

    private static void init(){
        similarity = new HashMap<>();
        Map<DNASymbol,Integer> simForA = new HashMap<>();
        simForA.put(DNASymbol.G,0);
        simForA.put(DNASymbol.C,0);
        simForA.put(DNASymbol.T,0);
        simForA.put(DNASymbol.A,1);
        similarity.put(DNASymbol.A,simForA);

        Map<DNASymbol,Integer> simForG = new HashMap<>();
        simForG.put(DNASymbol.A,0);
        simForG.put(DNASymbol.C,0);
        simForG.put(DNASymbol.T,0);
        simForG.put(DNASymbol.G,1);
        similarity.put(DNASymbol.G,simForG);

        Map<DNASymbol,Integer> simForC = new HashMap<>();
        simForC.put(DNASymbol.A,0);
        simForC.put(DNASymbol.G,0);
        simForC.put(DNASymbol.T,0);
        simForC.put(DNASymbol.C,1);
        similarity.put(DNASymbol.C,simForC);

        Map<DNASymbol,Integer> simForT = new HashMap<>();
        simForT.put(DNASymbol.A,0);
        simForT.put(DNASymbol.G,0);
        simForT.put(DNASymbol.C,0);
        simForT.put(DNASymbol.T,1);
        similarity.put(DNASymbol.T,simForT);

        Map<DNASymbol,Integer> simForEmpty = new HashMap<>();
        simForEmpty.put(DNASymbol.A,0);
        simForEmpty.put(DNASymbol.G,0);
        simForEmpty.put(DNASymbol.C,0);
        simForEmpty.put(DNASymbol.T,0);
        simForEmpty.put(DNASymbol.EMPTY,1);
        similarity.put(DNASymbol.EMPTY,simForEmpty);
    }

    private static void init2(){
        similarity = new HashMap<>();
        Map<DNASymbol,Integer> simForA = new HashMap<>();
        simForA.put(DNASymbol.G,-1);
        simForA.put(DNASymbol.C,-1);
        simForA.put(DNASymbol.T,-1);
        simForA.put(DNASymbol.A,1);
        similarity.put(DNASymbol.A,simForA);

        Map<DNASymbol,Integer> simForG = new HashMap<>();
        simForG.put(DNASymbol.A,-1);
        simForG.put(DNASymbol.C,-1);
        simForG.put(DNASymbol.T,-1);
        simForG.put(DNASymbol.G,1);
        similarity.put(DNASymbol.G,simForG);

        Map<DNASymbol,Integer> simForC = new HashMap<>();
        simForC.put(DNASymbol.A,-1);
        simForC.put(DNASymbol.G,-1);
        simForC.put(DNASymbol.T,-1);
        simForC.put(DNASymbol.C,1);
        similarity.put(DNASymbol.C,simForC);

        Map<DNASymbol,Integer> simForT = new HashMap<>();
        simForT.put(DNASymbol.A,-1);
        simForT.put(DNASymbol.G,-1);
        simForT.put(DNASymbol.C,-1);
        simForT.put(DNASymbol.T,1);
        similarity.put(DNASymbol.T,simForT);

        Map<DNASymbol,Integer> simForEmpty = new HashMap<>();
        simForEmpty.put(DNASymbol.A,-1);
        simForEmpty.put(DNASymbol.G,-1);
        simForEmpty.put(DNASymbol.C,-1);
        simForEmpty.put(DNASymbol.T,-1);
        simForEmpty.put(DNASymbol.EMPTY,1);
        similarity.put(DNASymbol.EMPTY,simForEmpty);
    }



    public static int get(DNASymbol a,DNASymbol b){
        if(similarity == null)
            init2();
        if(b == DNASymbol.EMPTY){
            return 0;
        }
        return similarity.get(a).get(b);
    }

}
