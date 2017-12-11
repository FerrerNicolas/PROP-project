package domain;
import persistence.*;
public class CtrlDomain {
	private CtrlPersistence ctrlPersistence;
	public CtrlDomain() {
		ctrlPersistence = new CtrlPersistence(); 
		//create all domain structures;
	}
}
