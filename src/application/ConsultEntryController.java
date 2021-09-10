package application;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class ConsultEntryController {
	final String userDirName = "C:\\Captain's Log\\";
	@FXML
	private DatePicker dp_datepicker;
	@FXML
	private TextField tf_showFile;
	@FXML
	private Button bt_export;
	
	
	@FXML
	public void displayFileByDate() throws IOException
	{
		String date = dp_datepicker.getValue().format(DateTimeFormatter.ofPattern("MM-d-yyyy"));
		
		String dailyDir = userDirName + date;
		
		File directory = new File(dailyDir);
		
		if(!directory.exists() || dailyDir == null)
		{
			String error_message = "Filename " + dailyDir;
			tf_showFile.setText("Error finding file" + error_message);
		}
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(directory);
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null)
		{
			StringBuilder text = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			String line;
			while((line = br.readLine()) != null)
			{
				text.append(line);
				text.append("\n");
			}
			br.close();
			
			String textToDisplay = text.toString();
			
			tf_showFile.setText(textToDisplay);
		}		
	}
	
	@FXML
	public void exportFiles() throws IOException
	{
		File initDir = new File(userDirName);
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(initDir);
		List<File> selectedFiles = fc.showOpenMultipleDialog(null);
		
		if(selectedFiles != null)
		{
			for(int i = 0; i < selectedFiles.size(); i++)
			{	
				StringBuilder text = new StringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(selectedFiles.get(i)));
				String line;
				Document document = new Document();
				try {
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(selectedFiles.get(i) + ".pdf"));
					document.open();
					document.add(new Paragraph("Hello, world"));
					while((line = br.readLine()) != null)
					{
						text.append(line);
						text.append("\n");
					}
					br.close();
					String textToWrite = text.toString();
					document.add(new Paragraph(textToWrite));
					
					document.close();
					writer.close();
				}catch(DocumentException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		
	}
	public void ReturnToMenu(ActionEvent event) throws IOException
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

}
