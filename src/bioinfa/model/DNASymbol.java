package bioinfa.model;

public enum DNASymbol {
	A("A"), G("G"), C("C"), T("T"), EMPTY("-");
	
	private final String symbol;
	
	private DNASymbol(String symbol){
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
}
