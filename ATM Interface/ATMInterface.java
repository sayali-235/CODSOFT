import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class ATMInterface extends JFrame {
    private double balance = 1000.0;
    private int pin = 1234;
    Container c;
    private JLabel balanceLabel;
    private JLabel pinLabel, amountLabel;
    private JTextField pinField, amountField;
    private JButton checkBalanceButton, withdrawButton, depositButton;

    public ATMInterface() {
          c=getContentPane();
        c.setLayout(null);
         Font f=new Font("Times new Roman",Font.BOLD,30);
        setTitle("ATM Interface");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered layout with horizontal gap of 20 and vertical gap of 10

        // Customize background color
        getContentPane().setBackground(new Color(240, 240, 240));

        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setFont(f);
        pinLabel = new JLabel("Enter PIN:");
        pinLabel.setFont(f);
        amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(f);

        pinField = new JTextField("1234", 10);
        pinField.setFont(f);
        amountField = new JTextField("0.0", 10);
        amountField.setFont(f);
         
       balanceLabel.setBounds(20,20,400,40);
       pinLabel.setBounds(20,120,400,40);
       pinField.setBounds(420,120,400,40);         
       amountLabel.setBounds(20,220,400,40);   
       amountField.setBounds(420,220,400,40);   
        checkBalanceButton = new JButton("Check Balance");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");

        // Customize button font and size
        checkBalanceButton.setFont(f);
        withdrawButton.setFont(f);
        depositButton.setFont(f);

      checkBalanceButton.setBounds(320,320,400,40);
      withdrawButton.setBounds(320,400,400,40);
     depositButton.setBounds(320,480,400,40);
        add(balanceLabel);
        add(pinLabel);
        add(pinField);
        add(amountLabel);
        add(amountField);
        add(checkBalanceButton);
        add(withdrawButton);
        add(depositButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validatePin()) {
                    JOptionPane.showMessageDialog(null, "Your balance is: $" + balance);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validatePin()) {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        balanceLabel.setText("Balance: $" + balance);
                        JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid withdrawal amount.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validatePin()) {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        balance += amount;
                        balanceLabel.setText("Balance: $" + balance);
                        JOptionPane.showMessageDialog(null, "Deposit successful.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN.");
                }
            }
        });

        setVisible(true);
    }

    private boolean validatePin() {
        int enteredPin;
        try {
            enteredPin = Integer.parseInt(pinField.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return enteredPin == pin;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMInterface();
            }
        });
    }
}
