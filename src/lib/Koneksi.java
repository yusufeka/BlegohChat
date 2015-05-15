/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Author Blegoh aka Yusuf Eka S
 */

package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Koneksi {
    private Connection conn;
    private Statement statement;
    private ResultSet result;
    private String query;
    private String select;
    private String from;
    private String where;
    private String having;
    private String join;
    private String url;
    private String user;
    private String password;
    
    public Koneksi(){
        try{
            url = "jdbc:mysql://localhost:3306/blegohChat";
            user = "root";
            password = "hornline";
            conn = DriverManager.getConnection(url,user,password);
            statement = conn.createStatement();
            this.select = "*";
            this.join = "";
        } catch(SQLException sqle){
            System.out.println("Proses koneksi gagal "+sqle);
        }
    }
    
    public void updateQuery(){
        this.query = "select "+select+" from "+from;
        if (!join.equalsIgnoreCase("")) {
            this.query += " "+join;
        }
        if (where != null) {
            this.query += " where "+where;
        }
        if (having != null) {
            this.query += " having "+where;
        }
    }
    
    public void select(String select){
        this.select = select;
        this.updateQuery();
    }
    public void from(String from){
        this.from = from;
        this.updateQuery();
    }
    public void where(String where){
        this.where = where;
        this.updateQuery();
    }
    public void having(String having){
        this.having = having;
        this.updateQuery();
    }
    public void join(String type, String join){
        this.join  += " "+type+" join "+join;
        this.updateQuery();
    }
    
    public void clearJoin(){
        this.join = "";
    }
    
    
    public void setQuery(String query){
        this.query = query;
    }
    
    public void executeQuery(){
        try{
            result = statement.executeQuery(this.query);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void executeUpdate() throws SQLException{
        statement.executeUpdate(this.query);
    }
    
    public ResultSet getResult(){
        return result;
    }
    
    public int getRow() throws SQLException{
        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        return rows;
    }
    
    public void close() throws SQLException{
        conn.close();
    }
}
