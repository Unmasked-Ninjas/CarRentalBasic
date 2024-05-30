package CarRental;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class GUIRental extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField dayss;
    private JLabel ConfirmationCard;
    private int days;
    private JLabel returnStatement;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIRental frame = new GUIRental();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GUIRental() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 986, 511);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Puntu Rentals");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(341, 11, 423, 78);
        contentPane.add(lblNewLabel);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Ferrari Spyder");
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        chckbxNewCheckBox.setBounds(46, 181, 203, 55);
        contentPane.add(chckbxNewCheckBox);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Buggati Chiron");
        chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        chckbxNewCheckBox_1.setBounds(46, 239, 191, 43);
        contentPane.add(chckbxNewCheckBox_1);

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Lamborghini Hurrican");
        chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        chckbxNewCheckBox_2.setBounds(46, 285, 311, 43);
        contentPane.add(chckbxNewCheckBox_2);

        makesingleselection(chckbxNewCheckBox, chckbxNewCheckBox_1, chckbxNewCheckBox_2);
        
        ConfirmationCard = new JLabel("");
        ConfirmationCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ConfirmationCard.setBounds(543, 162, 419, 119);
        contentPane.add(ConfirmationCard);

        dayss = new JTextField();
        dayss.setBounds(256, 114, 239, 55);
        contentPane.add(dayss);
        dayss.setColumns(10);

        dayss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    days = Integer.parseInt(dayss.getText());
                } catch (NumberFormatException ex) {
                    days = 0;
                }
            }
        });

       

        JLabel lblNewLabel_1 = new JLabel("Number of Days");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(46, 121, 240, 28);
        contentPane.add(lblNewLabel_1);

        JButton Yes = new JButton("Yes");
        Yes.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Yes.setBounds(543, 335, 111, 46);
        contentPane.add(Yes);
        Yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				returnStatement.setText("Please return in "+days+" days");
			}
        	
        });

        JButton No = new JButton("No");
        No.setFont(new Font("Tahoma", Font.PLAIN, 20));
        No.setBounds(664, 337, 100, 44);
        contentPane.add(No);
        No.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(returnStatement.getText() == "") {
					returnStatement.setText("Rental Failed");
				}
				else {
					
				}
				
			}
        	
        });

        returnStatement = new JLabel("");
        returnStatement.setFont(new Font("Tahoma", Font.PLAIN, 20));
        returnStatement.setBounds(256, 392, 448, 71);
        contentPane.add(returnStatement);
        
        JLabel lblNewLabel_2 = new JLabel("Confirm Rental?");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel_2.setBounds(553, 291, 211, 28);
        contentPane.add(lblNewLabel_2);
    }

   
    private void makesingleselection(JCheckBox chckbxNewCheckBox, JCheckBox chckbxNewCheckBox_1, JCheckBox chckbxNewCheckBox_2) {
        JCheckBox[] checkBoxes = {chckbxNewCheckBox, chckbxNewCheckBox_1, chckbxNewCheckBox_2};

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox selected = (JCheckBox) e.getSource();
                if (selected.isSelected()) {
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox != selected) {
                            checkBox.setSelected(false);
                        }
                    }
                    if (selected == chckbxNewCheckBox) {
                    	ConfirmationCard.setText("You need to pay $" + days * 5000+" for "+days+ " days. For this ferrari");
                    } else if (selected == chckbxNewCheckBox_1) {
                    	ConfirmationCard.setText("You need to pay $" + days * 8000+" for "+days+ " days. For this Buggati");
                    } else if (selected == chckbxNewCheckBox_2) {
                    	ConfirmationCard.setText("You need to pay $" + days * 3000+" for "+days+ " days. For this lambo");
                    }
                    returnStatement.setText(" ");
                    
                }
                dayss.setText(null);
            }
        };

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.addActionListener(listener);
        }
    }
}
