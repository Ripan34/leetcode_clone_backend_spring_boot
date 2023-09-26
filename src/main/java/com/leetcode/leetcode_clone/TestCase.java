package com.leetcode.leetcode_clone;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("TestCases")
public class TestCase {
    private String id;
    private String problem_id;
}
