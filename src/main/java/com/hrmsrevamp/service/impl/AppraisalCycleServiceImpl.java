package com.hrmsrevamp.service.impl;


@org.springframework.stereotype.Service
public class AppraisalCycleServiceImpl implements com.hrmsrevamp.service.AppraisalCycleService {


  @org.springframework.beans.factory.annotation.Autowired
  private com.hrmsrevamp.repository.AppraisalCycleRepository appraisalCycleRepository;


  @Override
  public com.hrmsrevamp.model.CustomResponse addAppraisalCycle(com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel) {
    com.hrmsrevamp.entity.AppraisalCycle cycleModel = new com.hrmsrevamp.entity.AppraisalCycle();
    cycleModel.setUserId(appraisalCycleModel.getUserId());
    cycleModel.setName(appraisalCycleModel.getName());
    cycleModel.setEmployeeCode(appraisalCycleModel.getEmployeeCode());
    cycleModel.setDesignation(appraisalCycleModel.getDesignation());
    cycleModel.setReportingManager(appraisalCycleModel.getReportingManager());
    cycleModel.setReviewingManager(appraisalCycleModel.getReviewingManager());
    cycleModel.setAppraisalCycle(appraisalCycleModel.getAppraisalCycle());
    cycleModel.setStatus(appraisalCycleModel.getStatus());

    com.hrmsrevamp.entity.AppraisalCycle savedAppraisalCycle = appraisalCycleRepository.save(cycleModel);
    return com.hrmsrevamp.model.CustomResponse.setAndGetCustomResponse(true, com.hrmsrevamp.constant.MessageEnum.NEW_DATA_STORED.name(), savedAppraisalCycle);
  }

  @Override
  public com.hrmsrevamp.model.CustomResponse updateAppraisalCycle(Long appraisalId, com.hrmsrevamp.model.AppraisalCycleModel appraisalCycleModel) {

    java.util.Optional<com.hrmsrevamp.entity.AppraisalCycle> optional = appraisalCycleRepository.findById(appraisalId);
    com.hrmsrevamp.entity.AppraisalCycle existingAppraisalCycle = optional.get();
    existingAppraisalCycle.setUserId(appraisalCycleModel.getUserId());
    existingAppraisalCycle.setName(appraisalCycleModel.getName());
    existingAppraisalCycle.setEmployeeCode(appraisalCycleModel.getEmployeeCode());
    existingAppraisalCycle.setDesignation(appraisalCycleModel.getDesignation());
    existingAppraisalCycle.setReportingManager(appraisalCycleModel.getReportingManager());
    existingAppraisalCycle.setReviewingManager(appraisalCycleModel.getReviewingManager());
    existingAppraisalCycle.setAppraisalCycle(appraisalCycleModel.getAppraisalCycle());
    existingAppraisalCycle.setStatus(appraisalCycleModel.getStatus());

    com.hrmsrevamp.entity.AppraisalCycle savedAppraisalCycle = appraisalCycleRepository.save(existingAppraisalCycle);
    return com.hrmsrevamp.model.CustomResponse.setAndGetCustomResponse(true, com.hrmsrevamp.constant.MessageEnum.UPDATED.name(), savedAppraisalCycle);
  }

  @Override
  public com.hrmsrevamp.model.CustomResponse getAppraisalCycleByUserId(Long userId) {
    java.util.List<com.hrmsrevamp.entity.AppraisalCycle> appraisalCycleModelList = appraisalCycleRepository.findByUserId(userId);
    if (!appraisalCycleModelList.isEmpty())
      return com.hrmsrevamp.model.CustomResponse.setAndGetCustomResponse(true, com.hrmsrevamp.constant.MessageEnum.APPRAISAL_CYCLE_LIST.name(), appraisalCycleModelList);
    return com.hrmsrevamp.model.CustomResponse.setAndGetCustomResponse(false, com.hrmsrevamp.constant.MessageEnum.NOT_FOUND.name(), null);
  }

}
