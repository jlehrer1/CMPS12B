import java.io.*;
import java.util.Scanner;

class Search {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	try {
			if (args.length < 2) {
    			System.out.println("Usage: Search file target1 [target2 ..]");
    			System.exit(1);
			}
			Scanner in = new Scanner(new File(args[0]));
    	} catch (FileNotFoundException g) {
			System.out.println(args[0] + " (No such file or directory)");
    		System.out.println("Usage: Search file target1 [target2 ..]");
    		System.exit(1);
    	}

		Scanner in = new Scanner(new File(args[0]));
		int lineCount = 0;
		while( in.hasNextLine() ){
		    in.nextLine();
		    lineCount++;
		}
		in.close();

		String[] wordArray = new String[lineCount];
		int[] lineNumber = new int[lineCount];
		File file = new File(args[0]);
		Scanner input = new Scanner(file);

		for (int i = 0; i < lineCount; i++) {
			wordArray[i] = input.nextLine();
		}

	   	for (int i = 1; i <= lineCount; i++) {
	   		lineNumber[i - 1] = i;
	   	}

	   	mergeSort(wordArray, lineNumber, 0, wordArray.length - 1);

	   	for (int i = 1; i < args.length; i++) {
	   		int foundIndex = binarySearch(wordArray, 0, wordArray.length - 1, args[i]);
	   		if (foundIndex == -1) {		
	   			System.out.println(args[i] + " not found");
	   		} else {
	   			System.out.println(args[i] + " found on line " + lineNumber[foundIndex]); //Wow i can't believe i made that mistake LOL fuck
	   		}
	   	}

	}

	//METHODS
	static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
		int q;
      	if(p < r) {
	        q = (p+r)/2;
	        mergeSort(word, lineNumber, p, q);
	        mergeSort(word, lineNumber, q + 1, r);
	        merge(word, lineNumber, p, q, r);
      }
	}

	static void merge(String[] word, int[] lineNumber, int p, int q, int r) {
		int n1 = q-p+1;
      	int n2 = r-q;
      	String[] L = new String[n1];
      	String[] R = new String[n2];
      	int[] intL = new int[n1];
      	int[] intR = new int[n2];
      	int i, j, k;

      	for(i = 0; i < n1; i++){
         	L[i] = word[p+i];
         	intL[i] = lineNumber[p+i];
      	}
      	for(j = 0; j < n2; j++){ 
         	R[j] = word[q+j+1];
         	intR[j] = lineNumber[q+j+1];
      	}

	    i = 0; j = 0;
      	for(k = p; k <= r; k++){
         	if( i < n1 && j < n2 ){
            	if( L[i].compareTo(R[j]) < 0) {
					word[k] = L[i];
					lineNumber[k] = intL[i];
               		i++;
            	} else {
               		word[k] = R[j];
               		lineNumber[k] = intR[j];
               		j++;
            	}
        	} else if ( i < n1 ){
            	word[k] = L[i];
            	lineNumber[k] = intL[i];
            	i++;
        	} else { // j<n2
            	word[k] = R[j];
            	lineNumber[k] = intR[j];
            	j++;
        }
      }
	}
	static int binarySearch(String[] A, int p, int r, String t) {
		int q;
      	if(p > r) {
	        return -1;
      	} else {
	        q = (p+r)/2;
        if(t.equals(A[q])){
        	return q;
        } else if (t.compareTo(A[q]) < 0){
        	return binarySearch(A, p, q-1, t);
        } else { // t > A[q]
            return binarySearch(A, q+1, r, t);
        }
      }
	}
}