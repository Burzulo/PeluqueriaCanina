package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	public void handleButtonAction(ActionEvent event) {
		System.out.println("Iniciar sesión");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Inicializa LC");
		btnLogin.setOnAction(this::handleButtonAction);

	}

}
