/*
Ian Buitrago: Slip days used for this project: 0  Slip days used (total): 0
Ervin Kalemi: Slip days used for this project: 0  Slip days used (total): 0

Pair programming log (> 80% paired)
9/19 3-4a  Ian, 1 hr
9/20 8:30-10p Ian Ervin, 4 hrs

Total time X hrs, Z hrs of pair programing

Challanges:

Learned:

Notes:
run with commands:
javac LowerBound.java
java -ea LowerBound smaller.txt
2 space indent (google standard)
*/

//Ian driving now
//swap every ~30 min

public class LowerBound {
  
	
	static final int ASCII = 255;
  public static void main(String[] args) throws Exception {
  	//assertion
  	boolean youRead = true;
  	assert(youRead == true);
  	
  	
  	
  	//testing args[]
  	String file = null;
  	
  	if (args.length > 0)
  	  file = args[0];
  	
  	//IO object
  	//try exception
  	IO.Compressor compressor = new IO.Compressor(file);
  	char[] charArray = compressor.getCharacters();
  	
  	//check occurence of each char
  	int[] occurence = new int[ASCII];
  	//Ervin driving now
  	for(int i = 0; i < charArray.length; i++)
  		occurence[ (int)charArray[i] ]++;
  	
  	double e =  entropy(occurence, (double) charArray.length);  	
  	double lowBound =  lowerBound(e, charArray.length);
  	
  	//Print the results
  	System.out.println("The lower bound is " + lowBound + "; the entropy is " + e);
  }
  
  static double entropy(int[] occurence, double size){
    double e = 0.0;
    for (int i=0; i<occurence.length; i++){
    	if(occurence[i] != 0){
    		e -= (occurence[i]/size) * (Math.log(occurence[i]/size)/Math.log(2));
    	}
    }
    e = Math.round(e * 1000);
  	return e / 1000;
  }
  
  static double lowerBound(double e, int size ){
  	double lowerBound = Math.round(size * e * 1000);
  	return lowerBound/1000;
  }
}

/* EX output
The lower bound is 61.700; the entropy is 1.234
*/