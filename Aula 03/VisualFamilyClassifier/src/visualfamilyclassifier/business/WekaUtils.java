package visualfamilyclassifier.business;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;

public class WekaUtils {
    
    // Atributos
    DataSource file;
    Instances data;
    
    public WekaUtils() {
        
        try {
            
            // Referência ao arquivo arff
            file = new DataSource("data/zoo.arff");
            data = file.getDataSet();
            
            String[] param = new String[] {"-R", "1" };
            Remove filter = new Remove();
            filter.setOptions(param);
            filter.setInputFormat(data);
            data = Filter.useFilter(data, filter);
            
            // Parametros a ser exibidos
            AttributeSelection selAttribute = new AttributeSelection();
            InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
            Ranker search = new Ranker();
            selAttribute.setEvaluator(evaluator);
            selAttribute.setSearch(search);
            selAttribute.SelectAttributes(data);            
          
        } catch(Exception e) {
            
            // Resgistro da Exceção
            System.out.println(e.getMessage());
        }
    }
        
    public String Classify(double[] vals) {
        
        try {
            
            // Criar uma instância baseada nestes atributos
            Instance animal = new DenseInstance(1.0, vals);
            animal.setDataset(data);

            // Criando a arvore para classificação
            String[] opcoes = new String[1];
            opcoes[0] = "-U";
            J48 tree = new J48();
            tree.setOptions(opcoes);
            tree.buildClassifier(data);
           
            // Classificar esta nova instância
            double label = tree.classifyInstance(animal);            
            return data.classAttribute().value((int) label);
        
        } catch (Exception err) {
            System.out.println(err.getMessage());
            return "Ops... Ocorreu algum erro na classificação!";
        }
    }    
}
