package ehu.isad.Controllers.ui;

import ehu.isad.App;
import ehu.isad.Controllers.db.ZerbitzuKud;
import ehu.isad.Model.Checksums;
import ehu.isad.Utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PhpMyAdminKud implements Initializable {
    @FXML
    private Button checkButton;

    @FXML
    private TextField textURL;

    @FXML
    private TableView<Checksums> taula;

    @FXML
    private TableColumn<Checksums, String> urlZut;

    @FXML
    private TableColumn<Checksums, String> md5Zut;

    @FXML
    private TableColumn<Checksums, String> versionZut;

    @FXML
    private Label mezua;

    private Sarea sarea=new Sarea();
    @FXML
    void onClick(ActionEvent event) throws Exception {
        try {
            URL url = new URL(textURL.getText() + "/README");
            InputStream is = url.openStream();
            MessageDigest md = MessageDigest.getInstance("MD5");
            String digest = Sarea.getDigest(is, md, 2048);

            badago(digest, url.toString());
        }catch (FileNotFoundException f){
            mezua.setText("Ez du aurkitu url-a");
        }

    }

    private void badago(String md5,String url) throws SQLException {
        Boolean dago= ZerbitzuKud.getInstance().badago(md5);
        if(dago){
            mezua.setText("Datubasean zegoen");
            Checksums c=ZerbitzuKud.getInstance().lortu(md5);
            c.setUrl(url);
            eguneratu(c);
        }else {
            mezua.setText("Ez da datubasean aurkitu");
            Checksums c=new Checksums(md5);
            c.setPath("README");
            c.setUrl(url);
            eguneratu(c);

        }

    }

    public void eguneratu(Checksums c) {
        taula.getItems().add(c);
        for(Checksums checksums : taula.getItems()) {
            urlZut.setCellValueFactory(new PropertyValueFactory<>("url"));
            md5Zut.setCellValueFactory(new PropertyValueFactory<>("md5"));
            versionZut.setCellValueFactory(new PropertyValueFactory<>("version"));

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taula.setEditable(true);
        versionZut.setCellFactory(TextFieldTableCell.forTableColumn());
        versionZut.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setVersion(t.getNewValue());
                    Checksums checksums=t.getTableView().getItems().get(t.getTablePosition().getRow());
                    ZerbitzuKud.getInstance().sartu(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    mezua.setText("md5 eta bertsio berria datubasean sartu dira");
                });
    }

    public void setMainApp(App app) {
    }
}
