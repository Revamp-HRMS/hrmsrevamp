package com.hrmsrevamp.controller;


import com.hrmsrevamp.model.CustomResponse;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/appraisal")
public class AppraisalCycleController {

    @org.springframework.beans.factory.annotation.Autowired
    private com.hrmsrevamp.service.AppraisalCycleService appraisalCycleService;

    @org.springframework.web.bind.annotation.PostMapping
    public org.springframework.http.ResponseEntity<CustomResponse> addAppraisalCycle(@org.springframework.web.bind.annotation.RequestBody com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel) {
        try {
            CustomResponse customResponse = appraisalCycleService.addAppraisalCycle(appraisalCycleModel);
            return org.springframework.http.ResponseEntity.ok(customResponse);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomResponse.setAndGetCustomResponse(false, com.hrmsrevamp.constant.MessageEnum.DATABASE_ERROR.name(), null));
        }
    }

    @org.springframework.web.bind.annotation.PutMapping("/{appraisalId}")
    public org.springframework.http.ResponseEntity<CustomResponse> updateAppraisalCycle(@org.springframework.web.bind.annotation.PathVariable Long appraisalId, @org.springframework.web.bind.annotation.RequestBody com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel) {
        try {
            CustomResponse customResponse = appraisalCycleService.updateAppraisalCycle(appraisalId, appraisalCycleModel);
            return org.springframework.http.ResponseEntity.ok(customResponse);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomResponse.setAndGetCustomResponse(false, com.hrmsrevamp.constant.MessageEnum.DATABASE_ERROR.name(), null));
        }
    }

    @org.springframework.web.bind.annotation.GetMapping("/{userId}")
    public org.springframework.http.ResponseEntity<CustomResponse> getAllAppriasalDetials(@org.springframework.web.bind.annotation.PathVariable Long userId) {
        try {
            CustomResponse customResponse = appraisalCycleService.getAppraisalCycleByUserId(userId);
            return org.springframework.http.ResponseEntity.ok(customResponse);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomResponse.setAndGetCustomResponse(false, com.hrmsrevamp.constant.MessageEnum.DATABASE_ERROR.name(), null));
        }
    }

}

