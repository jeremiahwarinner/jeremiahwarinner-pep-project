package DAO;
import Model.Account;
import java.util.List;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "insert into account (username, password) values (?,?)" ;

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Account loginAccount(Account account){
            Connection connection = ConnectionUtil.getConnection();
            try {
                //Write SQL logic here"
                String sql = "select * from account where username = ? and password = ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                //write preparedStatement's setString and setInt methods here.
                preparedStatement.setString(1,account.getUsername());
                preparedStatement.setString(2,account.getPassword());
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Account account_return = new Account(rs.getInt("account_id"), rs.getString("username"),
                            rs.getString("password"));
                    return account_return;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        
        }
        public Account checkAccount(Account account){
            Connection connection = ConnectionUtil.getConnection();
            try {
                //Write SQL logic here"
                String sql = "select * from account where username = ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                //write preparedStatement's setString and setInt methods here.
                preparedStatement.setString(1,account.getUsername());
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Account account_return = new Account(rs.getInt("account_id"), rs.getString("username"),
                            rs.getString("password"));
                    return account_return;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        
        }
        
        public Account checkAccountById(int id){
            Connection connection = ConnectionUtil.getConnection();
            try {
                //Write SQL logic here"
                String sql = "select * from account where account_id = ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                //write preparedStatement's setString and setInt methods here.
                preparedStatement.setInt(1,id);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Account account_return = new Account(rs.getInt("account_id"), rs.getString("username"),
                            rs.getString("password"));
                    return account_return;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        
        }
}
