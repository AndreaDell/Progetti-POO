package valutaEspressione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


import javax.swing.*;
import javax.swing.border.Border;

class FinestraGUI extends JFrame {
		private static final long serialVersionUID = 1L;
		
	   public FinestraGUI(){
		   
	      setTitle("Valutatore Espressioni");
	      this.setBounds(400, 200, 600, 600);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Border border=BorderFactory.createLineBorder(Color.black, 5);
	      JPanel panel=new JPanel(new BorderLayout()); //pannello generale
	      panel.setBackground(Color.red);
	      panel.setBorder(border);
	      this.add(panel);
	      //inizio parte superiore -- titolo
	      JLabel titolo= new JLabel();
	      titolo.setText("VALUTATORE ESPRESSIONI");
	      titolo.setBorder(border);
	      titolo.setBackground(Color.black);
	      titolo.setForeground(Color.white);
	      titolo.setVerticalAlignment(JLabel.CENTER);
	      titolo.setHorizontalAlignment(JLabel.CENTER);
	      titolo.setOpaque(true);
	      titolo.setFont(new Font("MV Boli",Font.PLAIN,20));
	      panel.add(titolo,BorderLayout.NORTH);
	      //inizio textField
	      Panel panelField= new Panel(); // pannello che contiene il textField
	      panelField.setBackground(Color.red);
	      JTextField field= new JTextField();
	      field.setBackground(Color.black);
	      field.setFont(new Font("MV Boli",Font.PLAIN,15));
	      field.setBorder(border);
	      field.setForeground(Color.white);
	      field.setColumns(19);
	      JLabel ds= new JLabel("Inserisci qui l'espressione -->",JLabel.RIGHT);  //descrizione
	      ds.setFont(new Font("MV Boli",Font.PLAIN,15));
	      ds.setForeground(Color.black);
	      panelField.add(ds);
	      panelField.add(field);
	      panel.add(panelField,BorderLayout.WEST);
	      //inizio JButton
	      JPanel butPanel= new JPanel(new BorderLayout());
	      butPanel.setBackground(Color.green);
	      JButton button= new JButton("Calcola il valore!");
	      button.setBackground(Color.white);
	      button.setFont(new Font("MV Boli",Font.PLAIN,20));
	      button.setBorder(border);
	      button.setForeground(Color.black);
	      butPanel.add(button,BorderLayout.NORTH);
	      //JLabel per il risultato
	      JLabel ris= new JLabel("Clicca per visualizzare il risultato");
	      ris.setBorder(border);
	      ris.setBackground(Color.black);
	      ris.setForeground(Color.white);
	      ris.setVerticalAlignment(JLabel.CENTER);
	      ris.setHorizontalAlignment(JLabel.CENTER);
	      ris.setOpaque(true);
	      ris.setFont(new Font("MV Boli",Font.PLAIN,20));
	      button.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	            	try {
	            	ValutaExpr v= new ValutaExpr(field.getText());
	            	ris.setText(String.valueOf(v.risolvi()));
	            	}catch(Exception e) {
	            		JOptionPane.showMessageDialog(null,"Stringa inserita non corretta!","Errore", JOptionPane.ERROR_MESSAGE);
	            	}//catch
	            }
	        });//lambda expression per l'action listener
	      butPanel.add(ris,BorderLayout.SOUTH);
	      panel.add(butPanel,BorderLayout.SOUTH);
	      pack();
	      setLocation(500,200);
	      this.setSize(500,250);
	      this.setVisible(true);
	   }//costruttore
	   
}//FinestraGUI


public class ValutaExprGUI  {
	
	public static void main(String[] args) {
		new FinestraGUI();
	}//main

}//ValutaExprGUI
