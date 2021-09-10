package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	final String userNameFile ="C:\\Captain's Log\\usernames.txt\\";
	final String userPassFile ="C:\\Captain's Log\\notpasswords.txt\\";
	@FXML
	private Label lb_status;
	@FXML
	private TextField tf_username;
	@FXML
	private TextField pf_password;
	@FXML
	private Label lb_LoginDenied;
	
	File usernameFile = new File(userNameFile);
	File userpassFile = new File(userPassFile);
	
	public void Login(ActionEvent event) throws IOException
	{
		String insertedUsername = tf_username.getText();
		String insertedPassword = pf_password.getText();
		
		Scanner scanner1 = new Scanner(usernameFile);
		Scanner scanner2 = new Scanner(userpassFile);
		
		int lineNum = 0;
		while(scanner1.hasNextLine() && scanner2.hasNextLine())
		{
			String usernameToCheck = scanner1.nextLine();
			String passwordToCheck = scanner2.nextLine();
			lineNum++;
			if((insertedUsername.equals(usernameToCheck))&&(insertedPassword.equals(passwordToCheck)))
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("MainMenu.fxml"));
				Parent MainMenuParent = loader.load();
				MainMenuController controller = loader.getController();
				Scene MainMenuScene = new Scene(MainMenuParent);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				
				window.setScene(MainMenuScene);
				window.show();
			}
			else
			{
				lb_LoginDenied.setText("LOGIN DENIED");
			}
		}
	}
	
	public void CreateProfile(ActionEvent event) throws IOException
	{
		Parent createProfileParent = FXMLLoader.load(getClass().getResource("CreateProfile.fxml"));
		Scene createProfileScene = new Scene(createProfileParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(createProfileScene);
		window.show();
	}
	

}
