package view;

import controller.signup.SignupController;
import controller.signup.SignupState;
import controller.signup.SignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField fullnameInputField = new JTextField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField birthdateInputField = new JTextField(15);
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;

    public SignupView(SignupController controller, SignupViewModel signupViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel fullnameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.NAME_LABEL), fullnameInputField);
        final LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.EMAIL_LABEL), emailInputField);
        final LabelTextPanel birthdateInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.BIRTHDATE), birthdateInputField);
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
//        final JButton signupbutton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);

        final JPanel buttons = new JPanel();

        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
//        buttons.add(signUp);
        // Create the label
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel signedUpLabel = new JLabel("Already signed up?");
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);

        panel.add(signedUpLabel);
        panel.add(toLogin);
        buttons.add(panel);

        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getUserID(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getEmail(),
                                    currentState.getBirthdate(),
                                    currentState.getFullName(),
                                    null,
                                    null
                            );
                            // TODO change null to the actual values
                        }
                    }
                }
        );

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        signupController.switchToLoginView();
                    }
                }
        );

        cancel.addActionListener(this);

        addEmailListener();
        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(fullnameInfo);
        this.add(emailInfo);
        this.add(birthdateInfo);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(signUp);
        this.add(buttons);
    }

    private void addEmailListener() {
        emailInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setEmail(emailInputField.getText()); // Update email in the state
                signupViewModel.setState(currentState); // Set the new state in the view model
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper(); // Call the helper when text is inserted
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper(); // Call the helper when text is removed
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper(); // Call the helper for changes (though typically not used for plain text)
            }
        });
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
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }
}
