import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class OrderGUI implements ActionListener, Serializable {

    static ArrayList<OrderBackend> OrdersList=new ArrayList<>();

    static JFrame orderFrame;
   static JFrame receiptFrame;
    JLabel orderLabel;
    JLabel orderAmount;
    JLabel receiptPrice;
    JLabel price;
    JLabel pay;

    JSpinner payment;
    JComboBox<String> comboBox;

    JSpinner Spinner;

    JButton orderButton;
    JButton payButton;
    JButton deleteButton,backButton,editButton;
    int totalprice;

    JTable orderTable;
    DefaultTableModel orderTableModel;


    void newOrderGui(){
        orderFrame=new JFrame("New Order");
        orderFrame.setSize(500,500);
        orderFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        orderFrame.setLayout(null);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        orderLabel=new JLabel("Select Item");
        orderLabel.setBounds(100, 130, 100, 50);
        orderFrame.add(orderLabel);

        comboBox=new JComboBox<>(UserGUI.menuItemNamesList.toArray(new String[0]));
        comboBox.setBounds(210, 145, 120, 20);
        orderFrame.add(comboBox);

        orderAmount=new JLabel("Amount");
        orderAmount.setBounds(100, 160, 100, 50);
        orderFrame.add(orderAmount);

        Spinner=new JSpinner();
        Spinner.setBounds(210, 175, 120, 20);
        orderFrame.add(Spinner);

        orderButton=new JButton("Add");
        orderButton.setBounds(260,205,70,20);
        orderButton.setActionCommand("Add");
        orderButton.setBackground(Color.cyan);
        orderButton.addActionListener(this);
        orderFrame.add(orderButton);

        orderFrame.setVisible(true);
    }

    void deleteOrderGUI(){
        orderFrame = new JFrame("Delete Order");
        orderFrame.setSize(500, 500);
        orderFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setLayout(null);

        ArrayList<OrderBackend> userOrders = new ArrayList<>();
        String username = LoginGui.username1;
        for (OrderBackend order : OrdersList) {
            if (order.customer.equals(username)) {
                userOrders.add(order);
            }
        }

        String[] columnNames = {"Customer Name","Item Name", "Item Amount","Item Price"};
        Object[][] rowData = new Object[userOrders.size()][4];

        for (int i = 0; i < userOrders.size(); i++) {
            rowData[i][0]=userOrders.get(i).customer;
            rowData[i][1] = userOrders.get(i).itemName;
            rowData[i][2] = userOrders.get(i).amount;
            rowData[i][3] = userOrders.get(i).price;
        }

        orderTableModel = new DefaultTableModel(rowData,columnNames);
        orderTable = new JTable(orderTableModel);
        JTableHeader Theader=orderTable.getTableHeader();
        Theader.setBackground(Color.cyan);
       // orderButton.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(orderTable);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setBounds(25,25,450,350);
        orderFrame.add(scroll);


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(275, 400, 150, 30);
        deleteButton.setBackground(Color.cyan);
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBackground(Color.cyan);
        backButton.setBounds(75,400,150,30);
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);

        orderFrame.add(deleteButton);
        orderFrame.add(backButton);
        orderFrame.setVisible(true);
    }

    void modifyOrderGUI(){
        orderFrame = new JFrame("Edit Order");
        orderFrame.setSize(500, 500);
        orderFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setLayout(null);

        ArrayList<OrderBackend> userOrders = new ArrayList<>();

        String username = LoginGui.username1;
        for (OrderBackend order : OrdersList) {
            if (order.customer.equals(username)) {
                userOrders.add(order);

            }
        }

        String[] columnNames = {"Customer Name","Item Name", "Item Amount","Item Price"};
        Object[][] rowData = new Object[userOrders.size()][4];

        for (int i = 0; i < userOrders.size(); i++) {
            rowData[i][0]=userOrders.get(i).customer;
            rowData[i][1] = userOrders.get(i).itemName;
            rowData[i][2] = userOrders.get(i).amount;
            rowData[i][3] = userOrders.get(i).price;
        }

        orderTableModel = new DefaultTableModel(rowData,columnNames);
        orderTable = new JTable(orderTableModel);
        JTableHeader Theader=orderTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(orderTable);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setBounds(25,25,450,350);
        orderFrame.add(scroll);


        editButton = new JButton("Edit");
        editButton.setBounds(275, 400, 150, 30);
        editButton.setActionCommand("Edit");
        editButton.setBackground(Color.cyan);
        editButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(75,400,150,30);
        backButton.setActionCommand("Back");
        backButton.setBackground(Color.cyan);
        backButton.addActionListener(this);

        orderFrame.add(editButton);
        orderFrame.add(backButton);
        orderFrame.setVisible(true);
    }

    void printReceipt(){
        receiptFrame=new JFrame("Print Receipt");
        receiptFrame.setSize(500, 500);
        receiptFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        receiptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receiptFrame.setLayout(null);

        ArrayList<OrderBackend> userOrders = new ArrayList<>();

        String username = LoginGui.username1;
        totalprice=0;
        for (OrderBackend order : OrdersList) {
            if (order.customer.equals(username)) {
                totalprice=order.price+totalprice;
                userOrders.add(order);
            }
        }
        receiptPrice=new JLabel("Total Price:");
        receiptPrice.setBounds(25,380,80,30);
        receiptFrame.add(receiptPrice);

        price=new JLabel(String.valueOf(totalprice));
        price.setBounds(100,380,80,30);
        receiptFrame.add(price);

        String[] columnNames = {"Customer Name","Item Name", "Item Amount","Item Price"};
        Object[][] rowData = new Object[userOrders.size()][4];

        for (int i = 0; i < userOrders.size(); i++) {
            rowData[i][0]=userOrders.get(i).customer;
            rowData[i][1] = userOrders.get(i).itemName;
            rowData[i][2] = userOrders.get(i).amount;
            rowData[i][3] = userOrders.get(i).price;
        }

        orderTableModel = new DefaultTableModel(rowData,columnNames);
        orderTable = new JTable(orderTableModel);
        JTableHeader Theader=orderTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(orderTable);
        scroll.setBounds(25,25,450,350);
        receiptFrame.add(scroll);

        backButton = new JButton("Back");
        backButton.setBounds(350,400,100,30);
        backButton.setActionCommand("Back1");
        backButton.setBackground(Color.cyan);
        backButton.addActionListener(this);

        receiptFrame.add(backButton);
        receiptFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command.equals("Add")){
            for(MenuBackend obj:MenuGUI.menuArrayList){
                String name=comboBox.getSelectedItem().toString();
                if(name.equals(obj.name)){

                    OrderBackend newOrder = new OrderBackend();
                    newOrder.customer=LoginGui.username1;
                    newOrder.itemName = name;
                    int amount= (int) Spinner.getValue();
                    newOrder.amount = amount;
                    int price=obj.price*amount;
                    newOrder.price = price;
                    newOrder.customer = LoginGui.username1;
                    OrdersList.add(newOrder);
                    JOptionPane.showMessageDialog(null,"Order Added");
                    orderFrame.dispose();

                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Order Denied");
            orderFrame.dispose();
            UserGUI.userFrame.setVisible(true);
        }
        else if(command.equals("Delete")){
            int selectedOrderRow = orderTable.getSelectedRow();

            if(selectedOrderRow != -1){
                String editedCustomerName=orderTable.getValueAt(selectedOrderRow,0).toString();
                int j=0;
                for(int i=0;i<OrderGUI.OrdersList.size();i++){
                    if (OrderGUI.OrdersList.get(i).customer.equals(editedCustomerName)){
                        j=i;
                        break;
                    }
                }
                OrdersList.remove(j+selectedOrderRow);
                JOptionPane.showMessageDialog(null,"Order Deleted");
            }
            else{
                JOptionPane.showMessageDialog(null,"Order Not Selected");
            }
            orderFrame.dispose();
            UserGUI.userFrame.setVisible(true);
        }
        else if(command.equals("Back")) {
            orderFrame.dispose();
            UserGUI.userFrame.setVisible(true);
        }
        else if(command.equals("Back1")) {
            receiptFrame.dispose();
            UserGUI.userFrame.setVisible(true);
        } else if(command.equals("Edit")){

            int selectedOrderRow = orderTable.getSelectedRow();
            if(selectedOrderRow != -1){
                try {
                    String editedCustomerName=orderTable.getValueAt(selectedOrderRow,0).toString();
                    String editedItemName = orderTable.getValueAt(selectedOrderRow, 1).toString();
                    int editedAmount = Integer.parseInt(orderTable.getValueAt(selectedOrderRow, 2).toString());

                    int j=0;

                    for(int i=0;i<OrderGUI.OrdersList.size();i++){
                        if (OrderGUI.OrdersList.get(i).customer.equals(editedCustomerName)){
                            j=i;
                            break;
                        }
                    }

                    OrderBackend editedObj = OrdersList.get(j+selectedOrderRow);
                    boolean itemFound = false;
                    for(MenuBackend obj:MenuGUI.menuArrayList) {
                        if (obj.name.equals(editedItemName)) {
                            editedObj.customer=editedCustomerName;
                            editedObj.itemName = editedItemName;
                            editedObj.amount = editedAmount;
                            editedObj.price = editedAmount * obj.price;
                            itemFound = true;
                            break;
                        }}

                    if (itemFound) {
                        JOptionPane.showMessageDialog(null, "Order Edited");
                    } else {
                        JOptionPane.showMessageDialog(null, "Item not found in menu");
                    }


                }catch(NumberFormatException l){
                    JOptionPane.showMessageDialog(null,"Invalid input for amount or price");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"No Order selected to Edit");
            }
            orderFrame.dispose();
            UserGUI.userFrame.setVisible(true);
        }
    }
}




