package Business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
	
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private boolean mined;
 
    
    
    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.mined = false;
        this.hash = calculateBlockHash();
    }
    
    
 
    public String calculateBlockHash() {
        var dataToHash = this.previousHash 
          + Long.toString(this.timeStamp) 
          + Integer.toString(this.nonce) 
          + this.data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
        	Logger logger = Logger.getLogger(Block.class.getName());
            logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
    
    /**
     * In this function, is used Optional class because this function may or may not return a valued string.
     * @param prefix
     * @return Optional<String>
     */
    
    public Optional<String> mineBlock(int prefix) {
    	Optional<String> value;
        var prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.hash.substring(0, prefix).equals(prefixString) && this.isMined() == false) {
            this.nonce++;
            this.setHash(calculateBlockHash());
        }
    	if(this.isMined()) {
    		value = Optional.empty();
    	}
    	else
    	{
            this.setMined(true);
    		value = Optional.of(this.hash);
    	}
    	return value;
    }
    

    
	public String getHash() {
		return hash;
	}
	public boolean isMined() {
		return mined;
	}
	public void setMined(boolean mined) {
		this.mined = mined;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}



	@Override
	public String toString() {
		return "Block [hash=" + hash + ", previousHash=" + previousHash + ", data=" + data + ", timeStamp=" + timeStamp
				+ ", nonce=" + nonce + ", mined=" + mined + "]";
	}
}
