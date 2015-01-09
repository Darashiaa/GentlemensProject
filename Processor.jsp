﻿<%@page import="org.json.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<% try {
    Class.forName("org.neo4j.jdbc.Driver");
  }
  catch(Exception e) {
      out.write("asdfasdf");
} %>

<!DOCTYPE HTML>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
			<div id="main">
	
	<div id="menubar">
	  <div id="welcome">
	    <h1><a href="#">G.entlemens</a></h1>
	  </div><!--close welcome-->
      <div id="menu_items">
	   <ul><li>Home</li>
  <li class="current"><a href="index2.jsp">Build your own PC</a></li>
  <li>
    Our products
    <ul>
      			<li><a href="Motherboard.jsp">Motherboard</a></li>
				<li><a href="Processor.jsp">Processors</a></li>
				<li><a href="Memory.jsp">Memory</a></li>
				<li><a href="Harddisk.jsp">Harddisk</a></li>
				<li><a href="Case.jsp">Case</a></li>
				<li><a href="Videocard.jsp">Videocard</a></li>
				<li><a href="Powersupply.jsp">Power Supply</a></li>
    </ul>
  </li>
 
  <li><a href="contact.html">Contact Us</a></li>
</ul>
      </div><!--close menu-->
    </div><!--close menubar-->	
    
	<div id="site_content">	
		<h2>Welcome to the ultimate PC building website</h2>
		<p>The latest project of the G.entlemens allows you to keep a close eye on the fluctuating PC part prizes. Also if you're interested in building your own PC from scratch w'll give you a step by step tutorial just click on the PC builder link in the navigation tab!</p>
	</div><!--close site_content-->
		<div id="main_wrapper">
		
		 <%
                ArrayList<String> ProcessorNames = new ArrayList<String>(); 
				 ArrayList<String> ProcessorPrice = new ArrayList<String>();
				  ArrayList<String> ProcessorPhoto = new ArrayList<String>();
				  ArrayList<String> ProcessorDescription = new ArrayList<String>();
				  ArrayList<String> ProcessorLink = new ArrayList<String>();
				  
			
            try {
                Connection con = (Connection) DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
                String query = ("Match (n:`Processor`) return n");
            try (PreparedStatement stmt = con.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
                    JSONObject jso = new JSONObject(rs.getString("n").trim());
                    String photo = jso.get("Photo").toString();
					String name = jso.get("Name").toString();
					String price = jso.get("Price").toString();
					String link = jso.get("Link").toString();
					String description = jso.get("Description").toString();
					
					ProcessorNames.add(name);
					ProcessorPrice.add(price);
					ProcessorPhoto.add(photo);
					ProcessorLink.add(link);
					ProcessorDescription.add(description);
					
                    
        %>
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
			
		<%	for(int i = 0; i < 500; i++)
			{ %>
			
			<div id = <%= i %> class = "product_wrapper"> 	
			  <br><img src=<%=ProcessorPhoto.get(i)%> width="50%" height="50%"/></br>
			  <br><%=ProcessorNames.get(i)%></br>
			  <br>€ <%=ProcessorPrice.get(i)%></br>
					<div id = "hover_info">
				<p><a href=<%=ProcessorLink.get(i)%> class='linkToWebsite'>Go to website</a></p>	
					<b>Description:</b>		
				<p><%=ProcessorDescription.get(i)%></p>
					
				
									
				</div>
			</div>	
			
		<%		} %>
				 	


		</div><!--close main_wrapper-->

	</body>
</html>