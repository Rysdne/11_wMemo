package list.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import list.vo.ListVO;

@Repository
public class ListDAO implements ListDaoInter {

	Connection conn;
	PreparedStatement pstmt;

	public ListDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rysdne", "3882");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ListVO> listAll(String _id) {
		try {
			String sql = "select * from wMemo where id=? order by subfolder asc, idx asc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			List<ListVO> list = new ArrayList();
			while(rs.next()) {
				String idx = rs.getString("idx");
				String id = rs.getString("id");
				String subfolder = rs.getString("subfolder");
				String title = rs.getString("title");
				String cdate = rs.getString("cdate");
				String ctime = rs.getString("ctime");
				ListVO vo = new ListVO(idx, id, subfolder, title, cdate, ctime);
				list.add(vo);
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ListVO> listMonth(String _id, String idxMonth) {
		try {
			String sql = "select * from wMemo where id=? and idx like ? order by subfolder, idx asc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			pstmt.setString(2, idxMonth + "%");
			ResultSet rs = pstmt.executeQuery();
			List<ListVO> list = new ArrayList();
			while(rs.next()) {
				String idx = rs.getString("idx");
				String id = rs.getString("id");
				String subfolder = rs.getString("subfolder");
				String title = rs.getString("title");
				String cdate = rs.getString("cdate");
				String ctime = rs.getString("ctime");
				ListVO vo = new ListVO(idx, id, subfolder, title, cdate, ctime);
				list.add(vo);
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
