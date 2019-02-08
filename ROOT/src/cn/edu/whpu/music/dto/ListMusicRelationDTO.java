package cn.edu.whpu.music.dto;
/**
 * ListMusicRelationDTO类用于传输JDBC操作的数据
 * @author WW
 */
public class ListMusicRelationDTO {
	private int listId;
	private int musicId;
	
	
	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public ListMusicRelationDTO() {
	}

	public ListMusicRelationDTO(int listId, int musicId) {
		super();
		this.listId = listId;
		this.musicId = musicId;
	}

	@Override
	public String toString() {
		return "ListMusicRelationDTO [listId=" + listId + ", musicId=" + musicId + "]";
	}
	
}
