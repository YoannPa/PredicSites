/**
 * class MutableDoubleMatrix.
 */
class MutableDoubleMatrix extends DoubleMatrix {

	/**
	 * Constructor.
	 */
	public MutableDoubleMatrix(int numberOfRows, int numberOfColumns) {
		super(numberOfRows, numberOfColumns);
	}

	/**
	 * Constructor.
	 */
	public MutableDoubleMatrix(DoubleMatrix matrix) {
		super(matrix);
	}

	/**
	 * Constructor.
	 */
	public MutableDoubleMatrix(PositiveIntegerMatrix matrix) {
		super(matrix);
	}

	/**
	 * Set the element at row i column j.
	 */
	public void setElement(int i, int j, double value) {
		this.element[i][j]=value;
	}
}
