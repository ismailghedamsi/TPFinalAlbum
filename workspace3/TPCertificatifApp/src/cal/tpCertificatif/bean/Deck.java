package cal.tpCertificatif.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe represantant un Deck qui est une collection de pantcarte (Flashcard)
 * @author small44
 *
 */
public class Deck {
	private static int counter;
	private int idDeck;
	private List<FlashCard> flashCardList;


	public Deck() {
		flashCardList = new ArrayList<>();
		idDeck = counter;
		counter++;
	}

	public List<FlashCard> getFlashCardList() {
		return flashCardList;
	}

	public void setFlashCardList(List<FlashCard> flashCardList) {
		this.flashCardList = flashCardList;
	}
	
	
	
	
}
