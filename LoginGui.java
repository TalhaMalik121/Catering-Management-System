import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class LoginGui implements ActionListener, MouseListener, WindowListener {
    SignupGui signupGuiObj;
    LoginBackend loginBackendObj;

    AdminGUI adminGUI=new AdminGUI();
    UserGUI userGUI=new UserGUI();

    static String username1;

    static JFrame frame;


    JLabel titleLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel signupLabel;

    JTextField usernameTextField;
    JPasswordField passwordField;

    JButton submitButton;

    JRadioButton userRadioButton;
    JRadioButton adminRadioButton;

    FileOutputStream menuFileOStream = null;
    ObjectOutputStream menuObjectOStream = null;
    FileOutputStream orderFileOStream = null;
    ObjectOutputStream orderObjectOStream = null;


    public LoginGui(SignupGui signupGuiObj) {

        this.signupGuiObj = signupGuiObj;
        loginBackendObj = new LoginBackend();

        frame = new JFrame("Catering Management System");

        frame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Welcome to Catering Management System");
        titleLabel.setBounds(70, 80, 380, 50);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 130, 100, 50);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(210, 145, 120, 20);
        usernameTextField.setColumns(40);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 160, 100, 50);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 175, 120, 20);
        passwordField.setColumns(40);

        signupLabel = new JLabel("Signup");
        signupLabel.setBounds(300, 230, 70, 20);
        signupLabel.setForeground(Color.BLUE);
        signupLabel.addMouseListener(this);

        userRadioButton = new JRadioButton("User");
        userRadioButton.setBounds(100,210,100,20);
        userRadioButton.setBackground(Color.decode("#D6EFFF"));
        userRadioButton.setFocusable(false);

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(100,230,100,20);
        adminRadioButton.setBackground(Color.decode("#D6EFFF"));
        adminRadioButton.setFocusable(false);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(userRadioButton);
        radioButtonGroup.add(adminRadioButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 260, 90, 20);
        submitButton.setBackground(Color.CYAN);
        submitButton.setActionCommand("Submit");
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);

        frame.addWindowListener(this);
        frame.add(titleLabel);
        frame.add(usernameLabel);
        frame.add(usernameTextField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signupLabel);
        frame.add(submitButton);
        frame.add(userRadioButton);
        frame.add(adminRadioButton);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Submit") && !usernameTextField.getText().isEmpty() && !(new String(passwordField.getPassword()).isEmpty()) && userRadioButton.isSelected()){
            loginBackendObj.username = usernameTextField.getText();
            username1=loginBackendObj.username;
            loginBackendObj.password = new String(passwordField.getPassword());

            for (SignupBackend signup : signupGuiObj.SignupArraylist) {
                if (signup.username.equals(loginBackendObj.username) &&
                        signup.password.equals(loginBackendObj.password)) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    frame.setVisible(false);
                    userGUI.userGUI();
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "Login Failed");
        }
        else if(command.equals("Submit") && usernameTextField.getText().equals("Admin")&& new String(passwordField.getPassword()).equals("12345") && adminRadioButton.isSelected()){
            frame.setVisible(false);
            adminGUI.adminGui();
            AdminGUI.adminFrame.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null,"Please Enter username and Password");

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame.dispose();
        signupGuiObj.mygui();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        signupLabel.setText("<HTML><U>Signup</U></HTML>");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        signupLabel.setText("Signup");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {


    try{
        menuFileOStream = new FileOutputStream("MenuObj.txt");
        menuObjectOStream = new ObjectOutputStream(menuFileOStream);

        for(MenuBackend data : MenuGUI.menuArrayList){
            menuObjectOStream.writeObject(data);

        }
    }
                catch(Exception x){

    }
                finally {
        try {
            menuObjectOStream.close();
            menuFileOStream.close();
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
    }
        try{
            orderFileOStream = new FileOutputStream("OrderObj.txt");
            orderObjectOStream = new ObjectOutputStream(orderFileOStream);

            for(OrderBackend data : OrderGUI.OrdersList){
                orderObjectOStream.writeObject(data);
            }
        }
        catch(Exception x){
        }
        finally {
            try {
                orderObjectOStream.close();
                orderFileOStream.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }


    }

    @Override
    public void windowClosed(WindowEvent e) {


    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}