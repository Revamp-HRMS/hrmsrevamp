package com.hrmsrevamp.setup;

import com.hrmsrevamp.constant.RoleEnum;
import com.hrmsrevamp.entity.Role;
import com.hrmsrevamp.repository.RoleRepository;
import com.hrmsrevamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class HrmsRevampDataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addRole(RoleEnum.EMPLOYEE.name());
        addRole(RoleEnum.MANAGER.name());
        addRole(RoleEnum.MENTOR.name());
        addRole(RoleEnum.HR.name());
        addRole(RoleEnum.ADMIN.name());
    }


    private Role addRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = roleRepository.save(Role.builder()
                    .name(name).build());
        }
        return role;
    }

}
