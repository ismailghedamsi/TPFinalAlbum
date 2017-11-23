package cal.tpCertificatif.bean;

/**
 * Classe represantant une session d'apprentissage
 * @author small44
 *
 */
public class LearningSession {
	private AbstractUser user;
	private Deck deck;
	
	public LearningSession(AbstractUser user, Deck deck) {
		super();
		this.user = user;
		this.deck = deck;
	}
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser user) {
		this.user = user;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	
	
}
