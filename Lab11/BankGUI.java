import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankGUI extends JFrame implements ActionListener {
    public JRadioButton rb1=new JRadioButton("Deposit");
    public JRadioButton rb2=new JRadioButton("Withdrawal");
    public JRadioButton rb3=new JRadioButton("Miscellaneous");
    ButtonGroup bg=new ButtonGroup();
    JFrame frame = new JFrame();
    public JLabel lblAmount= new JLabel("Amount:");
    public JTextField fieldAmount= new JTextField(4);
    public JLabel lblType=new JLabel("Description");
    public JTextField fieldDescription=new JTextField(3);
    public JButton save=new JButton("Save");
    int balance=0;
    public JLabel lblTotal=new JLabel(Integer.toString(balance));
    public JTextArea transactionList = new JTextArea(10, 30);

    public static void main (String args[]){
        new BankGUI();
    }
    BankGUI(){
        JPanel p =new JPanel();
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(rb1);
        radioPanel.add(rb2);
        radioPanel.add(rb3);
        p.add(radioPanel);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        save.addActionListener(this);
        frame.getContentPane().add(new JScrollPane(p));
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        p.add(rb1);
        p.add(rb2);
        p.add(rb3);
        p.add(lblAmount);
        p.add(fieldAmount);
        p.add(lblType);
        p.add(fieldDescription);
        p.add(save);
        p.add(lblTotal);
        p.add(new JLabel("Transaction List:"));
        JScrollPane scrollPane = new JScrollPane(transactionList);
        p.add(scrollPane);
        add(p);
        
        this.setTitle("Bank Transactions");
        this.setSize(800, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }
    public void actionPerformed(ActionEvent e){
        try{
            int amount = Integer.parseInt(fieldAmount.getText());
            
            String description=fieldDescription.getText();
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            
            if(rb1.isSelected()){
                if(amount<=0){
                    throw new IllegalArgumentException("Transaction Value Must Be Greater Than 0");
                }
                balance += amount; // Deposit
                transactionList.insert("\n" + formattedDateTime + "  " + "Deposit:"+description + "  " + "$"+amount, 0);
        }
            else if(rb2.isSelected()){
                if(amount<=0){
                    throw new IllegalArgumentException("Transaction Value Must Be Greater Than 0");
                }
                if(amount>balance){
                    throw new IllegalArgumentException("Insufficient Funds");
                }
                balance -= amount; // Withdrawal
                transactionList.insert("\n" + formattedDateTime + "  " + "Withdrawal:"+description + "  " + "$"+amount, 0);
        }
            else if(rb3.isSelected()){
                balance += amount; //fees/other
                transactionList.insert("\n" + formattedDateTime + "  " + "Misc:"+description + "  " + "$"+amount, 0);
            }
            else{
                transactionList.insert("\n"+"Error-Select a Transaction option",0);
            }
    
            
    
            lblTotal.setText(Integer.toString(balance));
        }
        catch(IllegalArgumentException ex){
            transactionList.insert("\n"+"Error-Invalid Transaction",0);
        }
    }
}

