/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifro.br.Control;

import edu.ifro.br.Model.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author 03783385288
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Entrar(ActionEvent event) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
       
        Query query = em.createQuery("SELECT u FROM Usuario as u WHERE u.usuario = :user");
        query.setParameter("user", txtUsuario.getText());
        
         if(query.getResultList().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("O usuário não existe");
            alert.showAndWait();
        }
        else{
            Login l = (Login) query.getSingleResult();
                if (!l.getSenha().equals(txtSenha.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Senha não confere");
                alert.showAndWait();
            }
            else{        
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/Calculadora.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Calculadora");
                stage.setScene(scene);
                stage.show();
            }
         }
    }
} 

