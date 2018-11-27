package bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/**
 * Application album
 * @author small44
 *
 */
@AllArgsConstructor
public class Artist {
	private String artistName;
	private List<Album> discography;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
		result = prime * result + ((discography == null) ? 0 : discography.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
			return false;
		if (discography == null) {
			if (other.discography != null)
				return false;
		} else if (!discography.equals(other.discography))
			return false;
		return true;
	}

}
