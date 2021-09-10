package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreateProfile extends Application {
	final String userDirName = "C:\\Captain's Log";
	final String userNameFile = "C:\\Captain's Log\\usernames.txt";
	final String userpassFile = "C:\\Captain's Log\\notpasswords.txt";
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		dirExists(userDirName);
		userFileExists(userNameFile);
		userFileExists(userpassFile);
		
		Parent root = FXMLLoader.load(getClass().getResource("CreateProfile.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void dirExists(String filename) throws IOException
	{
		File file = new File(filename);
		 if(!file.exists() || filename == null)
		 {
			 file.mkdir();
		 }
	}
	public void userFileExists(String filename) throws IOException
	{
		File file = new File(filename);
		 if(!file.exists() || filename == null)
		 {
			 file.createNewFile();
		 }
	}
}
