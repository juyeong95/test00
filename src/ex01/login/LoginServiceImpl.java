package ex01.login;

import ex01.dbservice.DBService;
import ex01.dbservice.DBServiceImpl;
import ex01.dto.MemDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class LoginServiceImpl implements LoginService{
	Parent root;
	DBService db;
	public LoginServiceImpl() {
		db=new DBServiceImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;		
	}
	
	@Override
	public void logincheck() {
		TextField id = (TextField)root.lookup("#fxId");
		PasswordField pwd = (PasswordField)root.lookup("#fxPwd");
		MemDTO dto = db.loginCheck(id.getText());
		if(dto != null) {
			if(dto.getPwd().equals(pwd.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("인증 성공");
				alert.show();
				
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ex01/login/loginview.fxml"));
				
				
				Parent root=null;
				try {
					root=loader.load();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Scene scnen = new Scene(root);
				stage.setScene(scnen); 
				stage.show();
				
				Label iName = (Label)root.lookup("#iName");
				Label iid = (Label)root.lookup("#iId");
				Label igen = (Label)root.lookup("#iGen");
				Label iage = (Label)root.lookup("#iAge");
				Label ifav = (Label)root.lookup("#iFav");
				
				
				
				iName.setText(dto.getName());
				iid.setText(dto.getId());
				igen.setText(dto.getGender());
				iage.setText(dto.getAge());
				ifav.setText(dto.getFav());
				
				
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("비밀번호가 틀렸습니다.");
				alert.show();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("존재하지 않는 아이디 입니다.");
			alert.show();
		}
	}

	@Override
	public void cancel() {
		Stage s = (Stage)root.getScene().getWindow();
		s.close();
		
	}

}
