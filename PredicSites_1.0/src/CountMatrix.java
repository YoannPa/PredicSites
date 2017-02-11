/**
 * class CountMatrix.
 */
class CountMatrix extends NonMutablePositiveIntegerMatrix {

	/**
	 * Referecence sum.
	 */
	protected int referenceSum;

	/**
	 * Constructor.
	 */
	CountMatrix(PositiveIntegerMatrix matrix) throws BadCountException {
		super(matrix);
		BadCountException e = new  BadCountException () ;
		try {
			if (matrix.numberOfRows != 4){
				throw e ;
			}
			checkColumns();
			referenceSum=this.getReferenceSum();
		}
		catch (Exception eCatch){
			System.out.println(eCatch.getMessage());
		}
	}

	/**
	 * Return the reference sum.	
	 */
	public int getReferenceSum() {
		int colTotal = 0 ;
		for (int ligne=0;ligne<this.numberOfRows;ligne++){
			colTotal += this.getElement(ligne, 0);
		}
		return colTotal ;
	}

	/**
	 * Raise BadCountException in case all columns do not sum to the same value.
	 */
	private void checkColumns() throws BadCountException {
		int ReferenceSum = this.getReferenceSum();
		int col ;
		int ligne ;
		int colTotal[] = null ;
		BadCountException e = new  BadCountException () ;
		try{
			for (col=0;col<this.numberOfColumns;col++){
				colTotal[col] = 0 ;
				for (ligne=0;ligne<this.numberOfRows;ligne++){
					if(this.getElement(ligne, col)<0){
						throw e ;
					}
					colTotal[col] += this.getElement(ligne, col);
				}
			}
			for (int i=0;i<this.numberOfColumns;i++){
				if(ReferenceSum!=colTotal[i]){
					throw e ;
				}
			}
		}
		catch (Exception eCatch){
			System.out.println(eCatch.getMessage());
		}
	}
}
