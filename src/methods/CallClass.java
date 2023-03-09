package methods;

import gui.MainScreen;

public class CallClass {
	public static void main(String[] args) {
		// System.out.println(exchangeApiRequests.getsymbolRequest().get(0)[0]);
		llamarVentana();
	}
	
	private static void llamarVentana() {
		 
		 MainScreen nuevaVentana = new MainScreen();
		 nuevaVentana.setVisible(true);
		 nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}

}
