//  AuthFilter (認証フィルタ)
// AuthFilter の目的は、リクエストを処理する前に、
// ユーザーの認証やアクセス権限を確認。
// ログインチェック、権限チェック、セッション管理の実装

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
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
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
セキュリティ向上: 認証されていないユーザーや権限が不足しているユーザーが、保護されたリソースにアクセスするのを防ぐ。
統一性: 各サーブレットやJSPで個別に認証処理を記述する必要がなくなる。
効率性: 認証を一元管理することで、コードの重複を防ぎ、メンテナンスが容易になる。*/