package bioinfa.tests.services;

import bioinfa.consensus.SimpleConsensusWordResolver;
import bioinfa.model.Sequence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Artur on 15.01.2017.
 */
public class SimpleConsensusWordResolverTest {

    @Test
    public void testSimpleExample() throws Exception {
        List<Sequence> sequences = new ArrayList<>();
        sequences.add(new Sequence("AAA"));
        sequences.add(new Sequence("AAG"));
        sequences.add(new Sequence("GGT"));

        SimpleConsensusWordResolver simpleConsensusWordResolver = new SimpleConsensusWordResolver();
        Sequence result = simpleConsensusWordResolver.resolve(sequences);

        assertEquals("RRD", result.toString());
    }

    @Test
    public void testExample() throws Exception {
        List<Sequence> sequences = new ArrayList<>();
        sequences.add(new Sequence("--AAA-AA-GCA"));
        sequences.add(new Sequence("TCAAAGA--GAT"));
        sequences.add(new Sequence("--AAA-ACGGCT"));

        SimpleConsensusWordResolver simpleConsensusWordResolver = new SimpleConsensusWordResolver();
        Sequence result = simpleConsensusWordResolver.resolve(sequences);

        assertEquals("TCAAAGAMGGMW", result.toString());
    }


}