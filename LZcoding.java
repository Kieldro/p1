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
import java.util.HashMap; 

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
    // IO compressor on the file
    IO.Compressor compressor = new IO.Compressor(file);
    // Convert file to an array of characters
    char[] charArray = compressor.getCharacters();
    // Create a dictionary
    HashMap dictionary = new HashMap<String, TrieNode>();
    // Initial null string to lookup in the file
    String lookup = "";
    int counter = 1;
    // For every character in the array of characters run encode
    for (int i= 0; i < charArray.length; i++){
      // Appand the next character to the lookup string
      lookup += charArray[i];
      // If the new string formed is not in the dictionary 
      if(!dictionary.containsKey(lookup)){
      
        // If the new string is just a character
        if(lookup.length() == 1){
          // Create a transmission node based on the counter and that character
          TrieNode node = new TrieNode(counter, lookup);
          // Add that node to the dictionary trie
          dictionary.put(lookup, node);
          // Run encode on that character with index 0
          compressor.encode(0, charArray[i]);
          // Re-initialize the lookup string and increment counter
          lookup = "";
          counter++;
          
        } // Else the new string is not a character, so we have to find it's parent
        else{
          // Get the parent of the new formed string
          String parent = lookup.substring(0, lookup.length()-1);
          // From the dictionary lookup the TrieNode that has the key parent
          // And get the index of that node in the transmission
          int index = ((TrieNode)dictionary.get(parent)).getIndex();
          // Create a new TrieNode based on the counter and the new string 
          // and add it to the dictionary.
          TrieNode node = new TrieNode(counter, lookup);
          dictionary.put(lookup, node);
          // Run incode on the last character of the lookup string
          compressor.encode(index, lookup.charAt(lookup.length()-1));
          // Re-initialize the lookup string and increment counter
          lookup = "";
          counter++;
        }
      }    
    }
    // Finalize compressor
    compressor.finalize();
  }
   
   
   public static void decompress(String file) throws Exception{
    
      
   
   }
}

class TrieNode{
  private String word;
  private int index;
  
  public TrieNode(int index, String word){
    this.word = word;
    this.index = index;
  }
  
  public int getIndex(){
    return this.index;
  }
}
  
