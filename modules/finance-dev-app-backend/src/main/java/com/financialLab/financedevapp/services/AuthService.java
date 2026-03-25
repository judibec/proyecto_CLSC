package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.dto.requests.AuthRequestDTO;
import com.financialLab.financedevapp.dto.responses.AuthenticationResponseDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public AuthenticationResponseDTO login(AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDTO.getEmail(), authRequestDTO.getPassword()
            ));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(myUserDetailsService.loadUserByUsername(authRequestDTO.getEmail()));

                User user = userService.getByEmail(authentication.getName());
                return new AuthenticationResponseDTO(token, jwtService.getExpiration(token), UserDTO.of(user));
            } else {
                throw FinancialAppException.unauthorizedError("Invalid credentials");
            }
        }catch (BadCredentialsException ex) {
            throw FinancialAppException.unauthorizedError("Invalid email or password" + ex.getMessage());
        }catch (UsernameNotFoundException ex) {
            throw FinancialAppException.objectNotFound("User not found" + ex.getMessage());
        } catch (RuntimeException ex) {
            throw FinancialAppException.objectNotFound("User nor found" + ex.getMessage());
        }catch (Exception ex){
            throw FinancialAppException.serverException("Error Internal Server" + ex.getMessage());
        }
    }
}
