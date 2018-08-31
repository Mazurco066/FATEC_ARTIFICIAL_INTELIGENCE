package visualfamilyclassifier.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import visualfamilyclassifier.business.WekaUtils;

public class FrmClassifierController {
    
    // Componentes
    @FXML private TextField txtPernas;
    @FXML private CheckBox checkPelos;
    @FXML private CheckBox checkPenas;
    @FXML private CheckBox checkOvos;
    @FXML private CheckBox checkLeite;
    @FXML private CheckBox checkAereo;
    @FXML private CheckBox checkAquatico;
    @FXML private CheckBox checkPredador;
    @FXML private CheckBox checkRabo;
    @FXML private CheckBox checkPresas;
    @FXML private CheckBox checkVertebrado;
    @FXML private CheckBox checkRespira;
    @FXML private CheckBox checkVenenoso;
    @FXML private CheckBox checkNadadeiras;
    @FXML private CheckBox checkDomestico;
    @FXML private CheckBox checkAgil;
    @FXML private Label lblResult;
    
    // Atributos
    private WekaUtils weka;
        
    @FXML
    protected void doSomething() {
        
        // Instanciando Classe de interação com framwork Weka
        weka = new WekaUtils();
        
        // recuperando dados do front-end
        double[] vals = new double[16];
        vals[0] = checkPelos.isSelected() ? 1 : 0;
        vals[1] = checkPenas.isSelected() ? 1 : 0;
        vals[2] = checkOvos.isSelected() ? 1 : 0;
        vals[3] = checkLeite.isSelected() ? 1 : 0;
        vals[4] = checkAereo.isSelected() ? 1 : 0;
        vals[5] = checkAquatico.isSelected() ? 1 : 0;
        vals[6] = checkPredador.isSelected() ? 1 : 0;
        vals[7] = checkPresas.isSelected() ? 1 : 0;
        vals[8] = checkVertebrado.isSelected() ? 1 : 0;
        vals[9] = checkRespira.isSelected() ? 1 : 0;
        vals[10] = checkVenenoso.isSelected() ? 1 : 0;
        vals[11] = checkNadadeiras.isSelected() ? 1 : 0;
        vals[12] = Integer.parseInt(txtPernas.getText());
        vals[13] = checkRabo.isSelected() ? 1 : 0;
        vals[14] = checkDomestico.isSelected() ? 1 : 0;
        vals[15] = checkAgil.isSelected() ? 1 : 0;
        
        // Chamando método criado para classificar animal
        lblResult.setText(weka.Classify(vals));
    }

}
