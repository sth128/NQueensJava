package nQueens;

import java.util.concurrent.BlockingQueue;
import java.io.*;

public class Writer implements Runnable {
	private BlockingQueue<String> q;
	private int count = 0;
	private int n;

	public Writer(BlockingQueue<String> q, int n) {
		this.q = q;
		this.n = n;
	}

	@Override
	public void run() {
		String s;
		BufferedWriter bw = null;
		try {
			File file = new File("C:/" + n + "_queens.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			try {
				while (true) {
					s = q.take();
					if (s == "EXIT") return;
					count++;
					bw.write(s);
					if (count % 10 == 0) {
						bw.write(System.lineSeparator()) ;
					} else {
						bw.write(" ");
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} catch (IOException e) {
			System.out.println("OMG write error");
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception e) {
				System.out.println("Error in closing the BufferedWriter" + e);
			}
		}
	}
	
	public int getCount() {
		return count;
	}
}
