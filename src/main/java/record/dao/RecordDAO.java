package record.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import record.vo.RecordVO;

@Repository
public class RecordDAO implements RecordDaoInter{

	Connection conn;
	PreparedStatement pstmt;

	public RecordDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rysdne", "3882");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean recordSave(String id, String rtitle, String rtext) {
		try {
			LocalDateTime _time = LocalDateTime.now();
			String rdate = _time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			String sql = "insert into wRec values(wRec_ridx_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, rtitle);
			pstmt.setString(3, rtext);
			pstmt.setString(4, rdate);
			int rs = pstmt.executeUpdate();

			System.out.println("Rec 저장 완료");

			pstmt.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<RecordVO> recordList(String _id) {
		try {
			String sql = "select * from wRec where id=? order by ridx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			List<RecordVO> list = new ArrayList();
			while(rs.next()) {
				int ridx = rs.getInt("ridx");
				String id = rs.getString("id");
				String title = rs.getString("rtitle");
				String text = rs.getString("rtext");
				Date recdate = rs.getDate("rdate");
				RecordVO vo = new RecordVO(ridx, id, title, text, recdate);
				list.add(vo);
			}
			
			pstmt.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<RecordVO> recordListOne(int _ridx) {
		try {
			String sql = "select * from wRec where ridx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _ridx);
			ResultSet rs = pstmt.executeQuery();
			List<RecordVO> list = new ArrayList();
			if(rs.next()) {
				
				int ridx = rs.getInt("ridx");
				String id = rs.getString("id");
				String rtitle = rs.getString("rtitle");
				String rtext = rs.getString("rtext");
				Date rdate = rs.getDate("rdate");
				RecordVO vo = new RecordVO(ridx, id, rtitle, rtext, rdate);
				list.add(vo);
			}
			
			pstmt.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean recordDelete(int ridx) {
		try {
			String sql = "delete from wRec where ridx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			int rs = pstmt.executeUpdate();

			System.out.println("Rec 삭제 완료");
			pstmt.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
