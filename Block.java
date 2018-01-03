package noobchain;

import java.util.Date;;

public class Block {
	public String hash; // digital signature
	public String previousHash;
	private String data;
	private long timeStamp; // in ms
	private int nonce; // arbitrary number used only once
	
	//Constructor for each new chain block
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		String calculatedHash = StringUtil.applySha256(previousHash + 
				Long.toString(timeStamp) + Integer.toString(nonce) + data);
		return calculatedHash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Managed to mine block  " + hash);
	}

}
