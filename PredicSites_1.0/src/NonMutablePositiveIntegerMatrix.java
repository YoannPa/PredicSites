/**
 * class NonMutablePositiveIntegerMatrix.
 */
class NonMutablePositiveIntegerMatrix extends PositiveIntegerMatrix {

	/**
	 * Constructor.
	 */
	public NonMutablePositiveIntegerMatrix(int numberOfRows, int numberOfColumns) {
		super(numberOfRows, numberOfColumns);
	}

	/**
	 * Constructor.
	 */
	public NonMutablePositiveIntegerMatrix(PositiveIntegerMatrix matrix) {
		super(matrix);
	}
}
