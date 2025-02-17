package article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Connecter;

public class ArticlesDAO {

	public List<ArticlesDTO> findAll() {
		List<ArticlesDTO> articlesDTO = new ArrayList<>();	//戻り値の変数宣言

		// sql発行
		try(Connection conn = Connecter.DBConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM article_list";
			ResultSet rset = stmt.executeQuery(sql);

			// クエリ結果をdtoオブジェクトの配列に格納
			while(rset.next()) {
				ArticlesDTO dto = new ArticlesDTO();
				dto.setId(rset.getInt("id"));
				dto.setUser_id(rset.getInt("user_id"));
				dto.setTitle(rset.getString("title"));
				dto.setArticle_path(rset.getString("article_path"));
//				dto.setUpload(rset.getDate("upload"));    //"upload"が犯人
//				dto.setModify(rset.getDate("modify"));
				articlesDTO.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// 処理結果を返す
		return articlesDTO;
	}

	public int countAll() {
	    int count = 0;

	    try(Connection conn = Connecter.DBConnection()){
	        Statement stmt = conn.createStatement();
	        String sql = "SELECT * FROM article_list";
	        ResultSet rset = stmt.executeQuery(sql);

	        while(rset.next()) {
	            ++count;
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}

	public ArticlesDTO findById(int id) {
		ArticlesDTO articlesDTO = new ArticlesDTO();

		try(Connection conn = Connecter.DBConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM article_list WHERE id = " + id;
			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			articlesDTO.setId(rset.getInt("id"));
			articlesDTO.setUser_id(rset.getInt("user_id"));
			articlesDTO.setTitle(rset.getString("title"));
			articlesDTO.setArticle_path(rset.getString("article_path"));
//			articlesDTO.setUpload(rset.getDate("upload"));
//			articlesDTO.setModify(rset.getDate("modify"));
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return articlesDTO;
	}

	public List<ArticlesDTO> findByUserId(int userId) {
		List<ArticlesDTO> articlesDTO = new ArrayList<>();

		try(Connection conn = Connecter.DBConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM article_list WHERE user_id = " + userId;
			ResultSet rset = stmt.executeQuery(sql);

			while(rset.next()) {
				ArticlesDTO dto = new ArticlesDTO();
				dto.setId(rset.getInt("id"));
				dto.setUser_id(rset.getInt("user_id"));
				dto.setTitle(rset.getString("title"));
				dto.setArticle_path(rset.getString("article_path"));
				dto.setUpload(rset.getDate("upload"));
				dto.setModify(rset.getDate("modify"));
				articlesDTO.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return articlesDTO;
	}

	public void insertArticle(int user_id, String title, String path) {
		try {
		    Connection conn = Connecter.DBConnection();
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO article_list VALUES (null," + user_id + ",0,'" + title + "','" + path + "',0,now(),now())";
			stmt.executeUpdate(sql); //insert句の場合はexecuteUpdateを使用しないといけない
			stmt.close();
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
