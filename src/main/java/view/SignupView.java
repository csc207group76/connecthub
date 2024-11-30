package view;

import controller.signup.SignupController;
import controller.signup.SignupState;
import controller.signup.SignupViewModel;
import entity.Comment;
import entity.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";
    private final SignupViewModel signupViewModel;
    private final SignupController signupController;

    private final JTextField fullnameInputField = new JTextField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField birthdateInputField = new JTextField(15);
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);


    private final JButton signUp;
    private final JButton toLogin;

    public SignupView(SignupController controller, SignupViewModel signupViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        this.setBackground(new Color(30, 30, 30));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Full name panel
        JLabel fullnameInfo = new JLabel((SignupViewModel.NAME_LABEL));
        fullnameInfo.setForeground(Color.WHITE);
        fullnameInputField.setBackground(new Color(50, 50, 60));
        fullnameInputField.setForeground(Color.GRAY);
        fullnameInputField.setText("e.g. Arjun Singh");
        JPanel fullNamePanel = new JPanel();
        fullNamePanel.setLayout(new BoxLayout(fullNamePanel, BoxLayout.Y_AXIS));
        fullNamePanel.setBackground(new Color(50, 50, 60));
        fullNamePanel.add(fullnameInfo);
        fullNamePanel.add(fullnameInputField);


        // Email name panel
        JLabel emailInfo = new JLabel(SignupViewModel.EMAIL_LABEL);
        emailInfo.setForeground(Color.WHITE);
        emailInputField.setBackground(new Color(50, 50, 60));
        emailInputField.setForeground(Color.GRAY);
        emailInputField.setText("e.g. izabelle@gmail.com");
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(new Color(50, 50, 60));
        emailPanel.add(emailInfo);
        emailPanel.add(emailInputField);

        // Birthdate name panel
        JLabel birthdateInfo = new JLabel(SignupViewModel.BIRTHDATE);
        birthdateInfo.setForeground(Color.WHITE);
        birthdateInputField.setBackground(new Color(50, 50, 60));
        birthdateInputField.setForeground(Color.GRAY);
        birthdateInputField.setText("12/12/23");
        JPanel bdayPanel = new JPanel();
        bdayPanel.setLayout(new BoxLayout(bdayPanel, BoxLayout.Y_AXIS));
        bdayPanel.setBackground(new Color(50, 50, 60));
        bdayPanel.add(birthdateInfo);
        bdayPanel.add(birthdateInputField);


        // username  panel
        JLabel usernameInfo = new JLabel(SignupViewModel.USERNAME_LABEL);
        usernameInfo.setForeground(Color.WHITE);
        usernameInputField.setBackground(new Color(50, 50, 60));
        usernameInputField.setForeground(Color.GRAY);
        usernameInputField.setText("e.g. dennis_ivy");
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
        usernamePanel.setBackground(new Color(50, 50, 60));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(usernameInputField);

        // password  panel
        JLabel passwordInfo = new JLabel(SignupViewModel.PASSWORD_LABEL);
        passwordInfo.setForeground(Color.WHITE);
        passwordInputField.setBackground(new Color(50, 50, 60));
        passwordInputField.setForeground(Color.blue);
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(new Color(50, 50, 60));
        passwordPanel.add(passwordInfo);
        passwordPanel.add(passwordInputField);


        // repeat password panel
        JLabel repeatPasswordInfo = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordInfo.setForeground(Color.WHITE);
        repeatPasswordInputField.setBackground(new Color(50, 50, 60));
        repeatPasswordInputField.setForeground(Color.blue);
        JPanel repeatPasswordPanel = new JPanel();
        repeatPasswordPanel.setLayout(new BoxLayout(repeatPasswordPanel, BoxLayout.Y_AXIS));
        repeatPasswordPanel.setBackground(new Color(50, 50, 60));
        repeatPasswordPanel.add(repeatPasswordInfo);
        repeatPasswordPanel.add(repeatPasswordInputField);

        // SIGN UP TITLE:

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(19, 19, 223));
        JLabel headerLabel = new JLabel(SignupViewModel.TITLE_LABEL);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        headerPanel.add(headerLabel);

        // sign up button
        signUp = new JButton("SignUp");
        signUp.setBackground(new Color(0, 123, 255));
        signUp.setForeground(Color.BLUE);
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);


        // switch to login
        JLabel signedUpLabel = new JLabel("Already signed up?");
        signedUpLabel.setForeground(Color.LIGHT_GRAY);
        signedUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        toLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toLogin.setForeground(Color.BLUE);



        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            List<String> moderators = new ArrayList();
                            List<String> comments = new ArrayList();


                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getUserID(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getEmail(),
                                    currentState.getBirthdate(),
                                    currentState.getFullName(), moderators, comments
                            );
                        }
                    }
                }
        );

        toLogin.addActionListener(
                evt -> signupController.switchToLoginView()
        );


        // All action listersns added as helper methods
        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        // buttons panel

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        buttonsPanel.setBackground(new Color(50, 50, 60));

        // Add the "Sign Up" button to the panel
        buttonsPanel.add(signUp);

        // Adding a space between "Sign Up" and the other buttons
        buttonsPanel.add(Box.createVerticalStrut(10));

        // Creating a minipanel for login and cancel
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Align buttons horizontally with gaps
        horizontalPanel.setBackground(new Color(50, 50, 60));

        // Add "Go to Login" and buttons to the sub-panel
        JPanel loginSwitch = new JPanel();
        loginSwitch.add(signedUpLabel);
        loginSwitch.add(toLogin);
        horizontalPanel.add(loginSwitch);

        // Add the horizontal panel to the main button panel
        buttonsPanel.add(horizontalPanel);

        // Signup panel
        final JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(50, 50, 60));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(fullNamePanel);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(bdayPanel);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(emailPanel);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(usernamePanel);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(passwordPanel);

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(repeatPasswordPanel);

        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(buttonsPanel);

        // mainPanel based on Lucas's:

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(headerPanel);
        mainPanel.add(loginPanel);

        this.setLayout(new GridBagLayout());
        this.add(mainPanel);

    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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
    }

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet!");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    // TODO: is this the right way to do the follwoing for signup state - Not sure what its for.
//    private void setFields(LoginState state) {
//        emailErrorField.setText(state.getUsername());
//    }
    private void setFields(SignupState state) {
        if (state == null) {
            // Handling the null case
            usernameInputField.setText("");
            emailInputField.setText("");
            passwordInputField.setText("");
            repeatPasswordInputField.setText("");
            fullnameInputField.setText("");
            return;
        }
        usernameInputField.setText(state.getUsername());
        emailInputField.setText(state.getEmail());
        passwordInputField.setText(state.getPassword());
        repeatPasswordInputField.setText(state.getRepeatPassword());
        fullnameInputField.setText(state.getFullName());
    }



    // TODO: prepare sucesss view EDIT:don't think its needed here

    public String getViewName() {
        return viewName;
    }
}