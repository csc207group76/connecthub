package api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import use_case.login.AccountDoesNotExistException;
import use_case.login.IncorrectPasswordException;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.signup.UserExistsException;

@RestController
public class AuthentificationController {
    private SignupInputBoundary signUpInteractor;
    private LoginInputBoundary loginInteractor;
    private LogoutInputBoundary logoutInteractor;

    public AuthentificationController(SignupInputBoundary signUpInteractor,
                                      LoginInputBoundary loginInteractor,
                                      LogoutInputBoundary logoutInteractor) {
        this.signUpInteractor = signUpInteractor;
        this.loginInteractor = loginInteractor;
        this.logoutInteractor = logoutInteractor;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> requestBody) {
        final SignupInputData signupInputData = new SignupInputData(
            requestBody.get("username"),
            requestBody.get("password"),
            requestBody.get("confirmation"),
            requestBody.get("email"),
            requestBody.get("birth_date"),
            requestBody.get("full_name")
        );

        try {
            signUpInteractor.signupUser(signupInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Signed up successfully!");
        } catch (UserExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong...");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logInUser(@RequestBody Map<String, String> requestBody) {
        final LoginInputData loginInputData = new LoginInputData(
            requestBody.get("email"),
            requestBody.get("password")
        );

        try {
            loginInteractor.LoginUser(loginInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Logged in successfully!");
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email not found");
        } catch (IncorrectPasswordException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong...");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOutUser() {
        LogoutInputData logoutInputData = new LogoutInputData(null);
        this.logoutInteractor.logoutUser(logoutInputData);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out");
    }
}
