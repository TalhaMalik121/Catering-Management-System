import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SignupGui implements ActionListener, Serializable {
    SignupBackend signupBackendObj;
    static ArrayList<SignupBackend> SignupArraylist = new ArrayList<>();

    JFrame signupFrame;

    JLabel signupLabel;
    JLabel fnameLabel;
    JLabel lnameLabel;
    JLabel emailLabel;
    JLabel phoneNumberLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField fnameTextField;
    JTextField lnameTextField;
    JTextField emailTextField;
    JTextField phoneNumberTextField;
    JTextField usernameTextField;
    JPasswordField passwordField;

    JButton signupButton;
    FileOutputStream signUpFileOStream = null;
    ObjectOutputStream signUpObjectOStream = null;

    public SignupGui() {
    }

    void mygui() {
        signupBackendObj = new SignupBackend();


        signupFrame = new JFrame("Signup Page");
        signupFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupFrame.setLayout(null);
        signupFrame.setSize(500, 500);

        signupLabel = new JLabel("Signup");
        signupLabel.setFont(new Font("comic sans", Font.BOLD, 18));
        signupLabel.setBounds(70, 80, 380, 50);
        signupFrame.add(signupLabel);

        fnameLabel = new JLabel("Enter Fname");
        fnameLabel.setBounds(100, 130, 100, 50);
        signupFrame.add(fnameLabel);

        fnameTextField = new JTextField();
        fnameTextField.setBounds(210, 145, 120, 20);
        fnameTextField.setColumns(40);
        signupFrame.add(fnameTextField);

        lnameLabel = new JLabel("Enter Lname");
        lnameLabel.setBounds(100, 160, 100, 50);
        signupFrame.add(lnameLabel);

        lnameTextField = new JTextField();
        lnameTextField.setBounds(210, 175, 120, 20);
        lnameTextField.setColumns(40);
        signupFrame.add(lnameTextField);

        emailLabel = new JLabel("Enter Email");
        emailLabel.setBounds(100, 190, 100, 50);
        signupFrame.add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(210, 205, 120, 20);
        emailTextField.setColumns(40);
        signupFrame.add(emailTextField);

        phoneNumberLabel = new JLabel("Enter PhoneNo");
        phoneNumberLabel.setBounds(100, 220, 100, 50);
        signupFrame.add(phoneNumberLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(210, 235, 120, 20);
        phoneNumberTextField.setColumns(40);
        signupFrame.add(phoneNumberTextField);

        usernameLabel = new JLabel("Enter Username");
        usernameLabel.setBounds(100, 250, 100, 50);
        signupFrame.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(210, 265, 120, 20);
        usernameTextField.setColumns(40);
        signupFrame.add(usernameTextField);

        passwordLabel = new JLabel("Enter Password");
        passwordLabel.setBounds(100, 280, 100, 50);
        signupFrame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 295, 120, 20);
        passwordField.setColumns(40);
        signupFrame.add(passwordField);

        signupButton = new JButton("Signup");
        signupButton.setBounds(300, 335, 90, 20);
        signupButton.setActionCommand("signup1");
        signupButton.setBackground(Color.cyan);
        signupButton.addActionListener(this);
        signupFrame.add(signupButton);

        signupFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("signup1")) {
            if(!fnameTextField.getText().isEmpty() && !lnameTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty() && !((passwordField.getPassword().toString()).equals(""))){
                signupBackendObj.fname = fnameTextField.getText();
                signupBackendObj.lname = lnameTextField.getText();
                signupBackendObj.email = emailTextField.getText();
                signupBackendObj.phoneNumber = phoneNumberTextField.getText();
                signupBackendObj.username = usernameTextField.getText();
                signupBackendObj.password = new String(passwordField.getPassword());
                SignupArraylist.add(signupBackendObj);
                JOptionPane.showMessageDialog(null, "Signup successful");
                signupFrame.dispose();
                try{
                    signUpFileOStream = new FileOutputStream("SignUpObj.txt");
                    signUpObjectOStream = new ObjectOutputStream(signUpFileOStream);

                    for(SignupBackend data : SignupGui.SignupArraylist){
                        signUpObjectOStream.writeObject(data);
                    }
                }
                catch(Exception x){
                }
                finally {
                    try {
                        signUpObjectOStream.close();
                        signUpFileOStream.close();
                    }
                    catch(Exception ee){
                        ee.printStackTrace();
                    }
                }
                new LoginGui(this);
            }
            else {
                JOptionPane.showMessageDialog(null,"Signup Failed");
            }
        }
    }
}
