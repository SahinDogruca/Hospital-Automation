package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void OptionPaneTextChange() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	
	public static void showMsg(String str) {
		String msg;
		OptionPaneTextChange();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz";
			break;
		case "success":
			msg = "Ýþlem baþarýlý !";
			break;
		case "error":
			msg="Bir þeyler ters gitti";
			break;
		default:
			msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Mesaj" , JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	public static boolean confirm(String str) {
		String msg;
		OptionPaneTextChange();
		
		switch(str) {
		case "sure":
			msg = "Bu iþlemi gerçekleþtirmek istiyor musunuz ?";
			break;
		default:
			msg = str;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msg,"DÝKKAT!",JOptionPane.YES_NO_OPTION);
		
		if(res == 0) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	

}
