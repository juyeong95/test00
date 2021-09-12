package ex01.dbservice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ex01.dbcommon.DBCommon;
import ex01.dto.MemDTO;

public class DBServiceImpl implements DBService{
	PreparedStatement ps;
	ResultSet rs;
	

	@Override
	public int insertMember(MemDTO dto) {
		String sql = "insert into fx_test1 values(?,?,?,?,?,?)";
		int result = 0;
		try {
			ps = DBCommon.con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getAge());
			ps.setString(6, dto.getFav());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	@Override
	public MemDTO loginCheck(String userId) {
		String sql = "select * from fx_test1 where id=?";
		
		MemDTO dto = null;
		try {
			ps = DBCommon.con.prepareStatement(sql);
			ps.setString(1, userId);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				dto = new MemDTO();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getString("age"));
				dto.setFav(rs.getNString("fav"));
				dto.setGender(rs.getString("gender"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	

}
