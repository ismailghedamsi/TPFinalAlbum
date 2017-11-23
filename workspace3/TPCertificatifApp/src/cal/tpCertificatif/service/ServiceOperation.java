package cal.tpCertificatif.service;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import cal.tpCertificatif.bean.FlashCard;

/**
 * Classe service pour generer la session
 * @author small44
 *
 */
public class ServiceOperation {
	/**
	 * 
	 * @param word
	 * @param wordLanguage
	 * @param translateWord
	 * @return la traduction du mot a la langue souhaitee
	 * @throws Exception
	 */
	public static String translateWord(String word,Language wordLanguage,Language translateWord) throws Exception {
		// Cle pour utiliser l'api
	  Translate.setKey("trnsl.1.1.20171105T033144Z.9d69b029915c4ebb.74471b753cf5248958e3569a35e79f2d0d70ff96");
	  	
        return Translate.execute(word, Language.SPANISH, Language.FRENCH); // recupere la trauction
	}
	
	/**
	 * 
	 * @param currentFlashBack
	 * @param answer
	 * @return Si l'tulisateur a eu une bonne reponse ou non
	 */
	public static boolean verifieAnswer(FlashCard currentFlashBack ,String answer) {
		return currentFlashBack.getTranslation().equalsIgnoreCase(answer);
	}
	//
}
