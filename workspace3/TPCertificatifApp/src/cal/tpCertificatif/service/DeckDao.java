package cal.tpCertificatif.service;

import java.util.List;

import cal.tpCertificatif.bean.FlashCard;
import cal.tpCertificatif.util.DAOUtil;

public class DeckDao implements DAOUtil<FlashCard>{
	
	/**
	 * Recherche d'une pancarte avec son idantifiant
	 */
	@Override
	public FlashCard getElementsById(List<FlashCard> list, int id) {
		for(FlashCard flashcard : list) {
			if(flashcard.getIdFlashCard() == id) {
				return flashcard;
			}
		}
		return null;
			   
	}

	
	/**
	 * Ajout d'une pancarte dans un Deck
	 */
	@Override
	public boolean add(List<FlashCard> list, FlashCard element) {
		return list.add(element);
	}
	
	

	/**
	 * Supprimer une pancard du deck par son id
	 */
	@Override
	public boolean deleteById(List<FlashCard> list, int id) {
		for(FlashCard flashcard : list) {
			if(flashcard.getIdFlashCard() == id) {
				return list.remove(flashcard);
			}
		}
		return false;
	}


	/**
	 * Recuperer une pancarte aleatoire
	 * @return la pancarte souhaite
	 */
	@Override
	public FlashCard getRandomElement(List<FlashCard> list) {
		int randomIndex = (int) (Math.random()*list.size());
		return list.get(randomIndex);
	}

}
