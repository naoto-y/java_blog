package article;

import java.sql.Date;

public class ArticlesDTO {
	private int id;
	private int user_id;
	private String title;
	private String article_path;
	private Date upload;
	private Date modify;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle_path() {
		return article_path;
	}

	public void setArticle_path(String article_path) {
		this.article_path = article_path;
	}

	public Date getUpload() {
		return upload;
	}

	public void setUpload(Date upload) {
		this.upload = upload;
	}

	public Date getModify() {
		return modify;
	}

	public void setModify(Date modify) {
		this.modify = modify;
	}
}
