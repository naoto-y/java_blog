package comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Connecter;

public class CommentsDAO {

    public List<CommentsDTO> findByAtrticleId(int id) {
        List<CommentsDTO> commentsDTO = new ArrayList<>();

        try(Connection conn = Connecter.DBConnection()) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM comment_list WHERE article_id = " + id;
            ResultSet rset = stmt.executeQuery(sql);

            while(rset.next()) {
                CommentsDTO dto = new CommentsDTO();
                dto.setId(rset.getInt("id"));
                dto.setUser_id(rset.getInt("user_id"));
                dto.setTitle(rset.getString("title"));
                dto.setComment(rset.getString("comment"));
                dto.setUpload(rset.getDate("upload"));
                commentsDTO.add(dto);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return commentsDTO;
    }

    public void insertComment(int article_id, int user_id, String title, String comment) {
        try {
            Connection conn = Connecter.DBConnection();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO comment_list VALUES (null," + article_id + "," + user_id + ",1,'" + title + "','" + comment + "',0,now())";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
