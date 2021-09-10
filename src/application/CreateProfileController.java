package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProfileController {
	final String userNameFile ="C:\\Captain's Log\\usernames.txt\\";
	final String userPassFile ="C:\\Captain's Log\\notpasswords.txt\\";
	@FXML
	private TextField tf_username;
	@FXML
	private PasswordField pf_password;
	@FXML
	private Label lb_displayMessage;
		
	
	File usernameFile = new File(userNameFile);
	File userpassFile = new File(userPassFile);
	
	public void CreateProfile (ActionEvent event) throws IOException
	{
		if((!usernameFile.exists() || usernameFile == null)&&(!userpassFile.exists() || userpassFile == null))
		{
			lb_displayMessage.setText("Error opening settings files");
		}
		else
		{
			String username = tf_username.getText().toString();
			
			Scanner scanner = new Scanner(usernameFile);
			
			int lineNum = 0;
			int flag = 0;
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				lineNum++;
				if(username.equals(line))
				{
					lb_displayMessage.setText("Username Already Exists");
					flag = 1;
				}
			}
			scanner.close();
			if(flag == 0)
			{
				String password = pf_password.getText().toString();
				if(password.isEmpty())
				{
					lb_displayMessage.setText("Password Field must be filled");
				}
				else
				{
					FileWriter fw = new FileWriter(usernameFile, true);
					fw.write(username + "\n");
					fw.close();
					
					
					
					FileWriter fw2 = new FileWriter(userpassFile, true);
					fw2.write(password + "\n");
					fw2.close();
					
					
					Parent loginParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
					Scene loginScene = new Scene(loginParent);
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
					
					window.setScene(loginScene);
					window.show();
				}
				}
			}
		}
	
	public void ReturnToLogin(ActionEvent event) throws IOException
	{
		Parent loginParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		Scene loginScene = new Scene(loginParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(loginScene);
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


