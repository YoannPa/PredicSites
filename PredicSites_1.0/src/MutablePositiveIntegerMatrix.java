/**
 * class MutablePositiveIntegerMatrix.
 */
class MutablePositiveIntegerMatrix extends PositiveIntegerMatrix {

	/**
	 * Constructor.
	 */
	public MutablePositiveIntegerMatrix(int numberOfRows, int numberOfColumns) {
		super(numberOfRows, numberOfColumns);
	}

	/**
	 * Constructor.
	 */
	public MutablePositiveIntegerMatrix(PositiveIntegerMatrix matrix) {
		super(matrix);
	}

	/**
	 * Set the element at row i column j.
	 */
	public void setElement(int i, int j, int value) {
		if(value<0){
			System.out.println("Valeur negative :"+value);
		}
		this.element[i][j]=value;
	}
}
