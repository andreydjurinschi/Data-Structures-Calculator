package org.logic;

import org.logic.database.DbConnector;
import org.logic.exceptions.IllegalSymbolException;
import org.logic.repo.AnswersRepo;
import org.logic.repo.ProblemsRepo;
import org.logic.utils.Calculator;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

/*        String expression = "50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500";
        try {
            System.out.println(Calculator.calculate(expression));
        } catch (IllegalSymbolException e) {
            System.out.println(e.getMessage());
        }*/

/*        try {
            Calculator.calculate("50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500");
            Calculator.calculate("(12 - 77) / 5 - 100");
            Calculator.calculate("500 + 890 / 55 * (13 - 3)");
        } catch (IllegalSymbolException e) {
            throw new RuntimeException(e);
        }*/

        //ProblemsRepo.history();

        ProblemsRepo.getLastThreeProblems();
    }
}