/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author DO.ITSUDPARIS
 */
public class JenaEngine {

    static private String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";


    static public Model readModel(String inputDataFile) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputDataFile);
        if (in == null) {
            System.out.println("Ontology file: " + inputDataFile + "not found");
            return null;
        }
        //read the RDF/XML file
        model.read(in, "");
        try {
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return null;
        }
        return model;
    }


    static public Model readInferencedModelFromRuleFile(Model model, String inputRuleFile) {
        InputStream in = FileManager.get().open(inputRuleFile);
        if (in == null) {
            System.out.println("Rule File: " + inputRuleFile + " notfound");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }
        List rules = Rule.rulesFromURL(inputRuleFile);
        GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
        reasoner.setDerivationLogging(true);
        reasoner.setOWLTranslation(true);
        // not needed in RDFS case
        reasoner.setTransitiveClosureCaching(true);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        return inf;
    }


    static public ResultSet executeQuery(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        // No reasoning
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        ResultSetFormatter.out(output, results, query);
        return results;
    }


    static public ResultSet executeQueryFile(Model model, String
            filepath) {
        File queryFile = new File(filepath);
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(filepath);
        if (in == null) {
            System.out.println("Query file: " + filepath + " notfound");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }
        String queryString = FileTool.getContents(queryFile);
        return executeQuery(model, queryString);
    }


    static public String executeQueryString(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        // No reasoning
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        ResultSetFormatter.outputAsJSON(results);
        return output.toString();
    }


    static public String executeQueryFileString(Model model, String
            filepath) {
        File queryFile = new File(filepath);
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(filepath);
        if (in == null) {
            System.out.println("Query file: " + filepath + " notfound");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }
        String queryString = FileTool.getContents(queryFile);
        return executeQueryString(model, queryString);
    }

    static public OutputStream executeQueryOutputStream(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        // No reasoning
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        ResultSetFormatter.outputAsJSON(results);
        return output;
    }


    static public OutputStream executeQueryFileOutputStream(Model model, String
            filepath) {
        File queryFile = new File(filepath);
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(filepath);
        if (in == null) {
            System.out.println("Query file: " + filepath + " notfound");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }
        String queryString = FileTool.getContents(queryFile);
        return executeQueryOutputStream(model, queryString);
    }
}