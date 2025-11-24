package repository;


import database.DbConnector;
import entity.Problem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProblemsRepo {
    public void createProblem(String expression){
        try(Connection conn = DbConnector.openConnection()){
            PreparedStatement statement = conn.prepareStatement("insert into problems(expression) values (?)");
            statement.setString(1, expression);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void history(){
        try(Connection conn = DbConnector.openConnection()){
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery("select p.expression from problems p ");
            while(set.next()){
                //System.out.println(set.getString("expression"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String,String> getLastThreeProblems(){
        Map<String, String> result = new HashMap<>();

        try(Connection conn = DbConnector.openConnection();
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery("select p.expression, a.value as answer from problems p" +
                    " join answers a on a.problem_id = p.id" +
                    " order by p.id desc limit 3 ");
        ){
            while(set.next()){
                result.put(set.getString("expression") + " = ", set.getString("answer"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getProblemId(String expression){
        try(Connection connection = DbConnector.openConnection();
            PreparedStatement statement = connection.prepareStatement("select p.id from problems p " +
                    "where p.expression = ?");
        ){
            statement.setString(1, expression);

            try (ResultSet set = statement.executeQuery()){
                if(set.next()){
                    return set.getLong("id");
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
