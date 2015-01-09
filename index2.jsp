<%-- 
    Document   : index
    Created on : Dec 15, 2014, 5:06:27 PM
    Author     : sharelison
--%>
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
		<h2>Welcome to the ultimate PC building website</h2>
		<p>The latest project of the G.entlemens allows you to keep a close eye on the fluxuating PC part prizes. Also if you're interested in building your own PC from scratch w'll give you a step by step tutorial just click on the PC builder link in the navigation tab!</p>
	</div><!--close site_content-->
        <%
            try {
                Connection con = (Connection) DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
                String query = ("Match (n:`Processor`) return n limit 1");
            try (PreparedStatement stmt = con.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    JSONObject jso = new JSONObject(rs.getString("n").trim());
                    String photo = jso.get("Photo").toString();
					String name = jso.get("Name").toString();
					String price = jso.get("Price").toString();
					
                    
        %>
        
            <div id="pc_builder">
		<div id="stappenplan">
			<h1>STEP ONE: CHOOSE MOTHERBOARD</h1>
			<h2>A motherboard is one of the most essential parts of a computer system. It holds together many of the crucial components of a computer, including the central processing unit (CPU), memory and connectors for input and output devices.</h2>
			
		</div><!--close stappenplan-->
		<div id="pc_part_image">
				<img src=<%=photo%> />
			<li><h3><%=name%></h3></li>
			<li><h3><%=price%><h3></li>
			
		</div><!--close pc_part_image">
	</div><!--close pc_builder-->
        
        <%
                   // System.out.println(rs.getString("n"));
                    
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
            }catch(SQLException e) {
                out.write(e.getMessage());
            }
         %>
    </body>
</html>
