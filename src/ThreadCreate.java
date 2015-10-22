
public class ThreadCreate {
	public static void main(String []args){
		Thread thread1 = new Thread (){
			public void run(){
				for (int i =0; i< 10; i+=2){
					System.out.println("Henra1");
				}
			}
		};
		
		Thread thread2 = new Thread(){
			public void run(){
				for (int i=0; i<10; i++){
					System.out.println("Hebra2");
				}
			}
		};
		
		thread1.start();
		thread2.start();
	}

}
