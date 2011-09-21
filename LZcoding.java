/*
Ian Buitrago: Slip days used for this project: 0  Slip days used (total): 0
Ervin Kalemi: Slip days used for this project: 0  Slip days used (total): 0

Pair programming log (> 80% paired)
9/19 3-4a  Ian, 1 hr
9/20 8:30-10p Ian Ervin, 4 hrs

Total time X hrs, Z hrs of pair programing

Challenges: Converting the first argument from string to character

Learned:

Notes:
run with commands:
javac LZcoding.java
java -ea LZcoding c test
java -ea LZcoding d test.cpz

To compare the files: 
cmp test test.cpz.dcz

2 space indent (google standard)
*/

//Ervin driving now
//swap every ~30 min


public class LZcoding {
	
	private static final boolean DEBUG = true;

  public static void main(String[] args) throws Exception {
  	
		if(DEBUG) System.out.println("main invoked.");
		
		long start = System.currentTimeMillis();	// Get current time
		
  	//assertion
    assert(args.length == 2);
    assert(args[0].charAt(0) == 'c' || args[0].charAt(0) == 'd');
    assert(args[0].length() == 1);
  	
  	//testing args[]
  	char type = args[0].charAt(0);
  	String file = args[1];
  	
  	//compression
  	compress(file);
  	
  	long elapsedTime = System.currentTimeMillis()-start;		// Get elapsed time in milliseconds
  	if(DEBUG) System.out.println("elapsed run time: " + elapsedTime + "ms");
  	}

	/* inFile is the name of the file to be compressed */
	public static void compress(String inFile) throws Exception {
		/* Initialize a IO.Compressor object */
		IO.Compressor compressor = new IO.Compressor(inFile);
		IO.Decompressor decompressor = new IO.Decompressor(inFile);
		/* Read all characters from the input file to a character array */
		char[] charArray = compressor.getCharacters();
		
		
		
		/* Perform compression on the array charArray,
		* this part may call io.encode(...) several times
		*/
		if(DEBUG) System.out.println("char[0] " + charArray[0]);
		compressor.encode(0, charArray[0]);
		
		
		decompressor.append("");
		
		
		
		/* Close all relevant files */
		compressor.finalize();
		if(DEBUG) System.out.println("Compression done.");
	}
}

/* EX output
test.cpz
*/
