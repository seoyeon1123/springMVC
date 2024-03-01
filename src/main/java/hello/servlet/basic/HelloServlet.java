package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //queryString 조회
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //header 정보에 넣기 - 응답
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //http message body에 데이터가 들어감 -> 화면에 hello + username을 보여줌
        response.getWriter().write("hello " + username);

    }
}
