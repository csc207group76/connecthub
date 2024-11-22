package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

import api.Authentification;
import controller.ViewManagerModel;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import controller.signup.SignupPresenter;
import controller.signup.SignupViewModel;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

@SpringBootApplication
@ComponentScan({"app", "api"})
public class WebServer {
    public static void main(String[] args) {
        final Repositories repositories = new Repositories();
		final MongoCollection<Document> userRepository = repositories.getUserRepository();

		// DAOs
		final DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userRepository);

		// Entity Factories
		UserFactory commonUserFactory = new CommonUserFactory();

		// Views (irrelavent for a web application)
		final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final SignupViewModel signupViewModel = new SignupViewModel();
		final LoginViewModel loginViewModel = new LoginViewModel();

		// Presenters (irrelavent for a web application)
		final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
		final LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel); 

		// Service Interactors
		final SignupInputBoundary signUpInteractor = new SignupInteractor(userDAO, signupOutputBoundary, commonUserFactory);
		final LoginInputBoundary loginInteractor = new LoginInteractor(userDAO, loginPresenter, commonUserFactory);

		// RestAPI 
		new Authentification(signUpInteractor, loginInteractor); // TODO add the rest of the interactors

		// Application Start
        SpringApplication.run(WebServer.class, args);
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});
    }
}
