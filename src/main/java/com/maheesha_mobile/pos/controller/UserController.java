package com.maheesha_mobile.pos.controller;

import com.maheesha_mobile.pos.constant.APIConstants;
import com.maheesha_mobile.pos.dto.LoginDto;
import com.maheesha_mobile.pos.dto.UserDto;
import com.maheesha_mobile.pos.model.UserEntity;
import com.maheesha_mobile.pos.response.login.LoginResponse;
import com.maheesha_mobile.pos.response.user.CreateUserResponse;
import com.maheesha_mobile.pos.services.JwtService;
import com.maheesha_mobile.pos.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(APIConstants.API_ROOT)
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @RequestMapping(value = APIConstants.CREATE_USER, method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        logger.info("Request Started In createUser |UserDto={} ", userDto);
        CreateUserResponse response = userService.createUser(userDto);
        logger.info("Request Completed In createUser |response={} ", response);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConstants.REFRESH_ACCESS_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<?> refreshAccessToken(@RequestParam String refreshToken) {
        logger.info("Request Started In refreshAccessToken |refreshToken={} ", refreshToken);

        if (jwtService.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
        }
        String userName = jwtService.extractUserName(refreshToken);
        String newAccessToken = jwtService.generateAccessToken(userName);

        logger.info("Request Complete In refreshAccessToken |AccessToken={} ", newAccessToken);
        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken
        ));
    }
    @RequestMapping(value = APIConstants.USER_LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> validateUser(@RequestBody LoginDto loginDto) {
        logger.info("Request Started In validateUser |LoginDto={} ", loginDto);
        LoginResponse response = userService.validateUser(loginDto);
        logger.info("Request Completed In validateUser |response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = APIConstants.FORGOT_PASSWORD, method = RequestMethod.POST)
    private ResponseEntity<?> forgotPassword(@RequestParam String userEmail,@RequestParam String password){
        logger.info("Request Started In forgotPassword |userEmail={} ", userEmail);
        String response = userService.forgotPassword(userEmail, password);
        logger.info("Request Completed In forgotPassword Response={} ", response);
        return ResponseEntity.ok(Map.of(
                "Status", "Successfully",
                "Description", response
        ));
    }
    @RequestMapping(value = APIConstants.USER_SEARCH_ALL, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser() {
        logger.info("Request Started In getUserLogin");
        List<UserEntity> response = userService.getAllUsers();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @RequestMapping(value = APIConstants.DELETE_USER, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleUser(@RequestParam int userId) {
        logger.info("Request Started In deleteSingleUser |userId={} ", userId);
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
