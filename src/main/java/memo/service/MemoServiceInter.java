package memo.service;

import javax.servlet.http.HttpServletRequest;

import list.vo.ListVO;
import memo.vo.MemoVO;

public interface MemoServiceInter {

	MemoVO findOne(HttpServletRequest req);

	boolean createProc(HttpServletRequest req);

	boolean updateProc(HttpServletRequest req);

	boolean deleteProc(String idx, String id, String preSubfolder);
	
}
