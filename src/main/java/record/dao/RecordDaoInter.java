package record.dao;

import java.util.List;

import record.vo.RecordVO;

public interface RecordDaoInter {

	List<RecordVO> recordList(String id);

	List<RecordVO> recordListOne(int ridx);

	boolean recordSave(String id, String rtitle, String rtext);

	boolean recordDelete(int ridx);

}
