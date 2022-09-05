package record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import record.dao.RecordDaoInter;
import record.vo.RecordVO;

@Service
public class RecordService implements RecordServiceInter{

	@Autowired
	RecordDaoInter dao;

	@Override
	public List<RecordVO> recordList(String id) {
		return dao.recordList(id);
	}

	@Override
	public List<RecordVO> recordListOne(int ridx) {
		return dao.recordListOne(ridx);
	}
	
	@Override
	public boolean recordSave(String id, String rtitle, String rtext) {
		return dao.recordSave(id, rtitle, rtext);
	}

	@Override
	public boolean recordDelete(int ridx) {
		return dao.recordDelete(ridx);
	}
	
}
