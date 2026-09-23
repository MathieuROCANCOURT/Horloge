package fr.ldnr.thread;

public class ThreadTime {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new MonRunnable(1));
		Thread thread2 = new Thread(new MonRunnable(2));
		Thread thread3 = new Thread(new MonRunnable(3));
		Thread thread4 = new Thread(new MonRunnable(4));
		Thread thread5 = new Thread(new MonRunnable(5));

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
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
}