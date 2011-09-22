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
import java.util.Hashtable;
import java.util.ArrayList;  
public class LZcoding {
	
	static final boolean DEBUG = true;
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
    trieNode root = new trieNode('k', 0);
    root.add('b', 4);
    root.add('x', 7);
    
    if(DEBUG) System.out.println("root.branch.get('b') = " + root.branch.get('b').toString() );
    if(DEBUG) System.out.println("root.branch.get('x') = " + root.branch.get('x').toString() );
    if(DEBUG) System.out.println("root.c = " + root.getChar() );
    if(DEBUG) System.out.println("root.idx = " + root.getIdx() );
    
    
    /*
    // Initial null string to lookup in the file
    String lookup = "";
    int counter = 1;
    // For every character in the array of characters run encode
    for (int i= 0; i < charArray.length; i++){
      // Appand the next character to the lookup string
      lookup += charArray[i];
      // If the new string formed is not in the dictionary 
      if(!dictionary.contains(lookup)){
        // If the new string is just a character
        if(lookup.length() == 1){
          // Create a transmission node based on the counter and that character
          Node trieNode = new Node(counter, lookup, (char)lookup);
          // Add that node to the dictionary trie
          dictionary.add(trieNode);
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
          Node trieNode = new Node(counter, lookup, lookup.charAt(lookup.length()-1));
          dictionary.add(trieNode);
          // Run incode on the last character of the lookup string
          compressor.encode(index, lookup.charAt(lookup.length()-1));
          // Re-initialize the lookup string and increment counter
          lookup = "";
          counter++;
        }
      }    
    }
    */
    // Finalize compressor
    compressor.finalize();
  }
  
  
	// The Node holds the word and the index of that word in the dictionary
	public static class trieNode{
	  private char c;
	  private int idx;
	  Hashtable<Character, trieNode> branch;
	  
	  public trieNode(char c, int i){
	    this.c = c;
	    idx = i;
	    branch = new Hashtable<Character,trieNode>();
	  }
	  
	  public void add (char c, int i){
	    trieNode child = new trieNode(c, i);
	    branch.put(new Character(c), child); 
	  }
	  
	  public String toString (){		//for debugging
	    return "(" + c + ", " + idx + ")";
	  }
	  
	  public char getChar (){
	    return c;
	  }
	  
	  public int getIdx (){
	    return idx;
	  }
	  
	}

  public static void decompress(String file) throws Exception{
    // Initialize decompressor and the arrayList that will serve
    // as the dictionary.
    IO.Decompressor io = new IO.Decompressor(file);
    ArrayList<String> dictionary = new ArrayList<String>();
    dictionary.add("Foo String, since dictionary[0] will never be user");
    
    // Get the first pair and start the counter
    IO.Pair next = io.decode();
    int counter = 1;
    // While that pair is valid
    while (next.isValid()){
      // If the index is zero
      if(next.getIndex() == 0){
        // That character is added to the dictionary
        String newEntry = Character.toString(next.getCharacter());
        dictionary.add(newEntry);
        // Write the new string to file
        io.append(newEntry);
        counter++;
      }
      // Else the index > 0, which means that that character is already in the dictionary
      else {
        // The new string is the string that is at dictionary[index] + the new character from the pair
        // Add the new enry to the dictionary
        String newEntry = dictionary.get(next.getIndex()) + Character.toString(next.getCharacter());
        dictionary.add(newEntry);
        // Writte the new string to file 
        io.append(newEntry);
        counter++;
      }
      // Get the next pair
      next = io.decode();      
    }
    // Finalize dhe decompressor
    io.finalize();
   }
   
}

