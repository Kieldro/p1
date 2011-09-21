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
  public static void main(String[] args) throws Exception{
  	//assertion
    assert(args.length == 2);
    assert(args[0].charAt(0) == 'c' || args[0].charAt(0) == 'd');
    assert(args[0].length() == 1);
  	
  	//testing args[]
  	char type = args[0].charAt(0);
  	String file = args[1];
  	
  	//Compress or decompress file based on the input
  	if(type == 'c')
	    compress(file);
    else if(type == 'd')
      decompress(file);
  }
  
  public static void compress(String file) throws Exception{
    IO.Compressor compressor = new IO.Compressor(file);
    char[] charArray = compressor.getCharacters();
    
    //Trie dictionary = new Trie();
    //for(int i =0; i< charArray.length; i++){}
      
   
   }
   public static void decompress(String file) throws Exception{
    
      
   
   }
}

class TrieNode{
  private String word;
  private int index;
  
  public TrieNode(String word, int index){
    this.word = word;
    this.index = index;
  }
}
  
