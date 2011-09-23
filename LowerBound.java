/*
Ian Buitrago: Slip days used for this project: 1  Slip days used (total): 1
Ervin Kalemi: Slip days used for this project: 1  Slip days used (total): 1

Pair programming log (> 80% paired)
9/19 3-4a  Ian, 1 hr
9/20 10-12p Ian, Ervin, 4 hrs
9/21 12-3p Ian, Ervin, 6 hrs
9/21 6-9p Ian, Ervin, 6 hrs
9/21 5-6p Ervin, 1 hr
9/22 6-8p Ian, Ervin, 4 hrs

Total time 22 hrs, 20 hrs of pair programing

Challenges: Trying to calculate the entropy when the number of occurence of
one of the characters was zero. It returned NaN. To fix this, we checked if the occurence
of the character wasn't zero we running the entropy formula.

We had to cast the size of int[] occurense to double, in order to get the probability as a
double. Otherwise the probability would be an int, resulting in a wrong entropy.

Learned: To round a number to certain decimals, you simply do 
round that number * 10^(#decimals), and divide that number by
10^(#decimals).

Notes:
run with commands:
javac LowerBound.java
java -ea LowerBound smaller.txt
2 space indent (google standard)
*/

//Ian driving now
//swap every ~30 min
import java.io.*; 

public class LowerBound {
	static final int ASCII = 255;
    
    public static void main(String[] args) throws Exception {
  	//assertion
  	assert(args.length == 1);
    // Input file
    String file = args[0];
  	
  	//IO object
  	char[] charArray = getCharacters(file);
  	
  	//check occurence of each char
  	int[] occurence = new int[ASCII];
  	for(int i = 0; i < charArray.length; i++){
  		occurence[ (int)charArray[i] ]++;
		}
  	// Calculate entropy and lower bound
  	double e =  entropy(occurence, (double) charArray.length);  	
  	double lowBound =  lowerBound(e, charArray.length);
  	
  	//Print the results
  	System.out.println("The lower bound of "+file+" is " + lowBound + "; the entropy is " + e);
  }
	//Ervin driving now 
  static double entropy(int[] occurence, double size){
    double e = 0.0;
    // For every occurence, if any, calculate run the entropy formula
    for (int i=0; i<occurence.length; i++){
    	if(occurence[i] != 0){
    		e -= (occurence[i]/size) * (Math.log(occurence[i]/size)/Math.log(2));
    	}
    }
    // Round the entropy to three decimals and return it
    e = Math.round(e * 1000);
  	return e / 1000;
  }
  
  static double lowerBound(double e, int size ){
    // Round the lower bound to three decimals and return it
  	double lowerBound = Math.round(size * e * 1000);
  	return lowerBound/1000;
  }
  
  // Function copied from IO.file. 
  // Copied so that when computing the lowerbound there won't be
  // any empty encoded file created.
  public static char[] getCharacters(String file) throws Exception {
	  File inFile = new File(file);
	  char[] ret = new char[(int) (inFile.length()+1)];
	  int retCursor = 0; 
	  FileInputStream from = new FileInputStream(inFile); 
	
	  while (true) {
		  byte nextByte = (byte) from.read(); 
		  if (nextByte == -1) 
			  break;
		
		  char nextChar = (char) nextByte; 
		  ret[retCursor++] = nextChar; 
	  }
	
	  ret[(int)(inFile.length())] = '\000';
	  return ret; 
  }
}

/* EX output
The lower bound is 61.700; the entropy is 1.234
*/
