<!-- 
 //   Document   : index
  //  Created on : Dec 15, 2014, 5:06:27 PM
   // Author     : sharelison-->

<%@page import="org.json.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<% try {
    Class.forName("org.neo4j.jdbc.Driver");
  }
  catch(Exception e) {
      out.write("asdfasdf");
} %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <meta charset="utf-8">
         <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <title>JSP Page</title>
    </head>
    <body>
	<div id="main">
	
	<div id="menubar">
	  <div id="welcome">
	    <h1><a href="#">G.entlemens</a></h1>
	  </div><!--close welcome-->
      <div id="menu_items">
	    <ul id="menu">
          <li><a href="index.html">Home</a></li>
          <li class="current"><a href="index2.jsp">Build your own PC</a></li>
		  <li><a href="producten.jsp">Onze Producten</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div><!--close menu-->
    </div><!--close menubar-->	
    
	<div id="site_content">	
		<h2> 
                    Welcome to the ultimate PC building website
                </h2>
		<p>
                    The latest project of the G.entlemens allows you to keep a close eye on the fluxuating PC part prizes. Also if you're interested in building your own PC from scratch w'll give you a step by step tutorial just click on the PC builder link in the navigation tab!
                </p>
                <h4>
                    How it works
                </h4>
                <p>
                    Give a total value you want to spend on generating a computer in the 'total price' and hit enter, done.
                </p>
	</div><!--close site_content-->

        <div id="content_blue">
            <form NAME="form1" METHOD="POST">
                <input type="text" name="total_price" placeholder="Total price">
                <br>
                <input type="submit" class='button_small' value="Enter">
            </form>
        </div>
        

        <script language="Javascript">   
            
            function button1() {
                document.form1.buttonName.value = "button 1";
                form.submit();
            }

        </script>
        
        <%  
            String myPar = request.getParameter("total_price");
            Connection con = (Connection) DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
            if (myPar != null){
                
                String query = ("MATCH (n:`Motherboard`) WHERE n.Price < (0.16 *" + myPar + ") AND n.Price > (0.10 * " + myPar +") RETURN n, rand() as name order by name  limit 1");
                PreparedStatement state = con.prepareStatement(query);
                ResultSet rs = state.executeQuery();
                while(rs.next()) {
                    JSONObject jso = new JSONObject(rs.getString("n").trim());
                    String name = jso.get("Name").toString();                        
                        String nameMotherboard = name;
                        double price_motherboard = jso.getDouble("Price");

                        double price_processor = 0.00;
                        String nameProcessor   = "nothing";
                        
                        double price_memory = 0.00;
                        String name_memory = "nothing";
                        
                        double price_case = 0.00;
                        String name_case = "nothing";
                        
                        double price_videocard = 0.00;
                        String name_videocard = "nothing";
                        
                        double price_power_supply =0.00;
                        String name_power_supply = "nothing";
                        
                        double price_harddisk = 0.00;
                        String name_harddisk = "nothing";
                    
                    try {    
                        String queryProcessor = ("MATCH (n:`Motherboard`)-[IS_COMPATIBLE_WITH]->(b:`Processor`) WHERE b.Price < (0.22 * " + myPar +") AND b.Price > (0.13 * " + myPar +") RETURN n,b limit 1 ");    
                        JSONObject processor_json =  executeQuery(queryProcessor);
                        nameProcessor   = processor_json.getString("Name");
                        price_processor = processor_json.getDouble("Price");
                        
                        
                        String queryMemory = ("MATCH (n:`Motherboard`{Link:'"+jso.get("Link").toString()+"'})-[IS_COMPATIBLE_WITH]->(b:`Memory`) WHERE b.Price < (0.15 * " + myPar +") AND b.Price > (0.08 * " + myPar +") RETURN n,b limit 1");
                        JSONObject memory_json = executeQuery(queryMemory);
                        name_memory = memory_json.getString("Name");
                        price_memory = memory_json.getDouble("Price");
                        
                        
                        String queryCase = ("MATCH (n:`Motherboard`{Link:'"+jso.get("Link").toString()+"'})-[IS_COMPATIBLE_WITH]->(b:`Case`) WHERE b.Price < (0.09 * " + myPar +") AND b.Price > (0.02 * " + myPar +") RETURN n,b limit 1");
                        JSONObject case_json = executeQuery(queryCase);
                        name_case = case_json.getString("Name");
                        price_case = case_json.getDouble("Price");
                        
                        
                        String queryVideocard = ("MATCH (b:`Videocard`) WHERE b.Price < (0.25 * " + myPar +") AND b.Price > (0.15 * " + myPar +") RETURN b, RAND() AS name ORDER BY name limit 1");
                        JSONObject videocard_json = executeQuery(queryVideocard);
                        name_videocard = videocard_json.getString("Name");
                        price_videocard = videocard_json.getDouble("Price");
                        
                        String queryPowerSupply =("MATCH (b:`Power Supply`) WHERE b.Price < 50 AND b.Price > 45 RETURN b limit 1");
                        JSONObject power_supply_json = executeQuery(queryVideocard);
                        name_power_supply = power_supply_json.getString("Name");
                        price_power_supply = power_supply_json.getDouble("Price");
                        
                        String queryHarddisk = ("MATCH (b:`Harddisk`) WHERE b.Price < 55  AND b.Price > 50 RETURN b limit 1");
                        JSONObject harddisk_json = executeQuery(queryVideocard);
                        name_harddisk = harddisk_json.getString("Name");
                        price_harddisk = harddisk_json.getDouble("Price");
                    }
                    catch(Exception e) {
                        
                    } 
                        
                    
                        
                    
                        
                        
        %>
                
            <div class="matcher_output">
                    <p>
                        <%=name%>
                        <%=price_motherboard%>
                    </p>
                    
                    <p>
                        <%=nameProcessor%>
                        <%=price_processor%>
                    </p>
                    
                    <p>
                       <%=name_memory%>
                        <%=price_memory%>
                    </p> 
                    
                    <p>
                        <%=name_case%>
                        <%=price_case%>
                    </p>
                    
                    <p>
                        <%=name_videocard%>
                        <%=price_videocard%>
                    </p>
                    
                    <p>
                        <%=name_power_supply%>
                        <%=price_power_supply%>
                    </p>
                    
                    <p>    
                        <%=name_harddisk%>
                        <%=price_harddisk%>
                    </p>    
            </div>
                
        <%
                    break;
                }
            }
            
        %>
        
        <%!    
            public static JSONObject executeQuery(String query) throws SQLException, JSONException{
                Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
                PreparedStatement state = con.prepareStatement(query);
                ResultSet rs = state.executeQuery();
                String nameResult = "nothing";
                JSONObject jso = new JSONObject();
                
                while(rs.next()) {
                   jso = new JSONObject(rs.getString("b").trim());
                   String name = jso.get("Name").toString();        
                   nameResult = name;                   
                }
                return jso;
                
            }
         %>
    </body>
</html>
