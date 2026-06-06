package Logics;

public class WaitLogic {
	
	public static void waitForSeconds(int sec) {
		try {
			Thread.sleep(sec*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
			
		}
	}

}
