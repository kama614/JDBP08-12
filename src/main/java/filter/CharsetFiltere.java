// CharsetFilter (文字コードフィルタ)
// CharsetFilter の目的は、リクエストおよびレスポンスの文字コードを統一すること
// リクエスト文字コードの設定、レスポンス文字コードの設定
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class CharsetFiltere
 */
@WebFilter("/*")
public class CharsetFiltere implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public CharsetFiltere() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
/*
必要性
文字化け防止: フォームデータやレスポンスデータが文字化けする問題を防ぐ。
統一性: アプリケーション全体で文字コードを統一することで、処理の一貫性を保つ。
効率性: 各サーブレットやJSPで個別に文字コードを設定する必要がなくなる。
*/