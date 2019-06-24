package fr.esgi.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.esgi.security.jwt.JWTConfigurer;
import fr.esgi.security.jwt.TokenProvider;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.web.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserJWTController to authenticate users.
 * @author christopher
 */
@Api(value = "UserJWT")
@RestController
@RequestMapping("/api")
public class UserJWTResource {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    
    private final UserService userService;

    @Autowired
    public UserJWTResource(TokenProvider tokenProvider, AuthenticationManager authenticationManager,
			UserService userService) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	/**
     * Authenticate the user and return the token which identify him.
     * @param login of the user
     * @return JWTToken
     */
    @ApiOperation(value = "Authenticate the user and return the token which identify him.")
    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody Login login) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (login.isRememberMe() == null) ? false : login.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt, getIdUser(login)), httpHeaders, HttpStatus.OK);
    }

    private Long getIdUser(Login login) {
    	return userService.findUserByUsername(login.getUsername())
    			.map(UserDTO::getId)
    			.orElse(null);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;
        private Long idUser;

        JWTToken(String idToken, Long idUser) {
			this.idToken = idToken;
			this.idUser = idUser;
		}

		@JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_user")
		public Long getIdUser() {
			return idUser;
		}

		void setIdUser(Long idUser) {
			this.idUser = idUser;
		}
    }
}
