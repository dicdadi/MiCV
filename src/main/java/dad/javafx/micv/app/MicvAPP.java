package dad.javafx.micv.app;

import dad.javafx.micv.maincontroller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MicvAPP extends Application {
	private MainController root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new MainController();
		Scene scene = new Scene(root.getRootView());
		primaryStage.setTitle("MiCV");
		primaryStage.getIcons().add( new Image("/imagenes/cv64x64.png"));
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);

	}

}
