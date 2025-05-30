package com.maheesha_mobile.pos.controller;

import com.maheesha_mobile.pos.constant.APIConstants;
import com.maheesha_mobile.pos.dto.LoginDto;
import com.maheesha_mobile.pos.services.JwtService;
import com.maheesha_mobile.pos.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String response = userService.validateUser(loginDto);
        logger.info("Request Completed In validateUser |response={} ", response);
        if(!response.equals("User credential is correct")){
            return ResponseEntity.ok(Map.of(
                    "Status", "Successfully",
                    "Description", response
            ));
        }
        String newAccessToken = jwtService.generateAccessToken(loginDto.getUserName());
        String newRefreshToken = jwtService.generateRefreshToken(loginDto.getUserName());
        logger.info("Request Completed In validateUser |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
        ));
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
}
