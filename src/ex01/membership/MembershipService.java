package ex01.membership;

import javafx.scene.Parent;

public interface MembershipService {
	public void setRoot(Parent root);
	public void confirm();
	public void cancel();
	public void view();

}
