package leituraarff;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;

/** @author gabriel.ribeiro */
public class LeituraArff {

    public static void main(String[] args) {
        
        try {
            
            // Referência ao arquivo arff
            DataSource file = new DataSource("data/zoo.arff");
            Instances data = file.getDataSet();
            System.out.println("Instances: " + data.numInstances());
            
            // Definição dos parametros de exclusão (Remover nome dos animais)
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
            int[] indexes = selAttribute.selectedAttributes();
            System.out.println("Selected Attributes: " + Utils.arrayToString(indexes));
            
            // Exibindo arvore dos dados (estrutura de decisão)
            String[] options = new String[1];
            options[0] = "-U";
            J48 tree  = new J48();
            tree.setOptions(options);
            tree.buildClassifier(data);
            System.out.println(tree);
        }
        catch(Exception e) {
            
            // Resgistro da Exceção
            System.out.println(e.getMessage());
        }
    }
    
}
