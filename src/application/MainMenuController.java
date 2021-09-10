package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainMenuController {
	
	public String username;
	
	@FXML
	private Label lb_welcome;
	
	
	public void InsertNewEntry(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewEntry.fxml"));
		Parent newEntryParent = loader.load();
		
		NewEntryController controller = loader.getController();
		Scene newEntryScene = new Scene(newEntryParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(newEntryScene);
		window.show();
		
	}
	public void ConsultEntries(ActionEvent event) throws IOException{
		Parent consultEntriesParent = FXMLLoader.load(getClass().getResource("ConsultEntries.fxml"));
		Scene consultEntriesScene = new Scene(consultEntriesParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(consultEntriesScene);
		window.show();
	}	
}
