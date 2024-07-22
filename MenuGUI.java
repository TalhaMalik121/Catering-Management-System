import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class MenuGUI implements ActionListener, Serializable {

    static JFrame menuGuiFrame;
    static JFrame menuOptionsFrame;
    static JFrame menuEditFrame;

    JButton backButton;
    JButton addMenu;
    JButton editMenu;
    JButton printMenu;
    JButton addItemToMenuButton;
    JButton editDoneButton;

    JLabel menuItemName;
    JLabel editMenuItemName;
    JLabel newMenuItemNameLabel;
    JLabel newMenuPriceLabel;
    JLabel menuItemPrice;

    JTextField newMenuItemName;
    JTextField editMenuItemTextField;
    JTextField menuNameTextField;

    JSpinner newMenuPrice;
    JSpinner menuPriceTextField;


    static ArrayList<MenuBackend> menuArrayList = new ArrayList<>();

    void adminMenuGUI(){
        menuGuiFrame = new JFrame("Menu Page");
        menuGuiFrame.setSize(500,500);
        menuGuiFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        menuGuiFrame.setLayout(null);
        menuGuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMenu = new JButton("Add Menu");
        addMenu.setBounds(150,130,200,40);
        addMenu.setBackground(Color.cyan);
        addMenu.setActionCommand("addmenu");
        addMenu.addActionListener(this);
        addMenu.setFocusable(false);

        editMenu = new JButton("Edit Menu");
        editMenu.setBounds(150,180,200,40);
        editMenu.setBackground(Color.cyan);
        editMenu.setActionCommand("editMenu");
        editMenu.addActionListener(this);
        editMenu.setFocusable(false);

        printMenu = new JButton("Print Menu");
        printMenu.setBounds(150,230,200,40);
        printMenu.setBackground(Color.cyan);
        printMenu.setActionCommand("printMenu");
        printMenu.addActionListener(this);
        printMenu.setFocusable(false);

        backButton = new JButton("Back");
        backButton.setBounds(10,10,75,30);
        backButton.setBackground(Color.cyan);
        backButton.setActionCommand("adminBack");
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        menuGuiFrame.add(backButton);
        menuGuiFrame.add(addMenu);
        menuGuiFrame.add(editMenu);
        menuGuiFrame.add(printMenu);
        menuGuiFrame.setVisible(true);
    }
    void addMenuGUI(){
        menuOptionsFrame = new JFrame("Add Menu");
        menuOptionsFrame.setSize(350,250);
        menuOptionsFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        menuOptionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuOptionsFrame.setLayout(null);

        menuItemName = new JLabel();
        menuItemName.setText("Name: ");
        menuItemName.setBounds(50,40,50,40);

        menuNameTextField = new JTextField();
        menuNameTextField.setBounds(110,50,140,20);

        menuItemPrice = new JLabel();
        menuItemPrice.setText("Price: ");
        menuItemPrice.setBounds(50,90,50,40);

        menuPriceTextField = new JSpinner();
        menuPriceTextField.setBounds(110,100,140,20);

        addItemToMenuButton = new JButton("Add");
        addItemToMenuButton.setBounds(200,160,70,20);
        addItemToMenuButton.setBackground(Color.cyan);
        addItemToMenuButton.setActionCommand("addMenuButton");
        addItemToMenuButton.addActionListener(this);

        menuOptionsFrame.add(addItemToMenuButton);
        menuOptionsFrame.add(menuItemPrice);
        menuOptionsFrame.add(menuPriceTextField);
        menuOptionsFrame.add(menuNameTextField);
        menuOptionsFrame.add(menuItemName);

        menuOptionsFrame.setVisible(true);
    }
    void addItemToMenu(){
        int response=JOptionPane.showConfirmDialog(null,"You want to Add more","Confirmation",JOptionPane.YES_NO_OPTION);
        String name = menuNameTextField.getText();
        int price = (int) menuPriceTextField.getValue();
        MenuBackend m1 = new MenuBackend();
        m1.name = name;
        m1.price = price;
        menuArrayList.add(m1);
        if(response==JOptionPane.YES_OPTION){
            menuNameTextField.setText("");
            menuPriceTextField.setValue(0);
        }
        else{
            menuOptionsFrame.dispose();
            menuGuiFrame.setVisible(true);

        }
    }

    void printMenuGui(){
        JFrame menuOptionsFrame = new JFrame("Print Menu");
        menuOptionsFrame.setSize(500, 500);
        menuOptionsFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        menuOptionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuOptionsFrame.setLayout(null);

        String[] columnNames = {"Item Name", "Item Price"};
        Object[][] rowData = new Object[menuArrayList.size()][2];


        for (int i = 0; i < menuArrayList.size(); i++) {
            rowData[i][0] = menuArrayList.get(i).name;
            rowData[i][1] = menuArrayList.get(i).price;
        }

        JTable menuTable = new JTable(rowData, columnNames);

        JScrollPane scroll = new JScrollPane(menuTable);
        JTableHeader Theader=menuTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        scroll.setBounds(25,25,450,350);
        menuOptionsFrame.add(scroll);

        menuOptionsFrame.setVisible(true);
    }
    void editMenuGui(){
        menuEditFrame=new JFrame("Edit the Menu");
        menuEditFrame.setLayout(null);
        menuEditFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        menuEditFrame.setSize(500,500);
        menuEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        editMenuItemName=new JLabel("Item to Change");
        editMenuItemName.setBounds(100, 130, 100, 50);
        menuEditFrame.add(editMenuItemName);

        editMenuItemTextField=new JTextField();
        editMenuItemTextField.setBounds(210, 145, 120, 20);
        editMenuItemTextField.setColumns(40);
        menuEditFrame.add(editMenuItemTextField);

        newMenuItemNameLabel=new JLabel("Enter New Name");
        newMenuItemNameLabel.setBounds(100, 160, 100, 50);
        menuEditFrame.add(newMenuItemNameLabel);

        newMenuItemName=new JTextField();
        newMenuItemName.setBounds(210, 175, 120, 20);
        newMenuItemName.setColumns(40);
        menuEditFrame.add(newMenuItemName);


        newMenuPriceLabel=new JLabel("Enter New Price");
        newMenuPriceLabel.setBounds(100,190,100,50);
        menuEditFrame.add(newMenuPriceLabel);

        newMenuPrice=new JSpinner();
        newMenuPrice.setBounds(210,205,120,20);
        menuEditFrame.add(newMenuPrice);

        editDoneButton=new JButton("Done");
        editDoneButton.setBounds(260,235,70,20);
        editDoneButton.setBackground(Color.cyan);
        editDoneButton.setFocusable(false);
        editDoneButton.setActionCommand("editDone");
        editDoneButton.addActionListener(this);
        menuEditFrame.add(editDoneButton);

        menuEditFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Back")){
            menuGuiFrame.dispose();
            AdminGUI.adminFrame.setVisible(true);
        }
        else if(command.equals("adminBack")){
            menuGuiFrame.dispose();
            AdminGUI.adminFrame.setVisible(true);
        }
        else if (command.equals("addmenu")) {
            menuGuiFrame.setVisible(false);
            addMenuGUI();
        }
        else if(command.equals("addMenuButton")){
            if(!(menuNameTextField.getText().isEmpty()) && !(menuPriceTextField.getValue() == null)){
                JOptionPane.showMessageDialog(null,"Item Successfully added to Menu");
                addItemToMenu();

            }
            else{
                JOptionPane.showMessageDialog(null,"Please fill the fields.");
            }}
        else if(command.equals("printMenu"))
        {
            printMenuGui();

        }
        else if (command.equals("editDone")) {
            String abc=editMenuItemTextField.getText();
            for(MenuBackend obj:menuArrayList){
                if(obj.name.equals(abc)){
                    obj.name=newMenuItemName.getText();
                    obj.price= (int) newMenuPrice.getValue();
                    JOptionPane.showMessageDialog(null,"Editing Done");
                    menuEditFrame.setVisible(false);
                    menuGuiFrame.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"No Matching item in Menu");
            menuEditFrame.setVisible(false);
            menuGuiFrame.setVisible(true);

        }
        else if (command.equals("editMenu")) {
            menuGuiFrame.setVisible(false);
            editMenuGui();

        }

    }
}