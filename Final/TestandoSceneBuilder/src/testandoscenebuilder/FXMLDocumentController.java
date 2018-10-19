/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandoscenebuilder;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import classifier.BuildClassifier;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author wellington
 */
public class FXMLDocumentController implements Initializable {
    
    private double cancer[] = new double[19];
    private J48 arvore;
    private Instances dados;
    
    @FXML
    private ImageView imgView;
    
    @FXML
    private SplitMenuButton menuButton;
    
    @FXML 
    private MenuItem menuSexoFemale;
    
    @FXML 
    private MenuItem menuSexoMale;
    
    @FXML
    private TextField txtIdade;
    
    @FXML
    private SplitMenuButton menuSexo;
    
    @FXML
    private CheckBox checkSteroid;
    
    @FXML
    private CheckBox checkAntVirals;
    
    @FXML
    private CheckBox checkFatigue;
    
    @FXML
    private CheckBox checkMalaise;
    
    @FXML
    private CheckBox checkAnorexia;
    
    @FXML
    private CheckBox checkFigadoGrande;
    
    @FXML
    private CheckBox checkFigadoFirme;
    
    @FXML
    private CheckBox checkBacoPalpavel;
    
    @FXML
    private CheckBox checkSpiders;
    
    @FXML
    private CheckBox checkAscites;
    
    @FXML
    private CheckBox checkVarizes;
    
    @FXML
    private TextField txtBili;
    
    @FXML
    private TextField txtAlkaline;
    
    @FXML
    private TextField txtSgot;
    
    @FXML
    private TextField txtAlbumin;
    
    @FXML
    private TextField txtProtime;
    
    @FXML
    private CheckBox checkHistology;
    
    @FXML
    private Button btnClick;
    
    public FXMLDocumentController() throws Exception{
        initCancer();
        initClassifier();
    }

    
    
    @FXML
    private void clicouBotao(ActionEvent event) throws Exception {
        String result = getResult();
        System.out.println(result);
        if(result.equals("DIE")){
            Image image = new Image("images/die.jpg");
            this.imgView.setImage(image);
        } else if(result.equals("LIVE")){
            Image image = new Image("images/live.jpg");
            imgView.setImage(image);
        } else {
            JOptionPane.showMessageDialog(null, "Insira os dados corretos!");
        }
    }
   
    @FXML
    private void selectFemale(ActionEvent event) {
        this.cancer[1] = 1.0;
        this.menuButton.setText("Female");
        
    }
    
    @FXML
    private void selectMale(ActionEvent event) {
        this.cancer[1] = 0.0;
        this.menuButton.setText("Male");
    }
    
    @FXML
    private void clickSteroid(ActionEvent event) {
        
        if(this.cancer[2] == 0.0)
            
            this.cancer[2] = 1.0;
        
        else
            
            this.cancer[2] = 0.0;
        
    }
    
    @FXML
    private void clickAntivirals(ActionEvent event)  {
        if(this.cancer[3] == 0.0)
            
            this.cancer[3] = 1.0;
        
        else
            
            this.cancer[3] = 0.0;
    }
    
    @FXML
    private void clickFatiga(ActionEvent event) {
        if(this.cancer[4] == 0.0)
            
            this.cancer[4] = 1.0;
        
        else
            
            this.cancer[4] = 0.0;
    }
    
    @FXML
    private void clickMalasia(ActionEvent event)  {
        if(this.cancer[5] == 0.0)
            
            this.cancer[5] = 1.0;
        
        else
            
            this.cancer[5] = 0.0;
    }
    
    @FXML
    private void clickAnorexia(ActionEvent event)  {
        if(this.cancer[6] == 0.0)
            
            this.cancer[6] = 1.0;
        
        else
            
            this.cancer[6] = 0.0;
    }
    
    @FXML
    private void clickFigadoGrande(ActionEvent event)  {
        if(this.cancer[7] == 0.0)
            
            this.cancer[7] = 1.0;
        
        else
            
            this.cancer[7] = 0.0;
    }
    
