package persistance;



import ui.Page;
import cli.Actions;

public class main {
	public static void main(String[] args) throws Exception {
		Page.nbPageComputer();
		Actions A = new Actions();
		A.boucle();
	}
	
	
}
