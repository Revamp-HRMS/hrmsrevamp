package com.hrmsrevamp.service;

import com.hrmsrevamp.model.CustomResponse;

public interface JobElementsService {
    CustomResponse addJobElements(com.hrmsrevamp.model.JobElementsModel jobElementsModel);

    CustomResponse getJobElementsById(Long jobElementsModel);

    CustomResponse updateJobElementsById(Long jobElementsId, com.hrmsrevamp.model.JobElementsModel jobElementsModel);
}
