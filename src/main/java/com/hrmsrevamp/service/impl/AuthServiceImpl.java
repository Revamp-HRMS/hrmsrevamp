package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.constant.MessageEnum;
import com.hrmsrevamp.constant.UserStatusEnum;
import com.hrmsrevamp.entity.Role;
import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.exception.EntityNotFoundException;
import com.hrmsrevamp.exception.HttpException;
import com.hrmsrevamp.exception.InvalidRequestException;
import com.hrmsrevamp.exception.UnauthorizedException;
import com.hrmsrevamp.model.*;
import com.hrmsrevamp.repository.RoleRepository;
import com.hrmsrevamp.repository.UserRepository;
import com.hrmsrevamp.security.UserPrincipal;
import com.hrmsrevamp.service.AuthService;
import com.hrmsrevamp.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Auth ServiceImpl.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    @Transactional
    public CustomResponse userSignUp(SignUpRequest request) {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        Optional<User> userEntity = userRepository.findByEmailAndStatusNot(request.getEmail(), UserStatusEnum.DELETED.name());
        if (userEntity.isPresent()) {
            LOGGER.error("User: {} already exist for email: {}", userEntity.get().getFullName(), userEntity.get().getEmail());
            return CustomResponse.setAndGetCustomResponse(false, MessageEnum.USER_ALREADY_EXISTS.name(), null);
        }
        Role role = roleRepository.findByName(request.getRole());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        User user = User.builder().fullName(request.getFullName())
                .email(request.getEmail())
                .status(UserStatusEnum.ACTIVE.name())
                .roles(roles)
                .build();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String jwt = jwtService.generateTokenForAdmin(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return CustomResponse.setAndGetCustomResponse(true,
                MessageEnum.USER_SIGNED_UP_SUCCESSFULLY.name(), jwtAuthenticationResponse);
    }

    @Override
    public CustomResponse signIn(SignInRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        if ((Objects.nonNull(email) && !email.isBlank())
                && (Objects.nonNull(password) && !password.isBlank())) {
            LOGGER.info("User signing by email/password.");
            return userSignIn(email, password);
        } else {
            LOGGER.error("Invalid signIn request.");
            return CustomResponse.setAndGetCustomResponse(false, MessageEnum.INVALID_SIGN_IN_REQUEST.name(), null);
        }
    }

    @Override
    public CustomResponse resetAdminPassword(ResetPasswordRequest resetPwdRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            LOGGER.error("Authentication in SecurityContextHolder is NULL");
            throw new HttpException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getUser().getId();
        if (!resetPwdRequest.getNewPassword().equals(resetPwdRequest.getConfirmPassword())) {
            LOGGER.error("Password reset failed, newPassword and confirmPassword do not match");
            throw new InvalidRequestException(MessageEnum.NEW_PASSWORD_CONFIRM_PASSWORD_MISMATCH.name());
        }
        // Update user's password
        User user = resetPassword(userId, resetPwdRequest.getNewPassword());
        // Authenticate the user using Email and new Password
        authentication = authenticate(user.getEmail(), resetPwdRequest.getNewPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Re-generate the jwt token for the new password
        UserPrincipal userPrincipal1 = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtService.generateTokenForUser(userPrincipal1.getUser());
        String refreshToken = jwtService.generateRefreshToken(user);
        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return CustomResponse.setAndGetCustomResponse(true, MessageEnum.PASSWORD_RESET_SUCCESSFULLY.name(), jwtAuthenticationResponse
        );
    }

    @Override
    public CustomResponse checkUserExistsOrNot(String email) {
        LOGGER.info("Checking User exists or not in db by email: {}", email);
        Optional<User> optionalUser = userRepository.findByEmailAndStatusIn(email,
                Arrays.asList(UserStatusEnum.ACTIVE.name(), UserStatusEnum.INCOMPLETE.name()));
        String message;
        Map<String, String> signInType = new HashMap<>();
        boolean flag = false;
        if (optionalUser.isPresent()) {
            flag = true;
            message = MessageEnum.USER_FOUND.name();
        } else {
            message = MessageEnum.USER_NOT_FOUND.name();
        }
        return CustomResponse.setAndGetCustomResponse(flag, message, signInType);
    }

    @Override
    public CustomResponse refreshTokenForUser(SignInRequest request) {
        if (jwtService.isRefreshTokenValid(request.getRefreshToken())) {
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            String userId = jwtService.extractUserId(request.getRefreshToken());
            Optional<User> user = userRepository.findById(Long.valueOf(userId));
            if (user.isPresent()) {
                String jwt = jwtService.generateTokenForUser(user.get());
                String refreshToken = jwtService.generateRefreshToken(user.get());
                jwtAuthenticationResponse.setAccessToken(jwt);
                jwtAuthenticationResponse.setRefreshToken(refreshToken);
                return CustomResponse.setAndGetCustomResponse(true,
                        MessageEnum.USER_SIGNED_UP_SUCCESSFULLY.name(), jwtAuthenticationResponse);
            } else {
                LOGGER.info("refresh token: {} not valid", request.getRefreshToken());
                return CustomResponse.setAndGetCustomResponse(false,
                        MessageEnum.USER_NOT_FOUND.name(), null);
            }
        } else {
            LOGGER.info("invalid refresh token: {}", request.getRefreshToken());
            return CustomResponse.setAndGetCustomResponse(false,
                    MessageEnum.INVALID_REFRESH_TOKEN.name(), null);
        }
    }


    private CustomResponse userSignIn(String email, String password) {
        LOGGER.info("SignIn admin with email: {}", email);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        Authentication authenticate = authenticate(email, password);
        Optional<User> optionalUser = userRepository.findByEmailAndStatusNot(email, UserStatusEnum.DELETED.name());
        CustomResponse customResponse;
        if (optionalUser.isPresent()) {
            verifyUserStatus(optionalUser.get());
            authenticate.getDetails();
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String jwt = jwtService.generateTokenForAdmin(optionalUser.get());
            String refreshToken = jwtService.generateRefreshToken(optionalUser.get());
            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            customResponse = CustomResponse.setAndGetCustomResponse(true,
                    MessageEnum.USER_SIGNED_IN_SUCCESSFULLY.name(), jwtAuthenticationResponse);
        } else {
            LOGGER.error("Invalid email or password");
            customResponse = CustomResponse.setAndGetCustomResponse(false, MessageEnum.INVALID_EMAIL_OR_PASSWORD.name(), null);
        }
        return customResponse;
    }

    private User resetPassword(Long userId, String newPassword) {
        Optional<User> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            User user = userEntity.get();
            LOGGER.info("Resetting password for user: {}", userId);
            user.setPassword(passwordEncoder.encode(newPassword));
            user = userRepository.save(user);
            LOGGER.info("Reset password done for user: {}", userId);
            return user;
        } else {
            LOGGER.error("user not found with id : {}", userId);
            throw new EntityNotFoundException(MessageEnum.USER_NOT_FOUND.name());
        }
    }


    public Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            LOGGER.error("User disabled. Error: {}", e.getMessage());
            throw new InvalidRequestException(MessageEnum.USER_DISABLED.name());
        } catch (BadCredentialsException e) {
            LOGGER.error("Invalid credential. Error: {}", e.getMessage());
            throw new InvalidRequestException(MessageEnum.INVALID_CREDENTIAL.name());
        } catch (Exception e) {
            LOGGER.error("User not found Email: {}. Error: {}", username, e.getMessage());
            throw new EntityNotFoundException(MessageEnum.USER_NOT_FOUND.name());
        }
    }

    private void verifyUserStatus(User user) {
        if (user.getStatus().equals(UserStatusEnum.INACTIVE.name())) {
            LOGGER.error("LoggedInUser: {} status is INACTIVE.", user.getId());
            throw new UnauthorizedException(MessageEnum.LOGGED_IN_USER_IS_INACTIVE.name());
        }
    }
}
