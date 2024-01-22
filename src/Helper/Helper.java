package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void OptionPaneTextChange() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	
	public static void showMsg(String str) {
		String msg;
		OptionPaneTextChange();
		switch (str) {
		case "fill":
			msg = "L�tfen t�m alanlar� doldurunuz";
			break;
		case "success":
			msg = "��lem ba�ar�l� !";
			break;
		case "error":
			msg="Bir �eyler ters gitti";
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
			msg = "Bu i�lemi ger�ekle�tirmek istiyor musunuz ?";
			break;
		default:
			msg = str;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msg,"D�KKAT!",JOptionPane.YES_NO_OPTION);
		
		if(res == 0) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	

}
