package com.maheesha_mobile.pos.controller;

import com.maheesha_mobile.pos.constant.APIConstants;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(value = APIConstants.CREATE_ACCESS_TOKEN, method = RequestMethod.GET)
    public ResponseEntity<?> generateAccessToken(@RequestParam String refreshToken) {
        logger.info("Request Started In generateAccessToken |token={} ", refreshToken);

        if (jwtService.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
        }
        String userName = jwtService.extractUserName(refreshToken);
        String newAccessToken = jwtService.generateAccessToken(userName);

        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken
        ));
    }

}
