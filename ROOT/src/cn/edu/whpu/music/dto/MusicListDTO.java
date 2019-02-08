package cn.edu.whpu.music.dto;
/** 
 *  	DTO类用于传输JDBC操作的数据  其操作的数据表为 tb_musiclists
 *  	DTO创建规范
 *  	1.DTO类中属性的个数和类型要和对应操作的数据表中的字段保持一致
 *  	2.提供无参构造器
 *  	3.提供带有所有参数的构造器
 *		4.提供所有属性的getter setter方法  
 ** @author ZY
 *
 */
public class MusicListDTO {
	private int listId;
	private String listName;
	private String listDesc;
	private String listTime;
	private int listUid;
	
	public MusicListDTO() {
		super();
	}
	public MusicListDTO(int listId, String listName, String listDesc, String listTime, int listUid) {
		super();
		this.listId = listId;
		this.listName = listName;
		this.listDesc = listDesc;
		this.listTime = listTime;
		this.listUid = listUid;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getListDesc() {
		return listDesc;
	}
	public void setListDesc(String listDesc) {
		this.listDesc = listDesc;
	}
	public String getListTime() {
		return listTime;
	}
	public void setListTime(String listTime) {
		this.listTime = listTime;
	}
	public int getListUid() {
		return listUid;
	}
	public void setListUid(int listUid) {
		this.listUid = listUid;
	}
	
	
	
}
