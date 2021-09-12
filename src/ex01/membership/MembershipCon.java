package ex01.membership;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MembershipCon implements Initializable{
	Parent root;
	MembershipService mss;
	
	public void setRoot(Parent root) {
		this.root=root;
		mss.setRoot(root);
		mss.view();
	}
	
	public void memConfirmProc() {
		mss.confirm(); //구현완료
	}
	public void memCancelProc() {
		mss.cancel(); //구현완료
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mss=new MembershipServiceImpl();
		
	}
}
