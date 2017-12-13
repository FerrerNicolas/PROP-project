package domain;
import persistence.*;
import model.*;
public class CtrlDomain {
	protected CtrlPersistence ctrlPersistence;
	protected User userActivo = null;
	
	public CtrlDomain() {
		ctrlPersistence = new CtrlPersistence(); 
		//create all domain structures;
	}
	//user setter
}
