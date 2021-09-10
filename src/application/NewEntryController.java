package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewEntryController {
	public String username;
	
	final String userDirName = "C:\\Captain's Log\\";
	@FXML
	private TextField tf_entry;
	@FXML
	private Button bt_saveEntry;
	@FXML
	private Button bt_clearEntry;
	@FXML
	private Button bt_returnToMainMenu;
	
	@FXML
	public void onClick_bt_saveEntry(ActionEvent event) throws IOException
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("MM-d-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("HH-mm");
		String dailyDir = userDirName + format.format(calendar.getTime());
		String filename = "[ENTRY] " + format2.format(calendar.getTime()) + ".txt";
		String txtToSave = tf_entry.getText().toString();
		OutputStream fOut = null;
		
		dirExists(dailyDir);
		
		File f = new File(dailyDir, filename);
		f.createNewFile();
		
		fOut = new FileOutputStream(f);
		
		fOut.write(txtToSave.getBytes());
		
		fOut.close();
	}
	
	@FXML
	public void onClick_bt_clearEntry(ActionEvent event)
	{
		tf_entry.clear();
	}
	@FXML
	public void onClick_bt_returnToMainMeny(ActionEvent event) throws IOException
	{	
		Parent mainMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene mainMenuScene = new Scene(mainMenuParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainMenuScene);
		window.show();
	}
	
	public void dirExists(String filename) throws IOException
	{
		File file = new File(filename);
		 if(!file.exists() || filename == null)
		 {
			 file.mkdir();
		 }
	}
	
}
