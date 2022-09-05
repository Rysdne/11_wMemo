package list.service;

import java.util.List;

import list.vo.ListVO;

public interface ListServiceInter {

	List<ListVO> listAll(String id);

	List<ListVO> listMonth(String id, String idxMonth);

}
