package list.dao;

import java.util.List;

import list.vo.ListVO;

public interface ListDaoInter {

	public List<ListVO> listAll(String id);

	public List<ListVO> listMonth(String id, String idxMonth);
	
}
