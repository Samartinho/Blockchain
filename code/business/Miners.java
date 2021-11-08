package Business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Miners implements Runnable{

	private Block bloco;
	private List<Block> blockchain;
	private Block novoBloco;
	
	
	
	public Miners(Block b, List<Block> chain)
	{
		this.setB(b);
		this.setBlockchain(chain);
	}
	
	

	@Override
	public void run() {
		System.out.println("Starting: " + Thread.currentThread().getName());
		Optional<String> hash = this.getBloco().mineBlock(5);
		if(hash.isPresent())
		{
			this.getBlockchain().add(bloco);
			novoBloco = new Block("data", hash.get(), Date.valueOf(LocalDate.now()).getTime());
			this.setNovoBloco(novoBloco);
			System.out.println("Finishing: " + Thread.currentThread().getName());
			System.out.println(this.toString());
		}
		else
		{
			System.out.println("Finishing: " + Thread.currentThread().getName());
		}
	}
	
	

	public Block getB() {
		return bloco;
	}
	public void setB(Block b) {
		this.bloco = b;
	}
	public List<Block> getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}
	public Block getBloco() {
		return bloco;
	}
	public void setBloco(Block bloco) {
		this.bloco = bloco;
	}
	public Block getNovoBloco() {
		return novoBloco;
	}
	public void setNovoBloco(Block novoBloco) {
		this.novoBloco = novoBloco;
	}
	
	

	@Override
	public String toString() {
		return "Mined Block by: " + Thread.currentThread().getName() + "  bloco=" + bloco.toString() + "]";
	}
}
