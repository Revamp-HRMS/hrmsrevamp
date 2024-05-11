package com.hrmsrevamp.controller;

import com.hrmsrevamp.model.CustomResponse;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/job-elements")
public class JobElementsController {

  @org.springframework.beans.factory.annotation.Autowired
  private com.hrmsrevamp.service.JobElementsService jobElementsService;

  @org.springframework.web.bind.annotation.PostMapping("/add")
  public org.springframework.http.ResponseEntity<CustomResponse> addJobElements(@org.springframework.web.bind.annotation.RequestBody com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
    CustomResponse customResponse = jobElementsService.addJobElements(jobElementsModel);
    return new org.springframework.http.ResponseEntity<>(customResponse, org.springframework.http.HttpStatus.OK);
  }

  @org.springframework.web.bind.annotation.PutMapping("/update/{id}")
  public org.springframework.http.ResponseEntity<CustomResponse> updateJobElements(@org.springframework.web.bind.annotation.PathVariable("id") Long jobElementsId,
                                                          @org.springframework.web.bind.annotation.RequestBody com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
    CustomResponse customResponse = jobElementsService.updateJobElementsById(jobElementsId, jobElementsModel);
    return new org.springframework.http.ResponseEntity<>(customResponse, org.springframework.http.HttpStatus.OK);
  }

  @org.springframework.web.bind.annotation.GetMapping("/{id}")
  public org.springframework.http.ResponseEntity<CustomResponse> getJobElements(@org.springframework.web.bind.annotation.PathVariable("id") Long jobElementsId) {
    CustomResponse customResponse = jobElementsService.getJobElementsById(jobElementsId);
    return new org.springframework.http.ResponseEntity<>(customResponse, org.springframework.http.HttpStatus.OK);
  }
  
}
