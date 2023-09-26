package com.leetcode.leetcode_clone;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api")
public class ProblemController {
    private ProblemService problemService;
    private CodeSubmissionService codeSubmissionService;
    @Autowired
    public ProblemController(ProblemService problemService, CodeSubmissionService codeSubmissionService){
        this.problemService = problemService;
        this.codeSubmissionService = codeSubmissionService;
    }
    @GetMapping("/problems")
    public List<Problem> hello(){
        return problemService.getAllProblems();
    }

    @GetMapping("/title")
    public Problem getProblemByTitle(@RequestParam String title){
        return problemService.getProblemByTitle(title);
    }

    @PostMapping("/submit")
    public String submitCode(@RequestBody Map<String, String> requestBody) throws JsonProcessingException {
        String problemId = requestBody.get("problemId");
        String code = requestBody.get("code");
        final String[] testCode = {""};
        Optional<Problem> optProblem = problemService.getProblemById(problemId);
        optProblem.ifPresentOrElse(
                (value) -> {
                   testCode[0] = value.getTestCode();
                },
                () -> {
                    System.out.println(
                        "Value is empty");
                });

        return codeSubmissionService.submitJavaScriptCode(code + testCode[0]).getBody();
    }
}
