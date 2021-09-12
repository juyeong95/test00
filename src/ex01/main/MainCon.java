package ex01.main;

import java.net.URL;
import java.util.ResourceBundle;

import ex01.dbcommon.DBCommon;
import ex01.login.LoginService;
import ex01.login.LoginServiceImpl;
import ex01.membership.MembershipMain;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MainCon implements Initializable{
	Parent root;
	MembershipMain msm;
	LoginService ls;
	
	public void setRoot(Parent root) {
		this.root = root;
		ls.setRoot(root);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		msm = new MembershipMain();
		ls = new LoginServiceImpl();
		DBCommon.setDBConnection();
	}
	public void loginProc() {
		ls.logincheck(); //구현 완료
	}
	public void membershipProc() {
		msm.OpenMembership();  //구현 완료
	}
	public void cancelProc() {
		ls.cancel(); //구현 완료
	}
	
}
