package com.hrmsrevamp.controller;

import com.hrmsrevamp.model.CustomResponse;
import com.hrmsrevamp.model.ResetPasswordRequest;
import com.hrmsrevamp.model.SignInRequest;
import com.hrmsrevamp.model.SignUpRequest;
import com.hrmsrevamp.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Auth controller for auth apis.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/signup")
    public ResponseEntity<CustomResponse> userSignUp(@RequestBody SignUpRequest request) {
        long startTime = System.currentTimeMillis();
        CustomResponse customResponse = authService.userSignUp(request);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("Time taken to signUp admin: {} Millis", (endTime - startTime));
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<CustomResponse> signIn(@RequestBody SignInRequest request) {
        long startTime = System.currentTimeMillis();
        CustomResponse customResponse = authService.signIn(request);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("Time taken to signIn admin: {} Millis", (endTime - startTime));
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @PostMapping("/password")
    public ResponseEntity<CustomResponse> resetAdminPassword(@Valid @RequestBody ResetPasswordRequest resetPwdRequest) {
        long startTime = System.currentTimeMillis();
        CustomResponse customResponse = authService.resetAdminPassword(resetPwdRequest);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("Time taken to reset password: {} Millis", (endTime - startTime));
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }


    @GetMapping("/exists")
    public ResponseEntity<CustomResponse> checkUserExistsOrNot(@RequestParam(value = "email") String email) {
        long startTime = System.currentTimeMillis();
        CustomResponse customResponse = authService.checkUserExistsOrNot(email);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("Time taken to check user exists or not by email: {} Millis", (endTime - startTime));
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<CustomResponse> refreshToken(@RequestBody SignInRequest request) {
        long startTime = System.currentTimeMillis();
        CustomResponse customResponse = authService.refreshTokenForUser(request);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("Time taken to refresh token: {} Millis", (endTime - startTime));
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}
