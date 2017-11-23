package cal.tpCertificatif.bean;

import com.rmtheis.yandtran.language.Language;

import cal.tpCertificatif.service.ServiceOperation;

/**
 * Classe representant la pancarte
 * @author small44
 *
 */
public class FlashCard {
	private static int counter;
	private int idFlashCard;
	private String word;
	private String translation;
	private Language wordLanguage;
	private Language translationLanguage; // Translating from yandex api to prevent errors
	
	
	public FlashCard(String word, Language wordLanguage, Language translationLanguage) {
		this.word = word;
		this.wordLanguage = wordLanguage;
		this.translationLanguage = translationLanguage;
		try {
			this.translation = ServiceOperation.translateWord(word, wordLanguage, translationLanguage);
		} catch (Exception e) {
			
		}
		idFlashCard = counter;
		counter++;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
    
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		FlashCard.counter = counter;
	}
	public Language getWordLanguage() {
		return wordLanguage;
	}
	public void setWordLanguage(Language wordLanguage) {
		this.wordLanguage = wordLanguage;
	}
	public Language getTranslationLanguage() {
		return translationLanguage;
	}
	public void setTranslationLanguage(Language translationLanguage) {
		this.translationLanguage = translationLanguage;
	}
	public int getIdFlashCard() {
		return idFlashCard;
	}
	@Override
	public String toString() {
		return "FlashCard [word=" + word + ", translation=" + translation + "]";
	}
	
	
	
	
}
