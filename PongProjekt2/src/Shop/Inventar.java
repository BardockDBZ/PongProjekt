package Shop;

public class Inventar {
	
	private static int Taler = 0;
	
	
	public static  void TalerGewinn(int gewinn) {
		Taler += gewinn;
	}
	public static void TalerVerlust(int Verlust) {
		Taler += Verlust;
	}


	public static int getTaler() {
		return Taler;
	}


	public static void setTaler(int taler) {
		Taler = taler;
	}
}
