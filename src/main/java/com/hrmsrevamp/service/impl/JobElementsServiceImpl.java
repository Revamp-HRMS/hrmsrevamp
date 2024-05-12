package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.constant.MessageEnum;
import com.hrmsrevamp.constant.RoleEnum;
import com.hrmsrevamp.entity.JobElements;
import com.hrmsrevamp.entity.RatingAndComments;
import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.model.CustomResponse;
import com.hrmsrevamp.model.JobElementsModel;
import com.hrmsrevamp.repository.JobElementsRepository;
import com.hrmsrevamp.repository.RatingAndCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class JobElementsServiceImpl implements com.hrmsrevamp.service.JobElementsService {

    @Autowired
    private RatingAndCommentsRepository ratingAndCommentsRepository;

    @Autowired
    private JobElementsRepository jobElementsRepository;

    @Override
    public CustomResponse getJobElementsById(Long jobElementsModelId) {
        Optional<JobElements> jobElementsEntity = jobElementsRepository.findById(jobElementsModelId);
        if (jobElementsEntity.isPresent()) {
            Optional<RatingAndComments> optionalRatingAndComments = ratingAndCommentsRepository.findById(jobElementsEntity.get().getRatingAndCommentsId());
            JobElementsModel jobElementsModel = new JobElementsModel();
            jobElementsModel.setJobElement(jobElementsEntity.get().getJobElement());
            jobElementsModel.setUserId(jobElementsEntity.get().getUserId());
            jobElementsModel.setSelfAssessment(optionalRatingAndComments.get().getSelfAssessment());
            jobElementsModel.setEmployeeComments(optionalRatingAndComments.get().getEmployeeComments());
            jobElementsModel.setMentor(optionalRatingAndComments.get().getMentor());
            jobElementsModel.setMentorComments(optionalRatingAndComments.get().getMentorComments());
            jobElementsModel.setProjectLeader(optionalRatingAndComments.get().getProjectLeader());
            jobElementsModel.setProjectManagerComments(optionalRatingAndComments.get().getProjectManagerComments());
            jobElementsModel.setAppraisalCycleId(optionalRatingAndComments.get().getAppraisalId());
            return CustomResponse.setAndGetCustomResponse(true, MessageEnum.JOB_ELEMENTS.name(), jobElementsModel);
        } else {
            return CustomResponse.setAndGetCustomResponse(false, MessageEnum.JOB_ELEMENTS_NOT_FOUND.name(), null);
        }

    }

    @Override
    public CustomResponse addJobElements(com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
        User loggedInUser = LoggedInUser.getCurrentUser();
        RatingAndComments ratingAndComments = new RatingAndComments();
        JobElements jobElements = new JobElements();
        if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.EMPLOYEE))) {
            ratingAndComments.setSelfAssessment(jobElementsModel.getSelfAssessment());
            ratingAndComments.setEmployeeComments(jobElementsModel.getEmployeeComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.MANAGER))) {
            ratingAndComments.setProjectLeader(jobElementsModel.getProjectLeader());
            ratingAndComments.setProjectManagerComments(jobElementsModel.getProjectManagerComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.MENTOR))) {
            ratingAndComments.setMentor(jobElementsModel.getMentor());
            ratingAndComments.setMentorComments(jobElementsModel.getMentorComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.ADMIN))) {
            ratingAndComments.setSelfAssessment(jobElementsModel.getSelfAssessment());
            ratingAndComments.setEmployeeComments(jobElementsModel.getEmployeeComments());
            ratingAndComments.setProjectLeader(jobElementsModel.getProjectLeader());
            ratingAndComments.setProjectManagerComments(jobElementsModel.getProjectManagerComments());
            ratingAndComments.setMentor(jobElementsModel.getMentor());
            ratingAndComments.setMentorComments(jobElementsModel.getMentorComments());
        }
        RatingAndComments ratingAndCommentsEntity = ratingAndCommentsRepository.save(ratingAndComments);
        jobElements.setJobElement(jobElementsModel.getJobElement());
        jobElements.setUserId(jobElements.getUserId());
        jobElements.setRatingAndCommentsId(ratingAndCommentsEntity.getId());
        jobElementsRepository.save(jobElements);
        return CustomResponse.setAndGetCustomResponse(true, MessageEnum.JOB_ELEMENTS_ADDED.name(), jobElementsModel);
    }

    @Override
    public CustomResponse updateJobElementsById(Long jobElementsId, com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
        User loggedInUser = LoggedInUser.getCurrentUser();
        JobElements jobElements = jobElementsRepository.findById(jobElementsId).get();
        RatingAndComments ratingAndComments = ratingAndCommentsRepository.findById(jobElements.getRatingAndCommentsId()).get();
        if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.EMPLOYEE))) {
            ratingAndComments.setSelfAssessment(jobElementsModel.getSelfAssessment());
            ratingAndComments.setEmployeeComments(jobElementsModel.getEmployeeComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.MANAGER))) {
            ratingAndComments.setProjectLeader(jobElementsModel.getProjectLeader());
            ratingAndComments.setProjectManagerComments(jobElementsModel.getProjectManagerComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.MENTOR))) {
            ratingAndComments.setMentor(jobElementsModel.getMentor());
            ratingAndComments.setMentorComments(jobElementsModel.getMentorComments());
        } else if (loggedInUser.getRoles().stream().allMatch(role -> role.equals(RoleEnum.ADMIN))) {
            ratingAndComments.setSelfAssessment(jobElementsModel.getSelfAssessment());
            ratingAndComments.setEmployeeComments(jobElementsModel.getEmployeeComments());
            ratingAndComments.setProjectLeader(jobElementsModel.getProjectLeader());
            ratingAndComments.setProjectManagerComments(jobElementsModel.getProjectManagerComments());
            ratingAndComments.setMentor(jobElementsModel.getMentor());
            ratingAndComments.setMentorComments(jobElementsModel.getMentorComments());
        }
        RatingAndComments ratingAndCommentsEntity = ratingAndCommentsRepository.save(ratingAndComments);
        jobElements.setJobElement(jobElementsModel.getJobElement());
        jobElements.setUserId(jobElements.getUserId());
        jobElements.setRatingAndCommentsId(ratingAndCommentsEntity.getId());
        jobElementsRepository.save(jobElements);
        return CustomResponse.setAndGetCustomResponse(true, MessageEnum.JOB_ELEMENTS_UPDATED.name(), jobElementsModel);
    }
}
