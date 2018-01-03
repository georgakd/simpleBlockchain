package noobchain;

/**
 * A simple blockchain model where the hashes are produced based
 * on the difficulty (number of 0) set.
 *  
 */

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NoobChain {
	
	public static ArrayList<Block> blockchain =  new ArrayList<Block>();
	public static int difficulty = 3; // mining difficulty

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("Basic blockchain model!");
		blockchain.add(new Block("This is the first chain block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("This is the second chain block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("This is the third chain block", blockchain.get(blockchain.size()-1).hash));	
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(Long.toString(elapsedTime) + " ms has passed during mining!");
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);      
        NoobChain.exportJson(blockchainJson);
        
        
	}
	
	public static void exportJson(String blockchainJson) {
		try {
			File file = new File("blockchain.json");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(blockchainJson);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1;i<blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes do not match...");
				return false;
			}
			
			if(!previousBlock.hash.equals(previousBlock.calculateHash())) {
				System.out.println("Previous Hashes do not match...");
				return false;
			}
			
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block is not mined...");
				return false;
			}
			
		}
		
		return true;
		
	}
	
	

}

