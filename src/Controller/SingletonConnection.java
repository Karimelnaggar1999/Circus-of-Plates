/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import java.sql.*;

/**
 *
 * @author es-karimabdelhamid19
 */
public class SingletonConnection {

    private static java.sql.Connection connection;
     

    static {
       String url = "jdbc:mysql://localhost:3306/final";
     String username = "root";
     String password = "";
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");
           // connection =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
           Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection
             connection = DriverManager.getConnection(url, username, password);
            System.out.println("connected");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    
    public static Connection getCon(){
    return (Connection) connection;
    }

}

