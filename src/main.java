import java.io.FileNotFoundException;
import java.io.IOException;
import presentation.*;

public class main {
	public static void main(String[] args)  {

		try {
			CtrlPresentationRecords records = new CtrlPresentationRecords();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		//Initialize controllers
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						//new VistaLEEME().hacerVisible(); //Meter vista primer menu (log in)
						CtrlPresentation ctrlPresentacion = new CtrlPresentation();
						ctrlPresentacion.initialize();
					}
				});
	}
}