package app;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
import use_case.get_user.GetUserInputBoundary;
import use_case.get_user.GetUserInteractor;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

@Configuration
public class AppConfig {

    @Bean
    public Repositories repositories() {
        return new Repositories();
    }

    @Bean
    public MongoCollection<Document> userRepository(Repositories repositories) {
        return repositories.getUserRepository();
    }

    @Bean
    public MongoCollection<Document> postRepository(Repositories repositories) {
        return repositories.getPostRepository();
    }

    // DAOs
    @Bean
    public DBUserDataAccessObject userDAO(MongoCollection<Document> userRepository) {
        return new DBUserDataAccessObject(userRepository);
    }

    @Bean
    public DBPostDataAccessObject postDAO(MongoCollection<Document> postRepository) {
        return new DBPostDataAccessObject(postRepository);
    }

    // Factories
    @Bean
    public UserFactory userFactory() {
        return new CommonUserFactory();
    }

    @Bean
    public PostFactory postFactory() {
        return new PostFactory();
    }

    // View Models
    @Bean
    public ViewManagerModel viewManagerModel() {
        return new ViewManagerModel();
    }

    @Bean
    public SignupViewModel signupViewModel() {
        return new SignupViewModel();
    }

    @Bean
    public LoginViewModel loginViewModel() {
        return new LoginViewModel();
    }

    @Bean
    public HomepageViewModel homepageViewModel(){
        return new HomepageViewModel();
    }

    @Bean
    public PostViewModel postViewModel() {
        return new PostViewModel();
    }

    @Bean
    public CreatePostViewModel createPostViewModel() {
        return new CreatePostViewModel();
    }

    // Presenters
    @Bean
    public SignupOutputBoundary signupPresenter(ViewManagerModel viewManagerModel,
                                                SignupViewModel signupViewModel,
                                                LoginViewModel loginViewModel) {
        return new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel); // Adjust dependencies if needed
    }

    @Bean
    public LoginOutputBoundary loginPresenter(ViewManagerModel viewManagerModel,
                                              HomepageViewModel homepageViewModel,
                                              LoginViewModel loginViewModel,
                                              SignupViewModel signupViewModel) {
        return new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);
    }

    @Bean
    public GetPostOutputBoundary homepagePresenter(ViewManagerModel viewManagerModel,
                                                   HomepageViewModel homepageViewModel,
                                                   PostViewModel postViewModel) {
        return new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
    }

    @Bean
    public CreatePostOutputBoundary createPostPresenter(CreatePostViewModel createPostViewModel, ViewManagerModel viewManagerModel) {
        return new CreatePostPresenter(createPostViewModel, viewManagerModel);
    }

    // Services
    @Bean
    public SignupInputBoundary signupInteractor(DBUserDataAccessObject userDAO,
                                                SignupOutputBoundary signupPresenter,
                                                UserFactory userFactory) {
        return new SignupInteractor(userDAO, signupPresenter, userFactory);
    }

    @Bean
    public LoginInputBoundary loginInteractor(DBUserDataAccessObject userDAO,
                                              LoginOutputBoundary loginPresenter,
                                              UserFactory userFactory) {
        return new LoginInteractor(userDAO, loginPresenter, userFactory);
    }

    @Bean
    public GetPostInputBoundary getPostInteractor(DBPostDataAccessObject postDAO,
                                                  GetPostOutputBoundary homepagePresenter) {
        return new GetPostInteractor(postDAO, homepagePresenter);
    }

    @Bean
    public CreatePostInputBoundary createPostInteractor(DBPostDataAccessObject postDAO,
                                                        DBUserDataAccessObject userDAO,
                                                        CreatePostOutputBoundary createPostOutputBoundary,
                                                        PostFactory postFactory) {
        return new CreatePostInteractor(postDAO, userDAO, createPostOutputBoundary, postFactory);
    }

    @Bean
    public GetUserInputBoundary getUserInteractor(DBUserDataAccessObject userDAO,
                                                  UserFactory userFactory) {
        return new GetUserInteractor(userDAO, userFactory);
    }

    @Bean
    public LogoutInputBoundary logoutInteractor(DBUserDataAccessObject userDAO) {
        return new LogoutInteractor(userDAO);
    }

    // RestAPIs
    @Bean
    public AuthentificationController authentificationController(SignupInputBoundary signupInteractor,
                                                                 LoginInputBoundary loginInteractor,
                                                                 LogoutInputBoundary logoutInteractor) {
        return new AuthentificationController(signupInteractor, loginInteractor, logoutInteractor);
    }

    @Bean
    public PostController postController(DBUserDataAccessObject userDAO,
                                         GetPostInputBoundary getPostInteractor,
                                         CreatePostInputBoundary createPostInteractor) {
        return new PostController(userDAO, getPostInteractor, createPostInteractor);
    }
}