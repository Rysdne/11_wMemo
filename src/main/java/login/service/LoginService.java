package login.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.dao.LoginDAO;
import login.dao.LoginDaoInter;
import login.vo.LoginVO;

@Service
public class LoginService implements LoginServiceInter {

	@Autowired
	LoginDaoInter dao;

	public boolean login(HttpServletRequest req) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		LoginVO vo = new LoginVO();
		vo.setId(id);
		vo.setPassword(password);
		boolean result = dao.login(vo);
		if (result) {
			req.getSession().setAttribute("id", req.getParameter("id"));
		}
		return result;
	}

	@Override
	public LoginVO findId(String email) {
		LoginVO result = dao.findId(email);
		return result;
	}

	@Override
	public LoginVO findPw(String id, String email) {
		LoginVO result = dao.findPw(id, email);
		return result;
	}

	@Override
	public int pwReset(String id, String password) {
		int result = dao.pwReset(id, password);
		return result;
	}

	@Override
	public int regId(String id, String password, String email) {
		return dao.regId(id, password, email);
	}

}
