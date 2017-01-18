package bioinfa.tests.services;

import bioinfa.ProgressiveAligner;
import bioinfa.model.Multialigment;
import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;
import bioinfa.model.SimilarityMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 17.01.2017.
 */
public class ProgressiveAlignerTest {

    ProgressiveAligner aligner;

    @Before
    public void init(){
        aligner = new ProgressiveAligner();
    }

    @Test
    public void testAlign(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("TCCA"));
        multialigment.getSequences().add(new Sequence("AC-A"));
        multialigment.getSequences().add(new Sequence("ACC-"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("TA-G"));
        multialigment2.getSequences().add(new Sequence("CAT-"));
        multialigment2.getSequences().add(new Sequence("-CCG"));

        aligner.alignByProfiles(multialigment,multialigment2);
    }

    @Test
    public void testAlign2(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("GGTACCAAATAGAA"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("GGCACCAAACAGAA"));

        aligner.alignByProfiles(multialigment,multialigment2);
    }

    @Test
    public void testAlign3(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("ACCGGTGACCAGTTGACCAGT"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("ATCGGTACCGGTAGAAGT"));

        System.out.println(aligner.alignByProfiles(multialigment,multialigment2));
    }

    @Test
    public void testAlign4(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("AAAAAGCA"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("TCAAAGAGAT"));

        Multialigment m = aligner.alignByProfiles(multialigment,multialigment2);

        Multialigment multialigment3 = new Multialigment();
        multialigment3.getSequences().add(new Sequence("AAAACGGCT"));

        System.out.println(aligner.alignByProfiles(m,multialigment3));
    }

    @Test
    public void testAlign5(){

        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("AAAAAAA--------------"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("GGG----------------GGGG"));

        System.out.println(aligner.alignByProfiles(multialigment,multialigment2));
    }

    @Test
    public void testAlign6(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("GGG"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("AAAGGG"));
        System.out.println(aligner.alignByProfiles(multialigment,multialigment2));
    }

    @Test
    public void testAlign7(){
        List<String> sequences = new LinkedList<>();
        sequences.add("ATCGGTACCGGTAGAAGT");
        sequences.add("GGTACCAAATAGAA");
        sequences.add("GGTACCAAATAGAA");
        sequences.add("GGCACCAAACAGAA");

        Multialigment result = new Multialigment();
        result.getSequences().add(new Sequence("ACCGGTGACCAGTTGACCAGT"));
        for (String s : sequences){
            Multialigment m = new Multialigment();
            m.getSequences().add(new Sequence(s));
            result = aligner.alignByProfiles(m,result);
        }

        System.out.println(result);
    }



}
