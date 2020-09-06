package javaapplicationatm;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import 

/**
 *
 * @author haris
 */
public class SqlConnection  {
      Connection con=null;
      PreparedStatement pstmt;
      private double data;

      SqlConnection(){
    
          try {
              //connection with driver
              Class.forName("com.mysql.jdbc.Driver");
              //connection with database
//              http://localhost/phpmyadmin/db_structure.php?server=1&db=db_acc
              con=DriverManager.getConnection("jdbc:mysql://localhost/db_acc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
//             con=DriverManager.getConnection("jdbc:mysql://985cca14c42e.ngrok.io/phpmyadmin/db_acc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
              
            }catch (ClassNotFoundException ex) {
              System.out.println("class not found exception"+ex.getMessage());
            }
            catch (SQLException ex) {
              System.out.println("Sql exception "+ex);
            }
            
 }
      boolean checkAcc(long acc_no)
      {
          try {
              String query="select acc_no from acc_holder";
              pstmt = con.prepareStatement(query);
              
              ResultSet rs = pstmt.executeQuery();
              while(rs.next())
              {
                  if(acc_no==rs.getInt(1))
                      return true;
                  
              }
              con.close();
          } catch (SQLException ex) {
              Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          return false;
      }
      
      boolean checkPin(long acc_no ,long pin){
          
          try {
              String query="select pin from acc_holder where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setLong(1, acc_no);
              ResultSet rs = pstmt.executeQuery();
              rs.first();
//              System.out.println(rs.getInt(1));
              if(pin==rs.getInt(1))
              {
//                  System.out.println("run");
                  return true;
              }
              con.close();
              return false;
            }
          
            catch (SQLException ex) {
              Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
              return false;
            }
       }
      String getName(long acc_no){
         try {
              String query="select name from acc_holder where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setLong(1, acc_no);                 //change 123451 to customized acc no
              ResultSet rs = pstmt.executeQuery();
              rs.first();
              String name = rs.getString(1);
              con.close();
              return name;
          } catch (SQLException ex) { }
          return "";  
      }
      boolean deposite(long acc_no,double amount){
          double bal;
          try {
              String query="select acc_bal from acc_balance where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setLong(1, acc_no);
              ResultSet rs = pstmt.executeQuery();
              rs.first();
              bal=rs.getInt(1);
              bal+=amount;
              
              query="update acc_balance set acc_bal=? where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setDouble(1, bal);
              pstmt.setLong(2,acc_no);
               if(pstmt.executeUpdate()==0)
                   return false;
               con.close();
              return true;
          } catch (SQLException ex) {
              Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
      }
       boolean widhraw(long acc_no,double amount){
          double bal;
          try {
              String query="select acc_bal from acc_balance where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setLong(1, acc_no);
              ResultSet rs = pstmt.executeQuery();
              rs.first();
              if(rs.getDouble(1)==0)
                  System.out.println("Empty Set Found");
              bal=rs.getDouble(1);
              if(amount>bal)
                  return false;
              else{
                  bal-=amount;
              query="update acc_balance set acc_bal=? where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setDouble(1, bal);
              pstmt.setLong(2,acc_no);
              if(pstmt.executeUpdate()==0)
                  return false;
              con.close();
              return true;
              }
          } catch (SQLException ex) {
              Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
      }
      double remBalance(long acc_no){
          try {
              String sql="select acc_bal from acc_balance where acc_no=?";
              pstmt = con.prepareStatement(sql);
              pstmt.setLong(1, acc_no);                 //change 123451 to customized acc no
              ResultSet rs = pstmt.executeQuery();
              rs.first();
              data = rs.getDouble(1);
              con.close();
          } catch (SQLException ex) {
              return -1;
          }
          return data;
      }
      
      boolean changePin(long acc_no,long  newpin){
          try {
              String query="update acc_holder set pin=? where acc_no=?";
              pstmt = con.prepareStatement(query);
              pstmt.setLong(1, newpin);
              pstmt.setLong(2,acc_no);
              if(pstmt.executeUpdate()==0)
                return false;
              con.close();
              return true;
             }
              catch (SQLException ex) {
              Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
              return false;
              }
      }
    public static void main(String[] args) {
       SqlConnection s = new SqlConnection();
               System.out.println(s.remBalance(123456));
//               System.out.println(s.checkAcc(123453));
               //System.out.println(s.checkPass(123454 ,"Sanjay4"));
    }
}