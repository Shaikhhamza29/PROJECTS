import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Expenses extends JFrame {

    String url, sqlstat;
    Statement smt;
    PreparedStatement ps;
    ResultSet rset;
    Connection conn;                    
    String line = "**************************************";

    JDBC obj = new JDBC();
    private DefaultListModel<String> expenseListModel;
    private JList<String> expenseList;

    public Expenses() {
        setVisible(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        expenseListModel = new DefaultListModel<>();
        expenseList = new JList<>(expenseListModel);
        JScrollPane scrollPane = new JScrollPane(expenseList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Expense");
        JButton displayButton = new JButton("DISPLAY EXPENSES");

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayexpenses();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addexpenses();
            }
        });
        panel.add(addButton, BorderLayout.NORTH);
        panel.add(displayButton, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    private void addexpenses() {

        final JDialog addExpenseDialog = new JDialog(this, "Add Expense", true);
        addExpenseDialog.setSize(300, 200);
        addExpenseDialog.setLayout(new GridLayout(4, 2));

        JLabel descriptionLabel = new JLabel("Description:");
        final JTextField descriptionField = new JTextField();
        addExpenseDialog.add(descriptionLabel);
        addExpenseDialog.add(descriptionField);

        JLabel amountLabel = new JLabel("Amount:");
        final JTextField amountField = new JTextField();
        addExpenseDialog.add(amountLabel);
        addExpenseDialog.add(amountField);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date date = new java.util.Date();
                String description = descriptionField.getText();
                String amount = amountField.getText();
                obj.Insert(date, description, amount);

                if (!description.isEmpty() && !amount.isEmpty()) {
                    String expenseEntry = date + "  " + description + "  $ " + amount;

                    expenseListModel.addElement(expenseEntry);
                    expenseListModel.addElement(line);
                    addExpenseDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addExpenseDialog, "Please fill in all fields.");
                }
            }
        });

        addExpenseDialog.add(addButton);
        addExpenseDialog.setVisible(true);
    }

    private void displayexpenses() {

        final JDialog addExpenseDialog = new JDialog(this, "DIsplay expenses", true);
        addExpenseDialog.setSize(300, 200);
        addExpenseDialog.setLayout(new GridLayout(4, 2));

        JLabel startdate = new JLabel("START-DATE:");
        final JTextField starTextField = new JTextField();
        addExpenseDialog.add(startdate);
        addExpenseDialog.add(starTextField);

        JLabel end_date = new JLabel("END-DATE:");
        final JTextField end_date_Field = new JTextField();
        addExpenseDialog.add(end_date);
        addExpenseDialog.add(end_date_Field);

        JButton addButton = new JButton("DISPLAY");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String start_date = starTextField.getText();
                String end_date = end_date_Field.getText();
                
                if (!start_date.isEmpty() && !end_date.isEmpty()) {
                    try {
                        int total = 0;
                        url = "jdbc:mysql://localhost:3306/firstDB";
                        conn = DriverManager.getConnection(url, "root", "root");

                        sqlstat = "Select *from expenses where date between ? and ?";

                        ps = conn.prepareStatement(sqlstat);
                        // ps = smt.execute(sqlstat);
                        // System.out.println(b);
                        ps.setString(1, start_date);
                        ps.setString(2, end_date);

                        rset = ps.executeQuery();
                        while (rset.next()) {
                            java.sql.Date date;
                            int smarks;

                            
                            date = rset.getDate(1);
                            String strname = rset.getString(2);
                            smarks = rset.getInt(3);
                            // System.out.println(date + "\t" + strname + "\t" + smarks);
                            expenseListModel.addElement(date + "    " + strname + "    " + smarks);
                            total  = total + smarks;
                            expenseListModel.addElement(line);
                            addExpenseDialog.dispose();


                        }
                        expenseListModel.addElement("TOTAL                 " + Integer.toString(total));
                        expenseListModel.addElement(line);
                        addExpenseDialog.dispose();
                        System.out.println("\nDATA RETRIVE SUCCESSFULLY \n");
                        smt.close();
                        conn.close();
                    } catch (Exception Ec) {
                        System.out.println();
                    }

                } else if (!start_date.isEmpty()) {

                    try {   
                         int total = 0;
                        url = "jdbc:mysql://localhost:3306/firstDB";
                        conn = DriverManager.getConnection(url, "root", "root");

                        sqlstat = "Select *from expenses where date between ? and now()";

                        ps = conn.prepareStatement(sqlstat);
                        // ps = smt.execute(sqlstat);
                        // System.out.println(b);
                        ps.setString(1, start_date);
                        // ps.setString(2, end_date);

                        rset = ps.executeQuery();
                        while (rset.next()) {
                            java.sql.Date date;
                            int smarks;
                           
                            date = rset.getDate(1);
                            String strname = rset.getString(2);
                            smarks = rset.getInt(3);
                            // System.out.println(date + "\t" + strname + "\t" + smarks);
                            total = total + smarks;

                            expenseListModel.addElement(date + "    " + strname + "    " + smarks);
                            expenseListModel.addElement(line);
                            addExpenseDialog.dispose();

                        }                       
                        expenseListModel.addElement("TOTAL                 " + Integer.toString(total));
                        expenseListModel.addElement(line);
                        addExpenseDialog.dispose();

                        System.out.println("\nDATA RETRIVE SUCCESSFULLY \n");
                        smt.close();
                        conn.close();
                    } catch (Exception Ec) {
                        System.out.println();
                    }

                } else {
                    JOptionPane.showMessageDialog(addExpenseDialog, "Please fill in all fields.");
                }
            }
        });

        addExpenseDialog.add(addButton);
        addExpenseDialog.setVisible(true);
    }

}