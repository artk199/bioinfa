package bioinfa.model;

import java.util.List;

public class Multialigment {
	private List<Sequence> sequences;
	private Sequence result;
	private SimilarityMatrix similarityMatrix;
	
	public List<Sequence> getSequences() {
		return sequences;
	}
	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}
	public Sequence getResult() {
		return result;
	}
	public void setResult(Sequence result) {
		this.result = result;
	}
	public SimilarityMatrix getSimilarityMatrix() {
		return similarityMatrix;
	}
	public void setSimilarityMatrix(SimilarityMatrix similarityMatrix) {
		this.similarityMatrix = similarityMatrix;
	}
}
