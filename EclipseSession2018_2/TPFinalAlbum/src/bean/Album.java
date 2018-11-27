package bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Album {
	

	//The id is the album id in the Last FM library
	private String id;
	private String albumName;
	private String type;
	private List<Song> tracklist;
	private String addedBy;
	
	/**
	 * Album constructor
	 * @param albumName album's name
	 * @param type album's type LP or EP
	 */
	public Album(String albumName, String type) {
		super();
		this.albumName = albumName;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tracklist == null) ? 0 : tracklist.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Album other = (Album) obj;
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tracklist == null) {
			if (other.tracklist != null)
				return false;
		} else if (!tracklist.equals(other.tracklist))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	
	
	
}
