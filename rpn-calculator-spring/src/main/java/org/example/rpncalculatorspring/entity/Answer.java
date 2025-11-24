package entity;

public class Answer {
    private Long id;
    private String value;
    private Problem problem_id;

    public Answer(String value, Problem problem_id) {
        this.value = value;
        this.problem_id = problem_id;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    public Problem getProblem_id() {
        return problem_id;
    }
}
