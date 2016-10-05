package modele;

import java.util.ArrayList;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

public class JeuServeur extends Jeu implements Global{
		
	
	
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	
	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void reception(Connection connection, Object info) {
		
		System.out.println(info);
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	public void constructionMurs (){
		int i ;
		for (i=0 ; i <= NBMURS ; i++){
			lesMurs.add(new Mur());
			
			controle.evenementModele(this , "ajout Mur" ,lesMurs.get(i).getLabel().getjLabel()); // on recup le mur i puis on passe par Label pour getjLabel
		}
	}
	
	
	
	
	//Constructeur
	public JeuServeur (Controle controle){
		super.controle = controle ;
		 Label.setNbLabel(0);
		
	}

}
