package record.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class RecordVO {

	int ridx;
	String id;
	String rtitle;
	String rtext;
	Date rdate;

	public RecordVO() {
	}

	public RecordVO(int ridx, String id, String rtitle, String rtext, Date rdate) {
		this.ridx = ridx;
		this.id = id;
		this.rtitle = rtitle;
		this.rtext = rtext;
		this.rdate = rdate;
	}

	public int getRidx() {
		return ridx;
	}

	public void setRidx(int ridx) {
		this.ridx = ridx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	public String getRtext() {
		return rtext;
	}

	public void setRtext(String rtext) {
		this.rtext = rtext;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

}
