package pers.qianshifengyi.test;


import com.alibaba.fastjson.JSONObject;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.sempre.*;
import edu.stanford.nlp.sempre.corenlp.CoreNLPAnalyzer;
import edu.stanford.nlp.sempre.freebase.SparqlExecutor;
import fig.basic.Utils;
import org.junit.Test;

import java.net.URL;
import java.util.*;

/**
 * Created by zhangshan193 on 17/3/9.
 */
public class AnalyzerTest {

    @Test
    public void test1(){

        String utterance = "";
        // zs-freebase.grammar
        // can you tell me what kinds of unsecured products you have two plus
        //utterance = "first word in zyz ccc aaa compositionality is key";
        //utterance = "first aaa in zyz ccc aaa compositionality in is key";
        //utterance = "two word in compositionality in is key";

        //utterance = "two times length of hello world";
        utterance = "length of hello world times two";
        // SimpleLexiconFn formula 或 type 原样替换

        // HanLPAnalyzer zhnlp=new HanLPAnalyzer();
        LanguageAnalyzer languageAnalyzer = null;
        languageAnalyzer = new CoreNLPAnalyzer();
        //languageAnalyzer = new SimpleAnalyzer();
        LanguageInfo Utterance_lang=languageAnalyzer.analyze(utterance);
        Example.Builder b = new Example.Builder();
        Example ex = b.createExample();
        ex.languageInfo=Utterance_lang;
        SparqlExecutor.opts.endpointUrl = "http://10.59.97.75:3002/sparql";
        SparqlExecutor.opts.verbose = 3;
        Executor executor = null;
        executor= new JavaExecutor();
       // executor = new SparqlExecutor();
        Builder builder = new Builder();
        builder.executor = executor;
        Grammar g = new Grammar();
        g.read();
        builder.grammar = g;
        builder.buildUnspecified();
        try{
            builder.parser.parse(builder.params, ex, false);
        }catch(Exception e){
            e.printStackTrace();
        }

        List<Derivation> derivs = ex.getPredDerivations();
        List<Map> queryFormulas = new ArrayList<Map>();
        if(derivs != null ){
            System.out.println("derivs.size:"+derivs.size());

            for(int i=0;i<derivs.size();i++){

                System.out.println("i="+i+","+derivs.get(i).getFormula()+"--"+executor.execute(derivs.get(i).getFormula(), null).value);
                Map<String,Object> Map1 = new HashMap<String,Object>();
                Map1.put("formulas",executor.execute(derivs.get(i).getFormula(), null).value.toString());
                Map1.put("queryFormulas",derivs.get(i).getFormula().toString());
                queryFormulas.add(Map1);

            }
        }

    }


}
