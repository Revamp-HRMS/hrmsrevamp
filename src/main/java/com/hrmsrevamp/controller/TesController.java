package com.hrmsrevamp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TesController {

    @GetMapping("/add")
    public ResponseEntity<String> getAppraisal() {
            return new ResponseEntity<>("Testing check", HttpStatus.OK);

    }
}
