import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI implements ActionListener {
    static JFrame adminFrame;
    static JFrame orderListFrame;
    static JFrame staffAssignFrame;

    JButton checkOrderList;
    JButton assignStaffAssignments;
    JButton editMenu, backButton;
    JButton logout;

    JTable orderListTable,staffAssignTable;
    DefaultTableModel orderListTableModel,staffAssignTableModel;

    JComboBox assignStaffComboBox;
    MenuGUI menuGui=new MenuGUI();

    void adminGui() {
        menuGui.adminMenuGUI();

        MenuGUI.menuGuiFrame.setVisible(false);
        adminFrame = new JFrame("AdminPage");
        adminFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        adminFrame.setLayout(null);
        adminFrame.setSize(500, 500);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        checkOrderList = new JButton("Check OrderList");
        checkOrderList.setBounds(150, 100, 200, 40);
        checkOrderList.setActionCommand("check orderlist");
        checkOrderList.setBackground(Color.cyan);
        checkOrderList.addActionListener(this);
        checkOrderList.setFocusable(false);
        adminFrame.add(checkOrderList);

        assignStaffAssignments = new JButton("Assign Staff Assignments");
        assignStaffAssignments.setBounds(150, 150, 200, 40);
        assignStaffAssignments.setActionCommand("Assign Staff");
        assignStaffAssignments.setBackground(Color.cyan);
        assignStaffAssignments.addActionListener(this);
        assignStaffAssignments.setFocusable(false);
        adminFrame.add(assignStaffAssignments);

        editMenu = new JButton("Menu");
        editMenu.setBounds(150, 200, 200, 40);
        editMenu.setActionCommand("menu");
        editMenu.setBackground(Color.cyan);
        editMenu.addActionListener(this);
        editMenu.setFocusable(false);
        adminFrame.add(editMenu);

        logout = new JButton("Logout");
        logout.setBounds(150, 250, 200, 40);
        logout.setActionCommand("logout");
        logout.setBackground(Color.cyan);
        logout.addActionListener(this);
        logout.setFocusable(false);
        adminFrame.add(logout);

        adminFrame.setVisible(false);
    }
    void  checkOrderList(){
        orderListFrame= new JFrame("Check OrderList");
        orderListFrame.setSize(500, 500);
        orderListFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        orderListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderListFrame.setLayout(null);

        String[] columnNames = {"Customer Name","Item Name", "Item Amount","Item Price"};
        Object[][] rowData = new Object[OrderGUI.OrdersList.size()][4];

        for (int i = 0; i < OrderGUI.OrdersList.size(); i++) {
            rowData[i][0]=OrderGUI.OrdersList.get(i).customer;
            rowData[i][1] = OrderGUI.OrdersList.get(i).itemName;
            rowData[i][2] = OrderGUI.OrdersList.get(i).amount;
            rowData[i][3] = OrderGUI.OrdersList.get(i).price;
        }

        orderListTableModel = new DefaultTableModel(rowData,columnNames);
        orderListTable = new JTable(orderListTableModel);
        JTableHeader Theader=orderListTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(orderListTable);
        orderListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setBounds(25,25,450,350);

        orderListFrame.add(scroll);
        orderListFrame.setVisible(true);

    }

    void assignStaffAssignments(){
        staffAssignFrame= new JFrame("Staff Assignment");
        staffAssignFrame.setSize(500, 500);
        staffAssignFrame.getContentPane().setBackground(Color.decode("#D6EFFF"));
        staffAssignFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staffAssignFrame.setLayout(null);

        String[] columnNames = {"Customer Name","Staff Assigned"};
        Object[][] rowData = new Object[OrderGUI.OrdersList.size()][2];

        String[] staff = {"John","Jack","Abraham","Ali","Ahmed","Talha"};
        assignStaffComboBox = new JComboBox<String>(staff);

        for (int i = 0; i < OrderGUI.OrdersList.size(); i++) {
            rowData[i][0]=OrderGUI.OrdersList.get(i).customer;
            rowData[i][1] = OrderGUI.OrdersList.get(i).staffAssigned;
        }

        staffAssignTableModel = new DefaultTableModel(rowData,columnNames);
        staffAssignTable = new JTable(staffAssignTableModel);
        JTableHeader Theader=staffAssignTable.getTableHeader();
        Theader.setBackground(Color.cyan);
        JScrollPane scroll = new JScrollPane(staffAssignTable);
        staffAssignTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setBounds(25,25,450,350);
        TableColumn col = staffAssignTable.getColumnModel().getColumn(1);
        col.setCellEditor(new DefaultCellEditor(assignStaffComboBox));
        assignStaffComboBox.setActionCommand("SetComboBoxValue");
        assignStaffComboBox.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(75,400,150,30);
        backButton.setBackground(Color.cyan);
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);

        staffAssignFrame.add(backButton);
        staffAssignFrame.add(scroll);
        staffAssignFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command.equals("logout"))
        {
            adminFrame.setVisible(false);
            LoginGui.frame.setVisible(true);

        }
        else if(command.equals("menu")) {
            adminFrame.setVisible(false);
            MenuGUI.menuGuiFrame.setVisible(true);
        } else if (command.equals("check orderlist")) {
            checkOrderList();
        }
        else if(command.equals("Back")) {
            staffAssignFrame.setVisible(false);
            adminFrame.setVisible(true);
        }
        else if (command.equals("Assign Staff")) {
            assignStaffAssignments();
        }
        else if(command.equals("SetComboBoxValue")){
            int row = staffAssignTable.getSelectedRow();

            if (row != -1) {
                staffAssignTableModel.setValueAt(assignStaffComboBox.getSelectedItem(), row, 1);
                OrderGUI.OrdersList.get(row).staffAssigned = (String) staffAssignTableModel.getValueAt(row,1);
            }
        }

    }
}
