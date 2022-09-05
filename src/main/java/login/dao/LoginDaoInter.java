package login.dao;

import login.vo.LoginVO;

public interface LoginDaoInter {

	boolean login(LoginVO vo);

	LoginVO findId(String email);

	LoginVO findPw(String id, String email);
	
	int pwReset(String id, String password);
	
	int regId(String id, String password, String email);

}
