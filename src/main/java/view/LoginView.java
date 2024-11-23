package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import controller.login.LoginController;
import controller.login.LoginState;
import controller.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    // Input field for username and password
    private final JTextField emailInputField = new JTextField(15);
    private final JLabel emailErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    // Buttons
    private final JButton logIn;

    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        // Set the main panel background
        this.setBackground(new Color(30, 30, 30));

        // email panel with label and input field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailInputField.setBackground(new Color(50, 50, 60));
        emailInputField.setForeground(Color.GRAY);
        emailInputField.setText("e.g. dennis_ivy");
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(new Color(50, 50, 60));
        emailPanel.add(emailLabel);
        emailPanel.add(emailInputField);

        // Password panel with label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordInputField.setBackground(new Color(50, 50, 60));
        passwordInputField.setForeground(Color.blue);
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(new Color(50, 50, 60));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInputField);

        // Header panel with app name or icon
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(19, 19, 223));
        JLabel headerLabel = new JLabel("LOGIN");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        headerPanel.add(headerLabel);

        // Login button with styling
        logIn = new JButton("Login");
        logIn.setBackground(new Color(0, 123, 255));
        logIn.setForeground(Color.WHITE);
        logIn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sign-up link as a JLabel styled
        JLabel signUpPrompt = new JLabel("Haven't signed up yet?");
        signUpPrompt.setForeground(Color.LIGHT_GRAY);
        signUpPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel signUpLink = new JLabel("Sign Up");
        signUpLink.setForeground(Color.CYAN);
        signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        signUpLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                loginController.switchToSignupView();
            }

        });

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            final LoginState currentState = loginViewModel.getState();
                            loginController.execute(
                                    currentState.getEmail(),
                                    currentState.getPassword());
                        }
                    }
                }
        );

        emailInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setEmail(emailInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(50, 50, 60));
        buttonsPanel.add(logIn);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(signUpPrompt);
        buttonsPanel.add(signUpLink);

        // Login panel
        final JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(50, 50, 60));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(emailPanel);
        loginPanel.add(emailErrorField);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(buttonsPanel);

        // mainPanel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(headerPanel);
        mainPanel.add(loginPanel);
        this.setLayout(new GridBagLayout());
        this.add(mainPanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        emailErrorField.setText(state.getEmailError());
    }

    private void setFields(LoginState state) {
        emailErrorField.setText(state.getEmail());
    }

    public String getViewName() {
        return viewName;
    }
}
