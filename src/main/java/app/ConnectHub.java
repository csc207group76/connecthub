package app;

import java.awt.CardLayout;

import javax.swing.*;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import controller.ViewManagerModel;
import controller.homepage.HomepageViewModel;
import controller.login.LoginViewModel;
import controller.post.PostViewModel;
import controller.login.LoginViewModel;
import controller.signup.SignupViewModel;
import view.HomePageView;
import view.PostView;
import view.LoginView;
import view.SignupView;
import view.StyleConstants;
import view.ViewManager;

/**
 * The version of Main with an external database used to persist user data.
 */
public class ConnectHub {

	/**
	 * The main method for starting the program with an external database used to persist user data.
	 * @param args input to main
	 */
	public static void main(String[] args) {
		final Repositories repositories = new Repositories();
		final MongoCollection<Document> userRepository = repositories.getUserRepository();
		final MongoCollection<Document> postRepository = repositories.getPostRepository();
		final MongoCollection<Document> commentRepositroy = repositories.getCommentRepository();

		final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userRepository);
		final DBPostDataAccessObject postDataAccessObject = new DBPostDataAccessObject(postRepository);

		// Closes the connection with the database when the program terminates
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});

		// Build the main program window, the main panel containing the
		// various cards, and the layout, and stitch them together.

		// The main application window.
		final JFrame application = new JFrame("ConnectHub");

		// Close the app when clicking X
		application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Set window size
		application.setSize(StyleConstants.APP_WIDTH, StyleConstants.APP_HEIGHT);

		// cardLayout is assigned to views which manages different views
		final CardLayout cardLayout = new CardLayout();
		final JPanel views = new JPanel(cardLayout);

		// Add views to the main application frame
		application.add(views);

		// View manager manages the current view
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
		new ViewManager(views, cardLayout, viewManagerModel);

		// The data for the views, such as username and password, are in the ViewModels.
		// This information will be changed by a presenter object that is reporting the
		// results from the use case. The ViewModels are "observable", and will
		// be "observed" by the Views.
		final SignupViewModel signupViewModel = new SignupViewModel();
		final LoginViewModel loginViewModel = new LoginViewModel();
		final PostViewModel postViewModel = new PostViewModel();
		final HomepageViewModel homepageViewModel = new HomepageViewModel();

		final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel,
				signupViewModel, loginViewModel, userDataAccessObject);
		views.add(signupView, signupView.getViewName());

		final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
				homepageViewModel, userDataAccessObject, signupViewModel);
		views.add(loginView, loginView.getViewName());

		final HomePageView homepageView = HomepageUseCaseFactory.create(viewManagerModel, homepageViewModel,
				postViewModel, postDataAccessObject);
		views.add(homepageView, homepageView.getViewName());
		
		final PostView postView = GetPostUseCaseFactory.create(viewManagerModel, postViewModel,
				homepageViewModel, postDataAccessObject);
		views.add(postView, postView.getViewName());

		viewManagerModel.setState(loginView.getViewName());
		viewManagerModel.firePropertyChanged();

		application.pack();
		application.setVisible(true);
	}
}
