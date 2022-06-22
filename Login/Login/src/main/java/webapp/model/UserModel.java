package webapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import webapp.beans.UserBeans;
import webapp.utility.JDBCDataSource;
import jdk.nashorn.internal.ir.WhileNode;
public class UserModel {
  
  public static long nextPk() {
    long pk = 0;
    Connection conn;
    try {
      conn = JDBCDataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement("select Max(id) from user");
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        pk = rs.getLong(1);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return pk+1;
    
  }
  
  public static long addUser(UserBeans user) {
    int i = 0;
    try {
      Connection conn = JDBCDataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
      stmt.setLong(1, nextPk());
      stmt.setString(2 , user.getFirstName() );
      stmt.setString(3 , user.getLastName() );
      stmt.setString(4 , user.getLogin() );
      stmt.setString(5 , user.getPassword() );
      stmt.setDate(6 , new java.sql.Date(user.getDob().getTime()) );
      stmt.setString(7 , user.getMobileNo() );
        i =     stmt.executeUpdate();
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    return i;
  }
  
  //Login User......
  public static UserBeans UserLogin(String login,String password) {
    Connection con;
    UserBeans user = null;
    try {
      con = (Connection) JDBCDataSource.getConnection();
      PreparedStatement stmt = (PreparedStatement) con.prepareStatement("Select * from user where login=? and password = ?");
      stmt.setString(1,login);
      stmt.setString(2,password);
      ResultSet rs = (ResultSet) stmt.executeQuery();
      if(rs.next()) {
        user = new UserBeans();
        System.out.println("ID: "+rs.getLong("id"));
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("fname"));
        user.setLastName(rs.getString("lname"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setDob(rs.getDate("dob"));
        user.setMobileNo(rs.getString("mobile"));    
      }
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return user;
  }
}