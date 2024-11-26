package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

import api.AuthentificationController;
import api.PostController;
import controller.ViewManagerModel;
import controller.create_post.CreatePostPresenter;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import controller.post.PostViewModel;
import controller.signup.SignupPresenter;
import controller.signup.SignupViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.PostFactory;
import entity.UserFactory;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInteractor;
import use_case.create_post.CreatePostOutputBoundary;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
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
		final MongoCollection<Document> postRepository = repositories.getPostRepository();

		// DAOs
		final DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userRepository);
		final DBPostDataAccessObject postDAO = new DBPostDataAccessObject(postRepository);

		// Entity Factories
		UserFactory commonUserFactory = new CommonUserFactory();
		PostFactory postFactory = new PostFactory();

		// Views (irrelavent for a web application)
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
		final LoginViewModel loginViewModel = new LoginViewModel();
		final HomepageViewModel homepageViewModel = new HomepageViewModel();
		final PostViewModel postViewModel = new PostViewModel();
		final CreatePostViewModel createPostViewModel = new CreatePostViewModel();

		// Presenters (irrelavent for a web application)
		final SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
		final LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);
		final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
		final CreatePostOutputBoundary createPostPresenter = new CreatePostPresenter(createPostViewModel, viewManagerModel);

		// Service Interactors
		final SignupInputBoundary signUpInteractor = new SignupInteractor(userDAO, signupPresenter, commonUserFactory);
		final LoginInputBoundary loginInteractor = new LoginInteractor(userDAO, loginPresenter, commonUserFactory);
		final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
		final CreatePostInputBoundary createPostInteractor = new CreatePostInteractor(postDAO, userDAO, createPostPresenter, postFactory);

		// RestAPI
		new AuthentificationController(signUpInteractor, loginInteractor);
		new PostController(userDAO, getPostInteractor, createPostInteractor);

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
