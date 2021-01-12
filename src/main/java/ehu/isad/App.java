/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ehu.isad;

import ehu.isad.Controllers.ui.PhpMyAdminKud;
import ehu.isad.Utils.Sarea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Scene phpScene;

    private Parent phpUI;

    private Stage stage;


    private PhpMyAdminKud phpMyAdminKud;
    private Sarea sarea=new Sarea();


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("PhpMyAdmin");
        stage.setScene(phpScene);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderphp = new FXMLLoader(getClass().getResource("/phpMyAdmin.fxml"));
        phpUI = (Parent) loaderphp.load();
        phpMyAdminKud = loaderphp.getController();
        phpMyAdminKud.setMainApp(this);
        phpScene = new Scene(phpUI, 600, 400);
    }




    public static void main(String[] args) {
        launch(args);
    }
}