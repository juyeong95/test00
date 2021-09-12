package ex01.membership;

import ex01.dbservice.DBService;
import ex01.dbservice.DBServiceImpl;
import ex01.dto.MemDTO;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class MembershipServiceImpl implements MembershipService{
	Parent root;
	DBService db;
	public MembershipServiceImpl() {
		db=new DBServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root=root;
	}
	

	@Override
	public void confirm() {
		System.out.println("확인 버튼");
		TextField id = (TextField)root.lookup("#memId");
		TextField name = (TextField)root.lookup("#memName");
		PasswordField pwd = (PasswordField)root.lookup("#memPwd");
		PasswordField pwdc = (PasswordField)root.lookup("#memPwdC");
		
		String gender = getGender();
		String favorit = getFav();
		String age = getComboBoxString();
		
		
		MemDTO dto = new MemDTO();
		dto.setId(id.getText());
		dto.setName(name.getText());
		dto.setPwd(pwd.getText());
		dto.setGender(gender);
		dto.setFav(favorit);
		dto.setAge(age);
		
		
		if( pwd.getText().equals( pwdc.getText() ) ) {
			int result = db.insertMember(dto);
			if(result == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("회원가입 성공");
				alert.show();
				Stage s = (Stage)root.getScene().getWindow();
				s.close();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("동일한 아이디가 존재합니다.");
				alert.show();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("비밀번호 확인이 다릅니다.");
			alert.show();
		}
		
		// 회원가입 창 값들 db에 저장 구현 완료
	
	}
	private String getGender() {
		RadioButton rdoMan = (RadioButton)root.lookup("#rMan");
		if(rdoMan.isSelected() ) {
			return "남자";
		}else {
			return "여자";
		}
		
	}
	private String getFav() {
		int favorit = 0;
		String result = "null";
		if( ((CheckBox)root.lookup("#cMusic")).isSelected() ) {favorit += 1;}
		if( ((CheckBox)root.lookup("#cSport")).isSelected() ) {favorit += 2;}
		if( ((CheckBox)root.lookup("#cMovie")).isSelected() ) {favorit += 4;}
		
		if(favorit==0) {result="없음";
		}else if(favorit == 1){result="음악";
		}else if(favorit == 2) {result = "스포츠";
		}else if(favorit == 3) {result ="음악,스포츠";
		}else if(favorit ==4) {result="영화";
		}else if(favorit == 5) {result="음악,영화";
		}else if(favorit == 6) {result = "스포츠,영화";
		}else if(favorit == 7) {result = "음악,스포츠,영화";}
		
		return result;
	}
	private String getComboBoxString() {
		ComboBox<String> age = (ComboBox<String>)root.lookup("#cAge");
		String a =null;
		if(age.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("연령대를 선택해주세요");
			alert.show();
		}else {
			a=age.getValue();
		}
		return a;
	}

	@Override
	public void cancel() {
		Stage s = (Stage)root.getScene().getWindow();
		s.close();
		
	}
	@Override
	public void view() {
		ComboBox<String> cmbAge = (ComboBox<String>)root.lookup("#cAge");
		if(cmbAge != null) {
			cmbAge.getItems().addAll("20대 미만","20대","30대","40대 이상");
		}else {
			System.out.println("콤보박스가 비어있습니다.");
		}
		
	}
	

}
