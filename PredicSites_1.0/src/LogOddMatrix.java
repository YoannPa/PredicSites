import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.String.*; 

/**
 * class LogOddMatrix.
 */
class LogOddMatrix extends NonMutableDoubleMatrix {
	/**
	 * Reference sum;.
	 */
	private int referenceSum;

	/**
	 * Constructor
	 */
	public LogOddMatrix (CountMatrix matrix) {
		super(matrix) ;
		this.computeLogOdd(matrix);
		this.referenceSum=0;
		for (int ligne=0;ligne<this.numberOfRows;ligne++){
			this.referenceSum += this.getElement(ligne, 0);
		}
	}

	/**
	 * Compute the entropy matrix.
	 */
	private void computeLogOdd(CountMatrix matrix) {
		float Wbi =0;
		int col ;
		int ligne ;
		int n = matrix.referenceSum;
		float entries[][] = new float[matrix.numberOfRows][matrix.numberOfColumns] ;
		
		for(col=0;col<matrix.numberOfColumns;col++){
			for (ligne=0;ligne<matrix.numberOfRows;ligne++){
				System.out.println(matrix.referenceSum);
				float Pbi = (float)matrix.element[ligne][col]/(float)matrix.referenceSum ;
				Wbi = (float) (Math.log10( (Pbi + (1 * 0.25)) / ((n+1))/(0.25)) );
				entries[ligne][col] = Wbi;
			}
		}
		
		for(col=0;col<matrix.numberOfColumns;col++){
			for (ligne=0;ligne<matrix.numberOfRows;ligne++){
				this.element[ligne][col]=entries[ligne][col];
			}
		}
	}
}
