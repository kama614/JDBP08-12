package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MemberDao;
import domain.Member;

@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
				.forward(request, response);
	}

	// 名前と年齢を取得→名前のﾊﾞﾘﾃﾞｰｼｮﾝ→年齢のﾊﾞﾘﾃﾞｰｼｮﾝ→フラグisErrorの判定
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// バリデーション用のフラグ
		boolean isError = false;
		// isError はバリデーションの結果、不備があったかどうかを記録するためのフラグです。
		// 初期値は false（問題なし）ですが、エラーが見つかった場合に true に変更されます。

		// パラメータの取得とバリデーション
		String name = request.getParameter("name"); // 受け取ったパラメータ"name"を取得
		request.setAttribute("name", name); // 再表示用・入力内容をリクエストスコープに保存
		
		if (name.isBlank()) { // 入力された名前が空（スペースのみや未入力）かどうかをチェック
			//エラーメッセージの作成
			request.setAttribute("nameError", "名前が未入力です。");
			isError = true; // 入力に不備ありと判定
		} else if (name.length() > 10) { // 名前が10文字を超えていないかをチェック
			request.setAttribute("nameError", "10字以内で入力してください。");
			isError = true;
		}
		// エラーが発生した場合は、対応するエラーメッセージを
		// リクエストスコープに保存し、isError を true に設定

		// 年齢は文字列として取得。未入力でなければ整数へ変換
		String strAge = request.getParameter("age"); 
		//フォームから送られてきた age パラメータ（入力された年齢）を取得
		Integer age = null; // 初期値は null
		request.setAttribute("age", strAge); // 再表示用・年齢の入力内容をリクエストスコープに保存
		
		if (!strAge.isEmpty()) { // 年齢が未入力でない場合に処理を実行
			try {
				age = Integer.parseInt(strAge);
			} catch (NumberFormatException e) {
				// 整数に変換できない場合の処理
				request.setAttribute("ageError", "年齢は整数で入力してください。");
				isError = true;
			}

		}

		// 会員種別、住所はバリデーション不要
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		String address = request.getParameter("address");
		request.setAttribute("typeId", typeId); // 再表示用
		request.setAttribute("addredd", address); // 再表示用

		// 入力不備がある場合は、フォームを再表示し、処理を中断
		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
					.forward(request, response);
			return;
		}
		// 入力に不備がなければ、データの追加
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		member.setTypeId(typeId);
		member.setAddress(address);

		try {
			MemberDao memberDao = DaoFactory.createMemberDao();
			memberDao.insert(member);
			request.getRequestDispatcher("/WEB-INF/view/addMemberDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
