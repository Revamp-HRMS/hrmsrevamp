package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.entity.Role;
import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.model.CustomResponse;

import java.util.List;

@org.springframework.stereotype.Service
public class JobElementsServiceImpl implements com.hrmsrevamp.service.JobElementsService {

    @Override
    public CustomResponse getJobElementsById(Long jobElementsModel) {
        return null;
    }

    @Override
    public CustomResponse addJobElements(com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
        User loggedInUser = LoggedInUser.getCurrentUser();
        List<Role> roles = loggedInUser.getRoles();
        //switch (loggedInUser);
        return null;
    }

    @Override
    public CustomResponse updateJobElementsById(Long jobElementsId, com.hrmsrevamp.model.JobElementsModel jobElementsModel) {
        return null;
    }
}
