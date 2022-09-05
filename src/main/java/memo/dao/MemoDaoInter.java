package memo.dao;

import javax.servlet.http.HttpServletRequest;

import memo.vo.MemoVO;

public interface MemoDaoInter {

	MemoVO findOne(HttpServletRequest req);

	boolean createProc(HttpServletRequest req);

	boolean updateProc(HttpServletRequest req);

	boolean deleteProc(String idx, String id, String preSubfolder);
	
}
