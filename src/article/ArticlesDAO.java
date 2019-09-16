package article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDAO {
	// db接続共通メソッド
	public Connection DBConnection() {
		Connection conn = null;
		String DB_HOST = null;
		String DB_DRIVER = null;

	    String osname = System.getProperty("os.name");
	    if(osname.indexOf("Windows")>=0) {
	        DB_HOST = "jdbc:mariadb://192.168.179.7";
	        DB_DRIVER = "org.mariadb.jdbc.Driver";
	    } else if(osname.indexOf("Linux")>=0) {
	        DB_HOST = "jdbc:mysql://localhost";
	        DB_DRIVER = "com.mysql.jdbc.Driver";
	    }

		try {
			Class.forName(DB_DRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(
				DB_HOST + "/blog_sys",
				"tomcat",
				"2Bbbbbb\""
			);
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public List<ArticlesDTO> findAll() {
		List<ArticlesDTO> articlesDTO = new ArrayList<>();	//戻り値の変数宣言

		// sql発行
		try(Connection conn = this.DBConnection()) {
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
				dto.setModify(rset.getDate("modify"));
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

	    try(Connection conn = this.DBConnection()){
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

		try(Connection conn = this.DBConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM article_list WHERE id = " + id;
			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			articlesDTO.setId(rset.getInt("id"));
			articlesDTO.setUser_id(rset.getInt("user_id"));
			articlesDTO.setTitle(rset.getString("title"));
			articlesDTO.setArticle_path(rset.getString("article_path"));
			articlesDTO.setUpload(rset.getDate("upload"));
			articlesDTO.setModify(rset.getDate("modify"));
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return articlesDTO;
	}

	public List<ArticlesDTO> findByUserId(int userId) {
		List<ArticlesDTO> articlesDTO = new ArrayList<>();

		try(Connection conn = this.DBConnection()) {
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
		    Connection conn = this.DBConnection();
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO article_list VALUES (null," + user_id + ",0,'" + title + "','" + path + "',0,now(),now())";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
