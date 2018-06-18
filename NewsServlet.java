package com.aula114;

//import javax.inject.Inject;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.aula114.New;
import com.aula114.DAONews;
import com.aula114.DAOImplNews;
import java.sql.*;

public class NewsServlet extends HttpServlet {

  //private String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  //private String DB_URL = "jdbc:mysql://localhost/"; // ??? No sé exactamente qué url hay que poner aquí

   //  Database credentials
   //private String USER = "localhost";
   //private String PASS = "";

    // JDK 6 and above only
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the MIME type for the response message
        response.setContentType("text/html");
        // Get a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 1: Allocate a database Connection object
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8888/News", "localhost", ""); // <== Check!
            // database-URL(hostname, port, default database), username, password

            // Step 2: Allocate a Statement object within the Connection
            stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            String sqlStr = "select title from news"; /*where author = "
                    + "'" + request.getParameter("author") + "'"
                    + " and qty > 0 order by price desc";*/

            // Print an HTML page as the output of the query
            out.println("<html><head><title>Títulos de noticias</title><meta charset='UTF-8'></head><body>");
            //out.println("<p>" + sqlStr + "<br></p>"); Echo for debugging
            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

            // Step 4: Process the query result set
            int count = 0;
            while (rset.next()) {
                // Print a paragraph <p>...</p> for each record
                out.println("<p>" + rset.getString("title") + "</p>");
                count++;
            }
            out.println("<p>==== " + count + " news found =====</p>");
            out.println("</body></html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}