<%@page import="org.json.*"%>
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
		<link rel="stylesheet" type="text/css" href="style/style.css" />
	</head>
	
	<body>
			<div id="main">
	
	<div id="menubar">
	  <div id="welcome">
	    <h1><a href="#">G.entlemens</a></h1>
	  </div><!--close welcome-->
      <div id="menu_items">
	   <ul><li>Home</li>
  <li class="current"><a href="index2---MARTIN.jsp">Build your own PC</a></li>
  <li>
    Our products
    <ul>
      			<li><a href="Processor.jsp">Processors</a></li>
				<li><a href="Motherboard.jsp">Motherboards</a></li>
				<li><a href="Memory.jsp">Memory</a></li>
				<li><a href="Case.jsp">Case</a></li>
				<li><a href="Harddisk.jsp">Harddisk</a></li>
				<li><a href="Videocard.jsp">Videocard</a></li>
				<li><a href="Powersupply.jsp">Power Supply</a></li>
    </ul>
  </li>
 
  <li><a href="contact.html">Contact Us</a></li>
</ul>
      </div><!--close menu-->
    </div><!--close menubar-->	
   <p>
	<div id="site_content">	
		<h2>Welcome to the ultimate PC building website</h2>
		<p>The latest project of the G.entlemens allows you to keep a close eye on the fluctuating PC part prizes. Also if you're interested in building your own PC from scratch w'll give you a step by step tutorial just click on the PC builder link in the navigation tab!</p>
	</div><!--close site_content-->
    </p>
		<div id="main_wrapper" width='800px' height='720px'>		
		 <%		
            String product = "Harddisk";
            try {
                Connection con = (Connection) DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
                String query = ("Match (n:`"+product+"`) return n");
            try (PreparedStatement stmt = con.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
                    JSONObject jso = new JSONObject(rs.getString("n").trim());
                    String photo = jso.get("Photo").toString();
					String name = jso.get("Name").toString();
					String price = jso.get("Price").toString();
					String link = jso.get("Link").toString();
					String description = jso.get("Description").toString();					
        %>
            <div class = "product_wrapper"> 	
                <br><img src=<%=photo%> width="50%" height="50%"/></br>
			    <br><%=name%></br>
			    <br>€ <%=price%></br>
			    <div id = "hover_info">
                    <p><b style='Color: Black'><h5><%=name%></h5></b> <img src=<%=photo%> width="25%" height="25%"/></p>
                    <p><a href=<%=link%> target="_blank" class='linkToWebsite'>Go to website</a></p>	
					<b style='Color: Black'>Description:</b>		
                    <p><%=description%></p>														
				</div>
			</div>	
    	<%
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
		</div><!--close main_wrapper-->
        </div>
	</body>
</html>