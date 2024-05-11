package com.hrmsrevamp.service;

import com.hrmsrevamp.model.CustomResponse;


public interface AppraisalCycleService {

  CustomResponse addAppraisalCycle(com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel);


  CustomResponse updateAppraisalCycle(Long appraisalId, com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel);

  CustomResponse getAppraisalCycleByUserId(Long userId);
}
