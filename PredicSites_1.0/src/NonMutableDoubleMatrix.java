/**
 * class NonMutableDoubleMatrix.
 */
class NonMutableDoubleMatrix extends DoubleMatrix {

	/**
	 * Constructor.
	 */
	public NonMutableDoubleMatrix(int numberOfRows, int numberOfColumns) {
		super(numberOfRows, numberOfColumns);
	}

	/**
	 * Constructor.
	 */
	public NonMutableDoubleMatrix(DoubleMatrix matrix) {
		super(matrix);
	}

	/**
	 * Constructor.
	 */
	public NonMutableDoubleMatrix(PositiveIntegerMatrix matrix) {
		super(matrix);
	}
}
