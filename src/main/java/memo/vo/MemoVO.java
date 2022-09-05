package memo.vo;

public class MemoVO {

	private String idx;
	private String id;
	private String subfolder;
	private String title;
	private String cdate;
	private String ctime;
	private String loaded;

	public MemoVO() {
	}
	
	public MemoVO(String idx, String id, String subfolder, String title, String loaded) {
		this.idx = idx;
		this.id = id;
		this.subfolder = subfolder;
		this.title = title;
		this.loaded = loaded;
	}



	public MemoVO(String idx, String id, String subfolder, String title, String cdate, String ctime) {
		this.idx = idx;
		this.id = id;
		this.subfolder = subfolder;
		this.title = title;
		this.cdate = cdate;
		this.ctime = ctime;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubfolder() {
		return subfolder;
	}

	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getLoaded() {
		return loaded;
	}

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}
	
}
