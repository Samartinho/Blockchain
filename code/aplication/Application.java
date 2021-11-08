package Aplicacao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import Business.*;


public class Application {
	public static void main(String[] args) throws InterruptedException {
		List<Block> blockchain = new ArrayList<>();
		var x = new Block("primeiro bloco", "", Date.valueOf(LocalDate.now()).getTime());
		ExecutorService thread = null;
		for(int i = 1; i <= 10; i++)
		{
			var task = new Miners(x, blockchain);
			try
			{
				thread = Executors.newFixedThreadPool(6);
				thread.execute(task);
				thread.execute(task);
				thread.execute(task);
				thread.execute(task);
				thread.execute(task);
				thread.execute(task);
				thread.shutdown();
				thread.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			}
			catch(Exception e) {
				throw e;
			}
			finally
			{
				thread.shutdownNow();
				x = task.getNovoBloco();
			}
			System.out.println("\n\n");
		}
		System.out.println("\n");
		blockchain.forEach(m -> System.out.println(m.toString()));
	}
}
