package com.ecommerce.ecommerce.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.implementation.UserService;
import com.ecommerce.ecommerce.repositories.IUserRepository;
import com.ecommerce.ecommerce.security.JwtUtil;
import com.ecommerce.ecommerce.security.LoginRequest;
import com.ecommerce.ecommerce.security.LoginResponse;
import com.ecommerce.ecommerce.security.UserResponse;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthRestController {
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);
        
        User user = userRepository.findByUsername(userDetails.getUsername());        
        
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
				        
        return ResponseEntity.ok(new LoginResponse(token, new UserResponse(user.getId(), user.getUsername(), user.getNombre(), user.getApellido(), user.getDni(), user.getEmail(), user.getTelefono(), roles)));
       
    }
    
    @PostMapping("/checkUser")
    public ResponseEntity<?> checkUser() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        User user = userRepository.findByUsername(currentPrincipalName);       
       
        List<String> roles = user.getUserRoles().stream()
        		.map(item -> item.getRole())
        		.collect(Collectors.toList());
                
        return  ResponseEntity.ok(new LoginResponse(new UserResponse(user.getId(), user.getUsername(), user.getNombre(), user.getApellido(), user.getDni(), user.getEmail(), user.getTelefono(), roles)));
    }


}
