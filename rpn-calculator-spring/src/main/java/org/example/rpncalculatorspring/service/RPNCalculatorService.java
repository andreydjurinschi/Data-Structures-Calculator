package service;

import org.springframework.stereotype.Service;
import repository.AnswersRepo;
import repository.ProblemsRepo;
import response.ProblemAnswerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RPNCalculatorService {

    private final ProblemsRepo problemRepository;
    private final AnswersRepo answersRepository;

    public RPNCalculatorService(ProblemsRepo repository, AnswersRepo answersRepository) {
        this.problemRepository = repository;
        this.answersRepository = answersRepository;
    }

    public void createProblem(String expression){
        problemRepository.createProblem(expression);
    }

    public List<ProblemAnswerResponse> getLastThreeProblems(){
        List<ProblemAnswerResponse> result = new ArrayList<>();
        Map<String,String> repoResult = problemRepository.getLastThreeProblems();

        for(var r : repoResult.entrySet()){
            result.add(new ProblemAnswerResponse(r.getKey(), r.getValue()));
        }

        return result;
    }


}