    @FXML
    private void clickFigadoFirme(ActionEvent event)  {
        if(this.cancer[8] == 0.0)
            
            this.cancer[8] = 1.0;
        
        else
            
            this.cancer[8] = 0.0;
    }
    
    @FXML
    private void clickBacoPalpavel(ActionEvent event)  {
        if(this.cancer[9] == 0.0)
            
            this.cancer[9] = 1.0;
        
        else
            
            this.cancer[9] = 0.0;
    }
   
    @FXML
    private void clickAranhas(ActionEvent event)  {
        if(this.cancer[10] == 0.0)
            
            this.cancer[10] = 1.0;
        
        else
            
            this.cancer[10] = 0.0;
    }
    
    @FXML
    private void clickAscites(ActionEvent event) {
        if(this.cancer[11] == 0.0)
            
            this.cancer[11] = 1.0;
        
        else
            
            this.cancer[11] = 0.0;
    }
    
    @FXML
    private void clickVarizes(ActionEvent event) {
        if(this.cancer[12] == 0.0)
            
            this.cancer[12] = 1.0;
        
        else
            
            this.cancer[12] = 0.0;
    }
    
    @FXML
    private void clickHistologia(ActionEvent event) throws Exception {
        if(this.cancer[18] == 0.0)
            
            this.cancer[18] = 1.0;
        
        else
            
            this.cancer[18] = 0.0;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    private int checkTextBox(){
        
        if(this.txtIdade.getText().equals("") || this.txtBili.getText().equals("") ||
           this.txtAlkaline.getText().equals("") || this.txtSgot.getText().equals("") |
           this.txtAlbumin.getText().equals("") || this.txtProtime.getText().equals(""))
           return 1;
        else
           try{
               Double.parseDouble(this.txtIdade.getText());
               Double.parseDouble(this.txtBili.getText());
               Double.parseDouble(this.txtAlkaline.getText());
               Double.parseDouble(this.txtSgot.getText());
               Double.parseDouble(this.txtAlbumin.getText());
               Double.parseDouble(this.txtProtime.getText());
           }catch(Exception e){
               return 1;
           }
        
 
        return 0;    
    }
    
    private boolean isNumber(String campo){
        
        return true;
    }
    
    private void initCancer(){
        cancer[0] = 0.0;
        cancer[1] = 1.0;
        cancer[2] = 0.0;
        cancer[3] = 0.0;
        cancer[4] = 0.0;
        cancer[5] = 0.0;
        cancer[6] = 0.0;
        cancer[7] = 0.0;
        cancer[8] = 0.0;
        cancer[9] = 0.0;
        cancer[10] = 0.0;
        cancer[11] = 0.0;
        cancer[12] = 0.0;
        cancer[13] = 0.0;
        cancer[14] = 0.0;
        cancer[15] = 0.0;
        cancer[16] = 0.0;
        cancer[17] = 0.0;
        cancer[18] = 0.0;
    }
    
    private void initClassifier() throws Exception{
        BuildClassifier buildClassifier = new BuildClassifier();
        
        J48 arvore = buildClassifier.getClassifier();
        
        Instances dados = buildClassifier.getDados();
        
        this.arvore = arvore;
        this.dados = dados;
    }
    
    private void setTextFields(){
        this.cancer[0] = Double.parseDouble(txtIdade.getText());
        this.cancer[13] = Double.parseDouble(txtBili.getText());
        this.cancer[14] = Double.parseDouble(txtAlkaline.getText());
        this.cancer[15] = Double.parseDouble(txtSgot.getText());
        this.cancer[16] = Double.parseDouble(txtAlbumin.getText());
        this.cancer[17] = Double.parseDouble(txtProtime.getText());
    }
    
    private String getResult() throws Exception{
        
        if (checkTextBox() == 0 && !this.menuButton.getText().equals("Sexo")){
            
            setTextFields();
            
            Instance cancerMama = new DenseInstance(1.0, this.cancer);
        
            cancerMama.setDataset(dados);

            double label = arvore.classifyInstance(cancerMama);

            return dados.classAttribute().value((int) label);
        }
        
        
        return ("Campos Invalidos");
    
    
    }
    
}
