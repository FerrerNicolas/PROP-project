import presentation.*;

public class main {
	public static void main(String[] args) {
		
        CtrlPresentationRecords records = new CtrlPresentationRecords();
		//Initialize controllers
		javax.swing.SwingUtilities.invokeLater (
			new Runnable() {
			    public void run() {
			          //new VistaLEEME().hacerVisible(); //Meter vista primer menu (log in)
			          CtrlPresentation ctrlPresentacion = new CtrlPresentation();
			          ctrlPresentacion.initialize();
		}});
	}
}
