package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import vue.ChoixArene; 

import modele.Mur;
import outils.son.Son;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class Arene extends JFrame implements Global {

	private JPanel contentPane;
	private JTextField txtSaisie;
	private JPanel jpnMurs ;
	private JPanel jpnJeu ;
	private JLabel lblFond ;
	private boolean client ; 
	private Controle controle ;
	private JTextArea txtChat;
	private Son [] lessons = new Son[SON.length];


	/**
	 * Create the frame.
	 */
	public Arene(String typeJeu, Controle controle ) {

		if(typeJeu == "client") {
			client = true ;
		} else {
			client=false;
		}

		this.controle = controle ;
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, L_ARENE+3*MARGE,H_ARENE + H_CHAT);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		if (typeJeu=="client"){

			contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					contentPane_keyPressed(arg0);
				}

			});
		}


		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(jpnJeu);
		jpnJeu.setLayout(null);
		jpnJeu.setOpaque(false);
		
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(jpnMurs);
		jpnMurs.setLayout(null);
		jpnMurs.setOpaque(false);
		

		lblFond = new JLabel("");
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE);
		
		contentPane.add(lblFond);
		jpnMurs.add(lblFond);
		lblFond.setIcon(new ImageIcon(FONDARENE+ChoixArene.getNumArene()+JPG));
		
		
		if (typeJeu=="client"){

			txtSaisie = new JTextField();
			txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);


			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					txtSaisie_keyPressed(arg0);
				}

			});
		}

		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds(0, H_ARENE+H_SAISIE, L_ARENE, H_CHAT-H_SAISIE-7*MARGE);
		contentPane.add(jspChat);
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);

		if (client){
			(new Son (SONAMBIANCE)).playContinue();
			for(int k=0;k<SON.length;k++){
				lessons[k] = new Son(CHEMINSONS+SON[k]);
			}
		}
		(new Son(SONAMBIANCE)).playContinue();



	}

	public JLabel getLblFond() {
		return lblFond;
	}

	public void setLblFond(JLabel lblFond) {
		this.lblFond = lblFond;
	}

	public void JoueSon (Integer numSon){
		lessons[numSon].play();

	}





	private void  contentPane_keyPressed(KeyEvent arg0) {
		int valeur = -1;
		switch (arg0.getKeyCode()){

		case KeyEvent.VK_UP  : 
			valeur = HAUT  ;
			break ; 

		case KeyEvent.VK_DOWN : 
			valeur = BAS ; 
			break ;
		case KeyEvent.VK_RIGHT : 
			valeur = DROITE ;
			break ; 
		case KeyEvent.VK_LEFT:
			valeur = GAUCHE ;
			break;
		case KeyEvent.VK_SPACE : 
			valeur = TIR ; 
			break; 

		}
		if (valeur != -1){
			controle.evenementVue(this , ACTION+SEPARE+valeur);
		}

	}

	private void txtSaisie_keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()== KeyEvent.VK_ENTER){

			if(txtSaisie.getText() != ""){
				controle.evenementVue(this, CHAT+SEPARE+txtSaisie.getText());
				txtSaisie.setText("");	

			}
			contentPane.requestFocus();
		}
	}
	/**
	 * @return the jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	public void  ajoutMur (JLabel unMur){



		jpnMurs.add(unMur);
		jpnMurs.repaint();


	}
	public void ajoutPanelMurs(JPanel unPanel){

		jpnMurs.add(unPanel);
		jpnMurs.repaint();
		contentPane.requestFocus();
		lblFond.setIcon(new ImageIcon(FONDARENE+ChoixArene.getNumArene()+JPG));

	}

	public void ajoutJoueur (JLabel unJoueur){

		jpnJeu.add(unJoueur);
		jpnJeu.repaint();

	}

	public void ajoutModifJoueur (int num , JLabel unLabel){

		try {
			jpnJeu.remove(num);

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		jpnJeu.add(unLabel, num) ; 
		jpnJeu.repaint();
		//System.out.println(jpnJeu);
	}
	public void ajoutChat(String unePhrase){

		txtChat.setText(unePhrase+"\r\n"+txtChat.getText());
	}

	public String getTxtChat() {
		return txtChat.getText();
	}


	public void remplaceChat (String remplace){
		txtChat.setText(remplace);
	}








}
