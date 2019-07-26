package article;

public class ArticleMain {
	public static void main(String[] args) {
		ArticlesDAO dao = new ArticlesDAO();
		dao.insertArticle(1, "test5", "file");
	}
}
