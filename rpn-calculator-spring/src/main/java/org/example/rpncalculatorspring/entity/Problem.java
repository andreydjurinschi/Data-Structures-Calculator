package entity;


public class Problem {
    private Long id;
    private String expression;

    public Problem(String expression) {
        this.expression = expression;
    }

    public Long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

}
