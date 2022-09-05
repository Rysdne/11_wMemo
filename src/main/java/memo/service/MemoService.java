package memo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memo.dao.MemoDaoInter;
import memo.vo.MemoVO;

@Service
public class MemoService implements MemoServiceInter{

	@Autowired
	MemoDaoInter dao;

	@Override
	public MemoVO findOne(HttpServletRequest req) {
		return dao.findOne(req);
	}
	
	@Override
	public boolean createProc(HttpServletRequest req) {
		return dao.createProc(req);
	}

	@Override
	public boolean updateProc(HttpServletRequest req) {
		return dao.updateProc(req);
	}

	@Override
	public boolean deleteProc(String idx, String id, String preSubfolder) {
		return dao.deleteProc(idx, id, preSubfolder);
	}
	
}
