package com.example.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.MediaType;
//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.Model.AuthenticationRequest;
import com.example.project.Model.AuthenticationResponse;
import com.example.project.Model.Patient;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;
import com.example.project.service.ApplicationUserService;


@RestController
@RequestMapping("/")
public class ApplicationUserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserService applicationUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ApplicationUserController(ApplicationUserRepository applicationUserRepository, ApplicationUserService applicationUserService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserService = applicationUserService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping( value = "/register", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody ApplicationUser applicationUser) {
        ApplicationUser user = applicationUserRepository.save(applicationUser);
        if (applicationUser != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping( value = "/signin", headers = "Accept=application/json", produces ="application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username and password: "+e.getMessage());
        }

        final UserDetails userDetails = applicationUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        
        AuthenticationResponse authResponse = new AuthenticationResponse(jwt);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", userDetails.getUsername());
        map.put("token", authResponse.getToken());
        ResponseEntity rspEntity = new ResponseEntity(map,HttpStatus.OK);
        

//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        return rspEntity;
    }

    @GetMapping( value = "/viewprofile/{username}", headers = "Accept=application/json", produces ="application/json")
    public @ResponseBody
    ResponseEntity<?> getApplicationUser(@PathVariable("username") String username) throws Exception {
    	
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", applicationUserService.loadUserByUsername(username).getUsername());
        map.put("password", applicationUserService.loadUserByUsername(username).getPassword());
        ResponseEntity rspEntity = new ResponseEntity(map,HttpStatus.OK);
        
        return rspEntity;
//        return ResponseEntity.ok().body(applicationUserService.loadUserByUsername(username));
    }
    
    @GetMapping("/list")
    List<ApplicationUser> all() {
      return applicationUserService.getApplicationUsers();
    }
    
}