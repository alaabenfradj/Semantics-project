package com.example.websemantique.controller;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tools.JenaEngine;

//import javax.ws.rs.Produces;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RestController
@RequestMapping("/ws")
@CrossOrigin("http://localhost:3000/")
public class TestController {

	@GetMapping("/test")
	public String testingController(){
		return "Alaa Ben Fradj";
	}


//school
    @GetMapping(value="/school/getbyname/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSchoolByname(@PathVariable(value = "name") String name) throws UnsupportedEncodingException {

    	String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "SELECT ?Adress ?ID ?name ?Email \n" +
                "\n" +
                "WHERE {\n" +
                "?School ns:Adress ?Adress ;\n" +
                "             ns:ID ?ID ;" +
                "             ns:name ?Name ;" +
                "             ns:Email ?Email ;\n" +
                "FILTER ( ( ?name = '"+name+"' )  ) " +
                "}\n" +
                "\n";

        Model model = JenaEngine.readModel("data/schooli.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

        Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }
   
    @GetMapping(value="/school/getbyadress/{Adress}",produces=MediaType.APPLICATION_JSON_VALUE)
    
    public String getSchoolByAdress(@PathVariable(value = "Adress") String adress) throws UnsupportedEncodingException {

    	String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "SELECT ?Adress ?ID ?name ?Email \n" +
                "\n" +
                "WHERE {\n" +
                "?School ns:Adress ?Adress ;\n" +
                "             ns:ID ?ID ;" +
                "             ns:name ?Name ;" +
                "             ns:Email ?Email ;\n" +
                "FILTER ( ( ?Adress = '"+adress+"' )  ) " +
                "}\n" +
                "\n";

        Model model = JenaEngine.readModel("data/schooli.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

        Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }
@GetMapping(value="/school/getall",produces=MediaType.APPLICATION_JSON_VALUE)
    
    public String getAllSchool() throws UnsupportedEncodingException {

    	String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "SELECT ?Adress ?ID ?name ?Email \n" +
                "\n" +
                "WHERE {\n" +
                "?School ns:Adress ?Adress ;\n" +
                "             ns:ID ?ID ;" +
                "             ns:name ?Name ;" +
                "             ns:Email ?Email ;\n" +
                "}\n" +
                "\n";

        Model model = JenaEngine.readModel("data/schooli.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

        Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }


@GetMapping(value="/school/getbyemail/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
public String getSchoolByEmail(@PathVariable(value = "email") String email) throws UnsupportedEncodingException {

	String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "\n" +
            "SELECT ?Adress ?ID ?name ?Email \n" +
            "\n" +
            "WHERE {\n" +
            "?School ns:Adress ?Adress ;\n" +
            "             ns:ID ?ID ;" +
            "             ns:name ?Name ;" +
            "             ns:Email ?Email ;\n" +
            "FILTER CONTAINS ( lcase(?Email) , '"+email.toLowerCase()+"'  ) " +
            "}\n" +
            "\n";

    Model model = JenaEngine.readModel("data/schooli.owl");

    QueryExecution qe = QueryExecutionFactory.create(qexec, model);
    ResultSet results = qe.execSelect();


    // write to a ByteArrayOutputStream
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ResultSetFormatter.outputAsJSON(outputStream, results);

    // and turn that into a String
    String json = new String(outputStream.toByteArray());

    JSONObject j = new JSONObject(json);
    System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

    Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



    return j.getJSONObject("results").getJSONArray("bindings").toString();

}
@GetMapping(value="/school/getbydepartment/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
public String getSchoolByDepartement(@PathVariable(value = "name") String name) throws UnsupportedEncodingException {

	String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "\n" +
            "\n" +
            "SELECT ?name ?Adress ?Email ?ID  \n" +
            "WHERE {\n" +
            "?School ns:has_department ?Department.\n" +
            "?Department ns:name ?Name.\n" + 
            "?School ns:name ?name.\n" +
            "?School ns:Adress ?Adress.\n" +
            "?School ns:Email ?Email.\n" +
            "?School ns:ID ?ID.\n" +
            "FILTER ( ( ?Name = '"+name+"' )  ) " +
            "}";

    Model model = JenaEngine.readModel("data/schooli.owl");

    QueryExecution qe = QueryExecutionFactory.create(qexec, model);
    ResultSet results = qe.execSelect();


    // write to a ByteArrayOutputStream
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ResultSetFormatter.outputAsJSON(outputStream, results);

    // and turn that into a String
    String json = new String(outputStream.toByteArray());

    JSONObject j = new JSONObject(json);
    System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

    Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



    return j.getJSONObject("results").getJSONArray("bindings").toString();

}
  //students  
@GetMapping("/students/all")

public String getAllStudents() throws UnsupportedEncodingException {

    String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "\n" +
            "SELECT ?Adress ?Age ?Degree ?is_student_of ?Email ?First_Name ?Last_Name\n" +
            "\n" +
            "WHERE {\n" +
            "?Student ns:Adress ?Adress ;\n" +
            "             ns:Email ?Email ;\n" +
            "             ns:First_Name ?First_Name ;\n" +
            "             ns:is_student_of ?is_student_of ;\n" +
            "             ns:Age ?Age ;" +
            "             ns:Last_Name ?Last_Name ;\n" +
            "}\n" +
            "\n";


    Model model = JenaEngine.readModel("data/schooli.owl");

    QueryExecution qe = QueryExecutionFactory.create(qexec, model);
    ResultSet results = qe.execSelect();


    // write to a ByteArrayOutputStream
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ResultSetFormatter.outputAsJSON(outputStream, results);

    // and turn that into a String
    String json = new String(outputStream.toByteArray());

    JSONObject j = new JSONObject(json);
    System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

    JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




    return j.getJSONObject("results").getJSONArray("bindings").toString();

}
	 @GetMapping(value = "/students/studentbyadress/{adress}",produces = MediaType.APPLICATION_JSON_VALUE)
public String getStudentByAdress(@PathVariable(value = "adress")String adress) throws UnsupportedEncodingException {

 String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "\n" +
            "\n" +
            "SELECT ?Adress ?Age ?Degree ?Email ?First_Name ?Last_Name\n" +
            "WHERE {\n" +
            "?Student ns:Adress ?Adress ;\n" +
            "             ns:Email ?Email ;\n" +
            "             ns:First_Name ?First_Name ;\n" +
            "             ns:Age ?Age ;" +
            "             ns:Last_Name ?Last_Name ;\n" +
            "FILTER CONTAINS ( lcase(?Adress) , '"+adress.toLowerCase()+"'  ) " +
            "}\n" +
            "\n";


    Model model = JenaEngine.readModel("data/schooli.owl");

    QueryExecution qe = QueryExecutionFactory.create(qexec, model);
    ResultSet results = qe.execSelect();


    // write to a ByteArrayOutputStream
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ResultSetFormatter.outputAsJSON(outputStream, results);

    // and turn that into a String
    String json = new String(outputStream.toByteArray());

    JSONObject j = new JSONObject(json);
    System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

    JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




    return j.getJSONObject("results").getJSONArray("bindings").toString();

}

	 @GetMapping(value="/getstudentbyteacher/email/{email}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getTeacherByEmailAndStudents(@PathVariable(value = "email") String email) {
	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?Last_Name ?First_Name ?Degree ?Email ?ID \n" +
	                "WHERE {\n" +
	                "?Teacher ns:is_teacher_of ?Student.\n" +
	                "?Teacher ns:Email ?email.\n" +
	                "?Student ns:Last_Name ?Last_Name.\n" + 
	                "?Student ns:First_Name ?First_Name.\n" + 
	                "?Student ns:Degree ?Degree.\n" + 
	                "?Student ns:Email ?Email.\n" + 
	                "?Student ns:ID ?ID.\n" + 
	                
	                "FILTER ( ( ?email = '"+email+"' )  ) " +
	         
	                "}";

	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();

	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    
	    

	    @GetMapping(value="/student/email/{email}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getStudents(@PathVariable(value = "email") String email) {

	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?Teacher ?Student \n" +
	                "WHERE {\n" +
	                "?Student ns:is_student_of ?Teacher ;\n" +
	          
	                "}";
	        
	        

	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();

	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	 
	    //department
	    @GetMapping(value = "/departmentByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getDepartmentByName(@PathVariable(value = "name")String name) throws UnsupportedEncodingException {

	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?Name ?Status ?ID\n" +
	                "WHERE {\n" +
	                "?Student ns:name ?Name ;\n" +
	                "             ns:status ?Status ;\n" +
	                "ns:ID ?ID\n" +
	                "FILTER CONTAINS ( lcase(?Name) , '"+name.toLowerCase()+"'  ) " +
	                "}\n" +
	                "\n";


	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();


	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    @GetMapping(value = "/departmentByStatus",produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getActiveDepartment() throws UnsupportedEncodingException {
       Boolean statusBoolean=true;
	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?name ?status ?ID\n" +
	                "WHERE {\n" +
	                "?Student ns:name ?name ;\n" +
	                "             ns:status ?status ;\n" +
	                "ns:ID ?ID\n" +
	                "FILTER ( ( ?status = '"+statusBoolean+"' )  ) " +
	                "}\n" +
	                "\n";


	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();


	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    
	    @GetMapping(value = "/getalldepartment",produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getAllDepartment() throws UnsupportedEncodingException {
	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?name ?status ?ID\n" +
	                "WHERE {\n" +
	                "?Student ns:name ?name ;\n" +
	                "             ns:status ?status ;\n" +
	                "ns:ID ?ID\n" +
	                "}\n" +
	                "\n";


	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();


	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }  
	    
	    //blocs
	    
	    @GetMapping(value ="/blocs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	    public String getAllBlocs() throws UnsupportedEncodingException {

	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?ID ?name ?status\n" +
	                "WHERE {\n" +
	                "?Bloc ns:ID ?ID ;\n" +
	                "     ns:name ?name ;\n" +
	                "     ns:status ?status ;\n" +
	                "}\n" +
	                "\n";

	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();


	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

	        Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    @GetMapping(value="/blocByDepartment/{departmentName}" ,produces =  MediaType.APPLICATION_JSON_VALUE)
	    public String getBlocsByDepartments(@PathVariable(value = "departmentName") String departmentName) {
	        System.out.println(departmentName);
	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?name ?status ?ID \n" +
	                "WHERE {\n" +
	                "?Department ns:name ?Name.\n" +
	                "?Department ns:has_bloc ?Bloc.\n" +
	                "?Bloc ns:name ?name.\n" +
	                "?Bloc ns:status ?status.\n" +
	                "?Bloc ns:ID ?ID.\n" +
	                "FILTER ( ( ?Name = '"+departmentName+"' )  ) " +
	                "}\n" +
	                "\n";

	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();

	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    @GetMapping(value="/departmentByBloc/{blocName}" ,produces =  MediaType.APPLICATION_JSON_VALUE)
	    public String getDepartmentByBloc(@PathVariable(value = "blocName") String blocName) {
	        String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n" +
	                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	                "\n" +
	                "\n" +
	                "SELECT ?name ?status ?ID \n" +
	                "WHERE {\n" +
	                "?Bloc ns:belongs_to_departement ?Department.\n" +
	                "?Bloc ns:name ?blocName.\n" +
	                "?Department ns:name ?name.\n" +
	                "?Department ns:status ?status.\n" +
	                "?Department ns:ID ?ID.\n" +
	                "FILTER ( ( ?blocName = '"+blocName+"' )  ) " +
	                "}\n" +
	                "\n";

	        Model model = JenaEngine.readModel("data/schooli.owl");

	        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
	        ResultSet results = qe.execSelect();

	        // write to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        ResultSetFormatter.outputAsJSON(outputStream, results);

	        // and turn that into a String
	        String json = new String(outputStream.toByteArray());

	        JSONObject j = new JSONObject(json);
	        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

	        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


	        return j.getJSONObject("results").getJSONArray("bindings").toString();

	    }
	    
	    //teacher
	    
	    
	    @GetMapping(value = "/teacher/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
		public String getAllTeacherByName(@PathVariable(value = "name") String name) {

			String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "\n" + "\n"
					+ "SELECT ?Degree ?Email ?First_Name ?ID ?Last_Name\n" + "WHERE {\n" + "?Teacher ns:Degree ?Degree ;\n"
					+ "             ns:Email ?Email ;\n" + "             ns:First_Name ?First_Name ;\n"
					+ "             ns:ID ?ID ;\n" + "             ns:Last_Name ?Last_Name ;" + "FILTER ( ( ?First_Name = '"
					+ name + "' )  ) " + "}";

			
			Model model = JenaEngine.readModel("data/schooli.owl");

			QueryExecution qe = QueryExecutionFactory.create(qexec, model);
			ResultSet results = qe.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsJSON(outputStream, results);

			// and turn that into a String
			String json = new String(outputStream.toByteArray());

			JSONObject j = new JSONObject(json);
			System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

			JSONArray res = j.getJSONObject("results").getJSONArray("bindings");

			return j.getJSONObject("results").getJSONArray("bindings").toString();

		}

		@GetMapping(value = "/teacher/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public String getAllTeachers() {

			String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "\n" + "\n"
					+ "SELECT ?Degree ?Email ?First_Name ?ID ?Last_Name\n" + "WHERE {\n" + "?Teacher ns:Degree ?Degree ;\n"
					+ "             ns:Email ?Email ;\n" + "             ns:First_Name ?First_Name ;\n"
					+ "             ns:ID ?ID ;\n" + "             ns:Last_Name ?Last_Name ;" + "}";

			Model model = JenaEngine.readModel("data/schooli.owl");

			QueryExecution qe = QueryExecutionFactory.create(qexec, model);
			ResultSet results = qe.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsJSON(outputStream, results);

			// and turn that into a String
			String json = new String(outputStream.toByteArray());

			JSONObject j = new JSONObject(json);
			System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

			JSONArray res = j.getJSONObject("results").getJSONArray("bindings");

			return j.getJSONObject("results").getJSONArray("bindings").toString();

		}

		@GetMapping(value = "/teacher/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
		public String getStudentByTeacherEmail(@PathVariable(value = "email") String email) {
			String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "\n" + "\n"
					+ "SELECT ?First_Name ?Last_Name ?Email ?Age ?Adress \n"
					+ "WHERE {\n" + "?Teacher ns:is_teacher_of ?Student.\n" + "?Teacher ns:Email ?EmailTeacher.\n"
					+ "?Student ns:First_Name ?First_Name.\n"
					+ "?Student ns:Last_Name ?Last_Name.\n" + "?Student ns:Email ?Email.\n"
					+ "?Student ns:Age ?Age.\n" + "?Student ns:Adress ?Adress.\n" + "FILTER ( ( ?EmailTeacher = '"
					+ email + "' )  ) " +

					"}";

			Model model = JenaEngine.readModel("data/schooli.owl");

			QueryExecution qe = QueryExecutionFactory.create(qexec, model);
			ResultSet results = qe.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsJSON(outputStream, results);

			// and turn that into a String
			String json = new String(outputStream.toByteArray());

			JSONObject j = new JSONObject(json);
			System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

			JSONArray res = j.getJSONObject("results").getJSONArray("bindings");

			return j.getJSONObject("results").getJSONArray("bindings").toString();

		}

		@GetMapping(value = "/cours/teacher/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
		public String getCoursByTeahcer(@PathVariable(value = "email") String email) {
			System.out.println(email);
			String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "\n" + "\n"
					+ "SELECT ?ID ?nb ?end_date ?start_date  \n" + "WHERE {\n"
					+ "?Course ns:teached_by ?Teacher.\n" + "?Teacher ns:Email ?Email.\n"
					+ "?Teacher ns:First_Name ?First_Name.\n" + "?Teacher ns:Last_Name ?Last_Name.\n"
					+ "?Teacher ns:Degree ?Degree.\n" + "?Course ns:Nomber_of_chapter ?nb.\n"
					+ "?Course ns:end_date ?end_date.\n" + "?Course ns:ID ?ID.\n" + "?Course ns:start_date ?start_date.\n" + "FILTER ( ( ?Email = '"
					+ email + "' )  ) " +

					"}";

			Model model = JenaEngine.readModel("data/schooli.owl");

			QueryExecution qe = QueryExecutionFactory.create(qexec, model);
			ResultSet results = qe.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsJSON(outputStream, results);

			// and turn that into a String
			String json = new String(outputStream.toByteArray());

			JSONObject j = new JSONObject(json);
			System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

			JSONArray res = j.getJSONObject("results").getJSONArray("bindings");

			return j.getJSONObject("results").getJSONArray("bindings").toString();

		}

		@GetMapping(value = "/tours/all/search/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
		public String searchTeachers(@PathVariable(value = "value") String value) {

			String qexec = "PREFIX ns: <http://www.semanticweb.org/ousse/ontologies/2022/9/smartschool#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "\n" + "\n"
					+ "SELECT ?ID ?Degree ?Email ?First_Name ?Last_Name \n" + "WHERE {\n" + "?Teacher ns:Degree ?Degree ;\n"
					+ "             ns:Email ?Email ;\n" + "             ns:ID ?ID ;\n" +  "             ns:First_Name ?First_Name ;\n"
					+ "             ns:Last_Name ?Last_Name ;\n" + "FILTER CONTAINS (lcase(?Email) , '"
					+ value.toLowerCase() + "'  ) " + "}";

			Model model = JenaEngine.readModel("data/schooli.owl");

			QueryExecution qe = QueryExecutionFactory.create(qexec, model);
			ResultSet results = qe.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsJSON(outputStream, results);

			// and turn that into a String
			String json = new String(outputStream.toByteArray());

			JSONObject j = new JSONObject(json);
			System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

			JSONArray res = j.getJSONObject("results").getJSONArray("bindings");

			return j.getJSONObject("results").getJSONArray("bindings").toString();

		}
	    
	    

}
