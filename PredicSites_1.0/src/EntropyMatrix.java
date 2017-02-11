import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.String.*; 

/**
 * class EntropyMatrix.
 */
class EntropyMatrix extends NonMutableDoubleMatrix {
	/**
	 * Reference sum;.
	 */
	private int referenceSum;

	/**
	 * Constructor
	 */
	public EntropyMatrix (CountMatrix matrix) {
		super(matrix) ;
		this.computeEntropy(matrix);
		this.referenceSum=0;
		for (int ligne=0;ligne<this.numberOfRows;ligne++){
			this.referenceSum += this.getElement(ligne, 0);
		}
	}

	/**
	 * Compute the entropy matrix.
	 */
	private void computeEntropy(CountMatrix matrix) {
		int col ;
		int ligne ;
		float coefCol[] = new float[matrix.numberOfColumns] ;
		float coefA ;
		float coefC ;
		float coefG ;
		float coefT ;

		// Compute coefficients.
		for(col=0;col<matrix.numberOfColumns;col++){
			coefCol[col]=0 ;
			coefA=0 ;
			coefC=0 ;
			coefG=0 ;
			coefT=0 ;
			if(matrix.element[0][col]!=0){
				coefA=(float)(((float)matrix.element[0][col]/(float)matrix.referenceSum)*(Math.log10(matrix.element[0][col]/(float)matrix.referenceSum))+Math.log10(5));
			}
			if(matrix.element[1][col]!=0){
				coefC=(float)(((float)matrix.element[1][col]/(float)matrix.referenceSum)*(Math.log10(matrix.element[1][col]/(float)matrix.referenceSum))+Math.log10(5));
			}
			if(matrix.element[2][col]!=0){
				coefG=(float)(((float)matrix.element[2][col]/(float)matrix.referenceSum)*(Math.log10(matrix.element[2][col]/(float)matrix.referenceSum))+Math.log10(5));
			}
			if(matrix.element[3][col]!=0){
				coefT=(float)(((float)matrix.element[3][col]/(float)matrix.referenceSum)*(Math.log10(matrix.element[3][col]/(float)matrix.referenceSum))+Math.log10(5));
			}
			coefCol[col]=(float) ((100.0/(Math.log10(5))*(coefA+coefC+coefG+coefT)));
		}

		// Compute the maximum of each column.
		float colMax[] = new float[matrix.numberOfColumns] ;
		for(col=0;col<matrix.numberOfColumns;col++){
			for (ligne=0;ligne<matrix.numberOfRows;ligne++){
				if (colMax[col]<matrix.getElement(ligne, col)){
					colMax[col] = (float) matrix.getElement(ligne, col);

				}
			}
		}

		// Compute the entries.
		float entries[][] = new float[matrix.numberOfRows][matrix.numberOfColumns] ;
		float coefTotal = 0 ;
		for(col=0;col<matrix.numberOfColumns;col++){
			coefTotal += (float) (coefCol[col] * (float)colMax[col]);
		}
		for(col=0;col<matrix.numberOfColumns;col++){
			for (ligne=0;ligne<matrix.numberOfRows;ligne++){
				entries[ligne][col] = (float) (coefCol[col] * matrix.getElement(ligne, col) / coefTotal);
			}
		}
		// Replace all the entries.
		for(col=0;col<matrix.numberOfColumns;col++){
			for (ligne=0;ligne<matrix.numberOfRows;ligne++){
				this.element[ligne][col]=entries[ligne][col];
			}
		}
	}
}
