package org.logic.repo;

import org.logic.database.DbConnector;

import java.sql.*;

public class AnswersRepo {
    public static void createAnswer(String value, Long expressionId){
        try(Connection conn = DbConnector.openConnection();
            PreparedStatement statement = conn.prepareStatement("insert into answers(value, problem_id) values (?,?)");
        ){
            statement.setString(1, value);
            statement.setLong(2, expressionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getWithExpressions(){
        try(Connection connection = DbConnector.openConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select p.expression, a.value as answer from problems p " +
                    "join answers a on a.problem_id = p.id");
        ){
            while(set.next()){
                System.out.println("Expression: " + set.getString("expression") + " = " + set.getString("answer"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
