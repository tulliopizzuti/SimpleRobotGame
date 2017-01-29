package p2_project_2016.unisa.ui.main.visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.interfaces.Readable;

import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

/**
 * Interfaccia grafica utilizzata per visualizzare ogni elemento e i suoi attributi
 * presenti nel campo
 * @author tullio
 */
public class Visual extends JFrame {
	private static final long serialVersionUID = 3569545958133088167L;
	
	private ArrayList<Insertable> element;
	private JTextArea text;
	/**
	 * Instanzia un nuovo visualizzatore di proprietà degli elementi
	 * @param e ArrayList Insertable lista di elementi inseriti nel field
	 */
	public Visual(ArrayList<Insertable> e){
		this.setName("Visualizzatore di Elementi");
		this.setSize(700,500);
		this.element=e;
		
		this.add(setTextArea(),BorderLayout.CENTER);
		this.add(setInputField(),BorderLayout.SOUTH);
	}
	/**
	 * Metodo usato per costruire una textArea Scrollabile
	 * @return JScrollPane area di testo dove vengono stampati gli elementi del gioco
	 */
	public JScrollPane setTextArea(){
		text=new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		text.setEditable(false);
		return scroll;
	}
	/**
	 * Restituisce un pannello contente i campi e i bottoni usati per leggere un elemento
	 * @return JPanel 
	 */
	public JPanel setInputField(){
		JPanel panel=new JPanel();
		JLabel xLabel=new JLabel("X:");
		JTextField xField=new JTextField("");
		JLabel yLabel=new JLabel("Y:");
		JTextField yField=new JTextField("");
		panel.setLayout(new GridLayout(2,3));
		panel.add(xLabel);
		panel.add(xField);
		JButton cercaTutti = new JButton("Cerca tutti");
		cercaTutti.addActionListener((e)->{text.setText("");
										for(Insertable i:element)
											text.append(((Readable) i).read());
										});
		panel.add(cercaTutti);
		panel.add(yLabel);
		panel.add(yField);
		JButton bottone=new JButton("Cerca");
		class ButtonClick implements ActionListener{
			public void actionPerformed(ActionEvent arg0)  {
				text.setText("");
				int x;
				int y;
				try{
					x=Integer.parseInt(xField.getText());
					y=Integer.parseInt(yField.getText());
					Position p = new Position(x,y);
					for(Insertable i:element){
						if(p.equals(i.getPosition()))
							text.append(((Readable) i).read());
					}
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null,"Inserisci valori corretti");
				}
				catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null,"Inserisci valori corretti");
				}
			}
		}
		ActionListener bottoneListener=new ButtonClick();
		bottone.addActionListener(bottoneListener);
		panel.add(bottone, BorderLayout.EAST);
		return panel;
	}
	/**
	 * Metodo utilizzato per stampare nella textField 
	 * le proprietà di tutti gli elementi
	 */
	public void printAll(){
		text.setText("");
		for(Insertable i:element){
			text.append(((Readable) i).read());
		}
	}
	
}
