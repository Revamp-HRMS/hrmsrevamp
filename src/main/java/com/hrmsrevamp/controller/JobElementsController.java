package com.hrmsrevamp.controller;

import com.hrmsrevamp.model.CustomResponse;
import com.hrmsrevamp.service.JobElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hrmsrevamp.model.JobElementsModel;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/job-elements")
public class JobElementsController {

    @Autowired
    private JobElementsService jobElementsService;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse> addJobElements(@RequestBody JobElementsModel jobElementsModel) {
        CustomResponse customResponse = jobElementsService.addJobElements(jobElementsModel);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updateJobElements(@PathVariable("id") Long jobElementsId,
                                                                                     @RequestBody JobElementsModel jobElementsModel) {
        CustomResponse customResponse = jobElementsService.updateJobElementsById(jobElementsId, jobElementsModel);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getJobElements(@PathVariable("id") Long jobElementsId) {
        CustomResponse customResponse = jobElementsService.getJobElementsById(jobElementsId);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}
