package com.leetcode.leetcode_clone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository extends MongoRepository<Problem, String> {
    Problem findByTitle(String title);
}