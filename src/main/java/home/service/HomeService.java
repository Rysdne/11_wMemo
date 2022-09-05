package home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.dao.HomeDaoInter;

@Service
public class HomeService implements HomeServiceInter{

	@Autowired
	HomeDaoInter dao;

	@Override
	public void wTableCheck() {
		dao.wUidCheck();
		dao.wMemoCheck();
		dao.wRecCheck();
	}
	
}
