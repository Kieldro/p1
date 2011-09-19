/*
Ian Buitrago: Slip days used for this project: 0  Slip days used (total): 0
Ervin Kalemi: Slip days used for this project: 0  Slip days used (total): 0

Pair programming log (> 80% paired)
9/19 3-4a  Ian, 1 hr
9/19 8-10p Ian Ervin, 4 hrs

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
  public static void main(String[] args) {
  	//assertion
  	boolean youRead = true;
  	assert(youRead == true);
  	
  	//testing args[]
  	String s = null;
  	
  	if (args.length > 0)
  	  s = args[0];
  	
  	System.out.println("args[0] = " + s);
  	
  	//IO object
  	IO.Pair p = new IO.Pair(0, (char)97);
  	
  	System.out.println("char = " + (int)p.getCharacter() );
  	
  	double e =  entropy();
  	System.out.println("Entropy h = " + e);
  	
  	double lowBound =  lowerBound();
  	System.out.println("Lower bound = " + lowBound);
  }
  
  static double entropy(){
    double e = 0.0;
    
    e = 0;
    
  	return e;
  }
  
  static double lowerBound(){
    double lowBound = 0;
  	
  	lowBound = 0;
    
  	return lowBound;
  }
}

/* EX output
The lower bound is 61.700; the entropy is 1.234
*/