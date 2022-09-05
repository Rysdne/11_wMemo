package login.service;

import javax.servlet.http.HttpServletRequest;

import login.vo.LoginVO;

public interface LoginServiceInter {

	boolean login(HttpServletRequest req);
	
	LoginVO findId(String email);
	
	LoginVO findPw(String id, String email);

	int pwReset(String id, String password);
	
	int regId(String id, String password, String email);	
	
}
