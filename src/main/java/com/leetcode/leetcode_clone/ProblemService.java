package com.leetcode.leetcode_clone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    private ProblemRepository problemRepository;
    @Autowired
    public ProblemService(ProblemRepository problemRepository){
        this.problemRepository = problemRepository;
    }

    public List<Problem> getAllProblems(){
        return problemRepository.findAll();
    }

    public Problem getProblemByTitle(String title){
        return problemRepository.findByTitle(title);
    }
    public Optional<Problem> getProblemById(String problemId){
        return problemRepository.findById(problemId);
    }
}
