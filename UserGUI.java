import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserGUI implements ActionListener {
    OrderGUI orderGUI=new OrderGUI();
    static ArrayList<String> menuItemNamesList=new ArrayList<>();

    static JFrame userFrame;

    JLabel title;

    JButton menuButton;
    JButton newOrder;
    JButton deleteOrder;
    JButton modifyOrder;
    JButton receipt;
    JButton logOut;



    void userGUI(){

        namesFromOneListToAnother();

        userFrame = new JFrame("User Page");
        userFrame.setLayout(null);
        userFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setSize(500,470);

        title = new JLabel();
        title.setText("WHAT YOU WANT TO DO?");
        title.setLayout(null);
        title.setBounds(150,30,250,40);
        title.setFont(new Font("Tahoma",Font.BOLD,15));

        menuButton = new JButton("Menu");
        menuButton.setBounds(150,80,200,40);
        menuButton.setBackground(Color.cyan);
        menuButton.setActionCommand("menu");
        menuButton.setFocusable(false);
        menuButton.addActionListener(this);

        newOrder = new JButton("New Order");
        newOrder.setBounds(150,130,200,40);
        newOrder.setActionCommand("New Order");
        newOrder.addActionListener(this);
        newOrder.setBackground(Color.cyan);
        newOrder.setFocusable(false);

        deleteOrder = new JButton("Delete Order");
        deleteOrder.setBounds(150,180,200,40);
        deleteOrder.setBackground(Color.cyan);
        deleteOrder.setActionCommand("Delete Order");
        deleteOrder.addActionListener(this);
        deleteOrder.setFocusable(false);

        modifyOrder = new JButton("Modify Order");
        modifyOrder.setBounds(150,230,200,40);
        modifyOrder.setActionCommand("Modify Order");
        modifyOrder.setBackground(Color.CYAN);
        modifyOrder.addActionListener(this);
        modifyOrder.setFocusable(false);

        receipt=new JButton("Print Receipt");
        receipt.setBounds(150,280,200,40);
        receipt.setBackground(Color.CYAN);
        receipt.setActionCommand("receipt");
        receipt.addActionListener(this);
        receipt.setFocusable(false);

        logOut = new JButton("Log Out");
        logOut.setBounds(150,330,200,40);
        logOut.setBackground(Color.CYAN);
        logOut.setActionCommand("logout");
        logOut.addActionListener(this);
        logOut.setFocusable(false);


        userFrame.add(receipt);
        userFrame.add(menuButton);
        userFrame.add(title);
        userFrame.add(logOut);
        userFrame.add(modifyOrder);
        userFrame.add(deleteOrder);
        userFrame.add(newOrder);
        userFrame.setVisible(true);
    }

    void namesFromOneListToAnother(){
        menuItemNamesList.clear();
        for(MenuBackend obj:MenuGUI.menuArrayList){
            String abc=obj.name;
            menuItemNamesList.add(abc);
        }
    }

    void printMenuGui(){
        JFrame menuOptionsFrame = new JFrame("Print Menu");
        menuOptionsFrame.setSize(500, 500);
        menuOptionsFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        menuOptionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuOptionsFrame.setLayout(null);

        String[] columnNames = {"Item Name", "Item Price"};
        Object[][] rowData = new Object[MenuGUI.menuArrayList.size()][2];

        for (int i = 0; i < MenuGUI.menuArrayList.size(); i++) {
            rowData[i][0] = MenuGUI.menuArrayList.get(i).name;
            rowData[i][1] = MenuGUI.menuArrayList.get(i).price;
        }

        JTable menuTable = new JTable(rowData, columnNames);
        JTableHeader Theader=menuTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(menuTable);
        menuOptionsFrame.add(scroll);
        scroll.setBounds(25,25,450,350);
        menuOptionsFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("menu")){
            printMenuGui();
        }
        else if(command.equals("logout")){
            userFrame.dispose();
            LoginGui.frame.setVisible(true);
        }
        else if (command.equals("New Order")) {
            orderGUI.newOrderGui();
            OrderGUI.orderFrame.setVisible(true);
        }
        else if (command.equals("Delete Order")) {
            userFrame.setVisible(false);
            orderGUI.deleteOrderGUI();
            OrderGUI.orderFrame.setVisible(true);

        } else if (command.equals("Modify Order")) {
            userFrame.setVisible(false);
            orderGUI.modifyOrderGUI();
            OrderGUI.orderFrame.setVisible(true);

        } else if (command.equals("receipt")) {
            userFrame.setVisible(false);
            orderGUI.printReceipt();
        }
    }
}