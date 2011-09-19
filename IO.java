import java.util.*;
import java.io.*; 


public class IO {
	public static final boolean DEBUGGING = true; // Set to TRUE to print debugging information
	public static final int BLOCK_SIZE = 128; // Don't touch
	
	// File extensions
	public static final String COMPRESSED_FILE_EXTENSION = "cpz";
	public static final String DECOMPRESSED_FILE_EXTENSION = "dcz";
	
	/* Pair class definition */
    static class Pair {
	    private boolean valid; 
	    final private int index; 
	    final private char character;
		
	    Pair(int index, char character) {
			this(index, character, true);
		}
		
		Pair(int index, char character, boolean _v) {
			assert (((int)character) < BLOCK_SIZE);
			
			this.index = index; 
			this.character = character; 
			this.setValid(_v);
		}
		
		public int getIndex() {
			return index;
		}
		
		public char getCharacter() {
			return character;
		}
		
		public boolean isValid() {
			return valid;
		}
		
		public void setValid(boolean valid) {
			this.valid = valid;
		}
	}
	
	/* Decompressor class definition */
    public static class Decompressor {
		private boolean finish; 
		private FileWriter oWriter; 
		private String lastString; 
		private int cursor; 
		private Vector output;  
		
		Decompressor(String inFile) throws Exception {
			lastString = "";
			finish = false; 
			cursor = 0;
			output = new Vector(); 
			FileInputStream inStream =  new FileInputStream(inFile); 
			FileWriter fw  = new FileWriter(inFile + "." + DECOMPRESSED_FILE_EXTENSION ); 
			oWriter = fw; 
			
			while(true) {
				int indexHigh = (byte)inStream.read(); 		    
				if(indexHigh == -1) 
					break ;
				int indexMiddle = (byte) inStream.read();		
				int indexLow = (byte) inStream.read();
				int index = indexHigh*BLOCK_SIZE*BLOCK_SIZE + indexMiddle*BLOCK_SIZE + indexLow;
				
				char c = (char) inStream.read(); 
				output.add(new Pair(index, c)); 
			}
		}
		
		public void finalize() throws Exception {	    
			oWriter.close(); 
		}
		
		public Pair decode() {
			Pair ret;
			if(cursor < output.size()) {
				ret = (Pair)output.get(cursor++); 
				ret.setValid(true); 
			} else {
				ret = new Pair(0,'a', false); 
			}
			return ret; 
		}  
		
		public void append(String op) throws Exception {
			oWriter.write(lastString); 
			lastString = new String(op); 
		}
	}
	
	/* Compressor class definition */
    static class Compressor {
		private FileOutputStream oStream; 
		private Vector output;  
		private File inFile, outFile; 
	
		Compressor (String fileName) throws Exception {
			String outfileName = fileName + "." + COMPRESSED_FILE_EXTENSION; 
			
			output  = new Vector(); 
			outFile = new File(outfileName);
			oStream = new FileOutputStream(outFile);
			inFile  = new File(fileName);
		}
		
		public void encode(int index, char character) throws Exception {
			output.add(new Pair(index, character)); 
			
			int xdash     = index / BLOCK_SIZE; 
			int xLowest   = index % BLOCK_SIZE; 
			int xdashdash = xdash / BLOCK_SIZE; 
			int xMiddle   = xdash % BLOCK_SIZE;
			
			oStream.write((byte) xdashdash);
			oStream.write((byte) xMiddle);
			oStream.write((byte) xLowest);
			oStream.write(character);
			
			if(DEBUGGING) {
				// This is to help you during debugging, please comment this out for large files
				System.out.println(" ( " + index + ", " + character + " )");
			}
		} 
		
		public void finalize() throws Exception {
			oStream.close(); 
		}
		
		public char[] getCharacters() throws Exception {
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
}