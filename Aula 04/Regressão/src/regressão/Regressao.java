package regressão;

import java.io.File;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.M5P;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Regressao {

    public static void main(String[] args) {
        
       try {
           
           // Realização da leitura do arquivo csv
            CSVLoader loader = new CSVLoader();
            loader.setFieldSeparator(",");
            loader.setSource(new File("data/ENB2012_data.csv"));
            Instances data = loader.getDataSet();
            data.setClassIndex(data.numAttributes() - 2);
            
            // Removendo dados não necessários
            Remove remover = new Remove();
            remover.setOptions(new String[] {"-R", data.numAttributes() + ""});
            remover.setInputFormat(data);
            data = Filter.useFilter(data, remover);
            
            // Definindo regressão
            LinearRegression model = new LinearRegression();
            model.buildClassifier(data);
            
            // Avaliando o algoritimo
            Evaluation validation = new Evaluation(data);
            validation.crossValidateModel(model, data, 10, new Random(1), new String[] {});
            System.out.println(validation.toSummaryString());   // 22% erro
            
            // Criação de arvores de regressão para aumentar precisão do algoritimo
            M5P md5 = new M5P();
            md5.setOptions(new String[] { "" });
            md5.buildClassifier(data);
            System.out.println(md5);
            
            // Reavaliando o código
            validation.crossValidateModel(md5, data, 10, new Random(1), new String[] {});
            System.out.println(validation.toSummaryString());   // 14% erro
        
       } catch (Exception err) { System.out.println(err.getMessage()); }      
    }  
}
