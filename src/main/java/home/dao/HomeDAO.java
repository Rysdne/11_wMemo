package home.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

@Repository
public class HomeDAO implements HomeDaoInter {

	Connection conn;
	PreparedStatement pstmt;

	public HomeDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rysdne", "3882");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void wMemoCheck() {
		try {
			String sql = "select table_name from all_tables where table_name='WMEMO'";
			pstmt = conn.prepareStatement(sql);
			ResultSet ex = pstmt.executeQuery();
			if (!ex.next()) {
				sql = "create table wMemo(idx varchar2(14) primary key, id varchar2(50) not null, subfolder varchar2(50) not null, title varchar2(50) not null, cdate varchar2(10) not null, ctime varchar2(8) not null, constraint wMemo_id_fk foreign key(id) references wUID(id) on delete cascade)";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				System.out.println("wMemo 테이블 생성");
			} else {
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void wUidCheck() {
		try {
			String sql = "select table_name from all_tables where table_name='WUID'";
			pstmt = conn.prepareStatement(sql);
			ResultSet UidEx = pstmt.executeQuery();
			if (!UidEx.next()) {
				sql = "create table wUID(uidx number(5) primary key, id varchar2(20) unique not null, password varchar2(20) not null, email varchar2(40) unique not null)";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				System.out.println("wUID 테이블 생성");
			}
			sql = "select sequence_name from all_sequences where sequence_name='WUID_UIDX_SEQ'";
			pstmt = conn.prepareStatement(sql);
			ResultSet seqEx = pstmt.executeQuery();
			if (!seqEx.next()) {
				sql = "create sequence wUID_uidx_seq increment by 1 start with 10001";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				System.out.println("UserIdx 시퀀스 생성");
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void wRecCheck() {
		try {
			String sql = "select table_name from all_tables where table_name='WREC'";
			pstmt = conn.prepareStatement(sql);
			ResultSet UidEx = pstmt.executeQuery();
			if (!UidEx.next()) {
				sql = "create table wRec(ridx number(5) primary key, id varchar2(20) not null, rtitle varchar2(100) not null, rtext varchar2(4000), rdate date  not null, constraint wRec_id_fk foreign key(id) references wUID(id) on delete cascade)";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				System.out.println("wRec 테이블 생성");
			}
			sql = "select sequence_name from all_sequences where sequence_name='WREC_RIDX_SEQ'";
			pstmt = conn.prepareStatement(sql);
			ResultSet seqEx = pstmt.executeQuery();
			if (!seqEx.next()) {
				sql = "create sequence wRec_ridx_seq increment by 1 start with 10001";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				System.out.println("RecordIdx 시퀀스 생성");
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
