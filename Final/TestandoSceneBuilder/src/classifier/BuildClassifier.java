/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;




public class BuildClassifier {
    private Instances dados;
    
   
    public J48 getClassifier() throws Exception{
        
        DataSource arquivo = new DataSource("classifier/dates/hepatite.arff");
        this.dados = arquivo.getDataSet();

        AttributeSelection selAtributo = new AttributeSelection();
        InfoGainAttributeEval avaliador = new InfoGainAttributeEval();
        Ranker busca = new Ranker();

        selAtributo.setEvaluator(avaliador);
        selAtributo.setSearch(busca);
        selAtributo.SelectAttributes(this.dados);

        int[] indices = selAtributo.selectedAttributes();

        String[] opcoes = new String[1];
        opcoes[0] = "-U";

        J48 arvore = new J48();
        arvore.setOptions(opcoes);
        arvore.buildClassifier(dados);
        
        return arvore;
    }
   
    public Instances getDados(){
        return dados;
    }
   
}
