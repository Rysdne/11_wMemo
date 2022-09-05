package list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import list.dao.ListDaoInter;
import list.vo.ListVO;

@Service
public class ListService implements ListServiceInter {

	@Autowired
	ListDaoInter dao;

	public List<ListVO> listAll(String id) {
		return dao.listAll(id);
	}

	public List<ListVO> listMonth(String id, String idxMonth) {
		return dao.listMonth(id, idxMonth);
	}

}
