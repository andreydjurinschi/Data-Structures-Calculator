package response;

public class ProblemAnswerResponse {
    private final String expression;
    private final String value;

    public ProblemAnswerResponse(String expression, String value) {
        this.expression = expression;
        this.value = value;
    }

    public String getExpression() {
        return expression;
    }

    public String getValue() {
        return value;
    }
}
