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
java -ea c smaller.txt
java -ea d smaller.txt
2 space indent (google standard)
*/

//Ervin driving now
//swap every ~30 min

public class LZcoding {
  public static void main(String[] args) throws Exception {
  	//assertion
    assert(args.length == 2);
    assert(args[0].charAt(0) == 'c' || args[0].charAt(0) == 'd');
    assert(args[0].length() == 1);
  	
  	//testing args[]
  	char type = args[0].charAt(0);
  	String file = args[1];
  	
  	//IO object
  	//try exception
  	IO.Compressor compressor = new IO.Compressor(file);
  	char[] charArray = compressor.getCharacters();
  	}
}

/* EX output
The lower bound is 61.700; the entropy is 1.234
*/
