package cal.tpCertificatif.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cal.tpCertificatif.bean.Deck;
import cal.tpCertificatif.util.IPersisanceService;

public class DeckPersistanceService implements IPersisanceService<Deck>{
	
	/**
	 * Sauvegarder les Deck dans un fichier xml
	 */
	@Override
	public boolean saveElementToXML(String pathName,Deck deck) {

		try {

			XStream stream = new XStream(new DomDriver());
			stream.alias("Deck",Deck.class);

			stream.toXML(deck, new FileOutputStream(pathName,true));

			return new File(pathName).exists();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	/**
	 * Recuperer un Deck
	 * @return le deck souhaite 
	 */
	@Override
	public Deck loadElement(String pathName) throws FileNotFoundException {
		XStream stream = new XStream(new DomDriver());
		stream.alias("Deck",Deck.class);
		return   (Deck) stream.fromXML(new FileInputStream(pathName));
		
	}



}
