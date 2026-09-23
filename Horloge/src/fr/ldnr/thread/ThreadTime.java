package fr.ldnr.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTime {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new MonRunnable(1));
		Thread thread2 = new Thread(new MonRunnable(2));
		Thread thread3 = new Thread(new MonRunnable(3));
		Thread thread4 = new Thread(new MonRunnable(4));
		Thread thread5 = new Thread(new MonRunnable(5));

		/*
		 * The results are different because the threads works in parallels and no
		 * synchronized. Also, the resources are access to a common resource.
		 */
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		Thread run = new Thread(new TestRunnable("!#$%&\'()*".toCharArray()));
		run.start();
		

	    DateFormat df = new SimpleDateFormat("HH:mm:ss");
	    Thread thread = new Thread(new Clock(1000));

	    System.out.println(df.format(new Date()));

	    thread.start();
	}

	private static class MonRunnable implements Runnable {

		private long delai;

		public MonRunnable(long delai) {
			this.delai = delai;
		}

		@Override
		public void run() {
			try {
				for (int repeatFive = 0; repeatFive < 5; repeatFive++) {
					Thread.sleep(delai);
					String repeated = new String(new char[(int) delai]).replace('\0', '-');
					System.out.print(delai + repeated + ' ');
				}
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class TestRunnable implements Runnable {
		private char[] characterSpecial;

		public TestRunnable(char[] characterSpecial) {
			this.characterSpecial = characterSpecial;
		}

		@Override
		public void run() {
			String result = "";
			try {
				int sizeArry = this.characterSpecial.length;

				for (int indexChar = 0; indexChar < sizeArry; indexChar++) {
					String repeated = new String(new char[indexChar + 1]).replace('\0',
							this.characterSpecial[sizeArry - 1]);
					result = result.concat(
							this.characterSpecial[indexChar] + repeated + this.characterSpecial[indexChar] + '\n');
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.print(result);
		}
	}
	
	private static class Clock implements Runnable {
	    private long delai;

	    public Clock(long delai) {
	      this.delai = delai;
	    }

	    @Override
	    public void run() {
	    	while(true) {
		      try {
		        Thread.sleep(delai);
		        System.out.println(new Date());
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
	    	}
	    }
	  }
}