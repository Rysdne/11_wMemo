package memo.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import memo.vo.MemoVO;

@Repository
public class MemoDAO implements MemoDaoInter {

	Connection conn;
	PreparedStatement pstmt;
	String basePath = "C:\\Users\\Rysdne\\Desktop\\Rysdne\\Document\\01. Java\\work\\wMemo\\src\\main\\webapp\\file\\";

	public MemoDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rysdne", "3882");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemoVO findOne(HttpServletRequest req) {

		String sql = "select * from wMemo where idx=? and id=? and subfolder=? and title=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, req.getParameter("idx"));
			pstmt.setString(2, req.getParameter("id"));
			pstmt.setString(3, req.getParameter("subfolder"));
			pstmt.setString(4, req.getParameter("title"));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String idx = rs.getString("idx");
				String id = rs.getString("id");
				String subfolder = rs.getString("subfolder");
				String title = rs.getString("title");

				String loaded = "";
				String line;

				String folderPath = basePath + id;
				String subFolderPath = folderPath + "\\" + subfolder;
				String filePath = subFolderPath + "\\" + idx;

				BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));

				while ((line = br.readLine()) != null) {
					loaded += line;
				}

				MemoVO result = new MemoVO(idx, id, subfolder, title, loaded);

				br.close();
				pstmt.close();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createProc(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			LocalDateTime _time = LocalDateTime.now();
			// 일련번호로 사용할 예정
			// 이런 일련번호가 있어야 title을 자유롭게 사용 가능
			String idx = _time.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			// long idx=Integer.parseInt(_idx);
			String cdate = _time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String ctime = _time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

			// 2) 사용자 이름
			String id = req.getParameter("id");
			// 3) 폴더 이름
			String subfolder = req.getParameter("subfolder");
			// 4) 타이틀
			String title = req.getParameter("title");
			// 5) 메모
			String memo = req.getParameter("memo");
			
//			// 이걸 이렇게 쓰면 안됨(추후 수정 필요)
//			if (memo.equals(null))
//				memo = "";
			
			// 6) 파일 이름
			String fileName = idx;

			// 7) 파일 경로
			// 경로 설정
			// 프로젝트 내부에 저장
			// 저장 경로 : C:\Users\Rysdne\Desktop\Rysdne\Document\01.
			// Java\work\wMemo\src\main\webapp\WEB-INF\static\file
			// 폴더명 : 입력받은 id로 자동 설정
			String folderPath = basePath + id;
			String subFolderPath = folderPath + "\\" + subfolder;
			String filePath = subFolderPath + "\\" + fileName;

			// 폴더명 설정
			File folder = new File(folderPath);
			// 경로 폴더 확인
			File subFolder = new File(subFolderPath);
			if (!subFolder.exists()) {
				subFolder.mkdir();
				System.out.println("하위 폴더 생성");
			}
			// 6) 파일 저장 위치
			// fos의 마지막에 false인 것은 파일을 새로 교체하기 위한 것
			// FileOutputStream fos = new FileOutputStream(filePath, false);

			FileOutputStream fos = new FileOutputStream(filePath, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));

			// // 7) 작성한 메모를 txt 파일로 저장
			bw.write(memo);
			System.out.println("파일 저장됨");

			bw.close();
			fos.close();

			// =============================================================================
			// 테이블 등록

			String sql = "insert into wMemo values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, id);
			pstmt.setString(3, subfolder);
			pstmt.setString(4, title);
			pstmt.setString(5, cdate);
			pstmt.setString(6, ctime);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("DB 입력됨");

			pstmt.close();

			// =============================================================================

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProc(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			LocalDateTime _time = LocalDateTime.now();
			// 일련번호로 사용할 예정
			// 이런 일련번호가 있어야 title을 자유롭게 사용 가능
			String idx = req.getParameter("idx");
			// long idx=Integer.parseInt(_idx);
			String cdate = _time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String ctime = _time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

			// 2) 사용자 이름
			String id = req.getParameter("id");
			// 3) 폴더 이름
			String subfolder = req.getParameter("subfolder");
			// 3-1) 기존 하위폴더
			String preSubfolder = req.getParameter("preSubfolder");

			// 4) 타이틀
			String title = req.getParameter("title");

			// 5) 메모
			String memo = req.getParameter("memo");
			
//			// 이걸 이렇게 쓰면 안됨(추후 수정 필요)
//			if (memo.equals(null))
//				memo = "";
			
			// 6) 파일 이름
			String fileName = idx;

			// 7) 파일 경로
			// 경로 설정
			// 프로젝트 내부에 저장
			// 저장 경로 : C:\Users\Rysdne\Desktop\Rysdne\Document\01.
			// Java\work\wMemo\src\main\webapp\WEB-INF\static\file
			// 폴더명 : 입력받은 id로 자동 설정
			String folderPath = basePath + id;

			String preFolderPath = folderPath + "\\" + preSubfolder;
			String preFilePath = preFolderPath + "\\" + fileName;

			String subFolderPath = folderPath + "\\" + subfolder;
			String filePath = subFolderPath + "\\" + fileName;

			// 폴더명 설정
			File folder = new File(folderPath);
			// 경로 폴더 확인
			File subFolder = new File(subFolderPath);
			if (!subFolder.exists()) {
				subFolder.mkdir();
				System.out.println("하위 폴더 생성");
			}

			// 이전 파일 존재 확인
			File preFile = new File(preFilePath);
			// 파일 삭제
			if (preFile.exists()) {
				preFile.delete();
				System.out.println("기존 파일 삭제됨");
			}

			// 6) 파일 저장 위치
			// fos의 마지막에 false인 것은 파일을 새로 교체하기 위한 것
			// FileOutputStream fos = new FileOutputStream(filePath, false);

			FileOutputStream fos = new FileOutputStream(filePath, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));

			// // 7) 작성한 메모를 txt 파일로 저장
			bw.write(memo);
			System.out.println("파일 수정됨");

			bw.close();
			fos.close();

			// =============================================================================
			// 테이블 수정

			String sql = "update wMemo set subfolder=?, title=?, cdate=?, ctime=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subfolder);
			pstmt.setString(2, title);
			pstmt.setString(3, cdate);
			pstmt.setString(4, ctime);
			pstmt.setString(5, idx);
			int rs = pstmt.executeUpdate();

			System.out.println("DB 수정됨");

			pstmt.close();

			// =============================================================================

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProc(String idx, String id, String preSubfolder) {

		// 1) 파일 이름
		String fileName = idx;

		// 2) 파일 경로
		String folderPath = basePath + id;

		String preFolderPath = folderPath + "\\" + preSubfolder;
		String preFilePath = preFolderPath + "\\" + fileName;

		try {

			// 이전 파일 존재 확인
			File preFile = new File(preFilePath);
			// 파일 삭제
			preFile.delete();
			System.out.println("파일 삭제 완료");
			
			String sql = "delete from wMemo where idx=? and id=? and subfolder=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, id);
			pstmt.setString(3, preSubfolder);
			ResultSet rs = pstmt.executeQuery(); 

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
