package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.Controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.Controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.Controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns을 *로 주어서, /front-controller/v1/하위의 모든 URL요청 매핑되어 이 서블릿이 호출된다.
@WebServlet(name = "FrontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //URL을 key로 하여, ControllerV1 구현체들을 value로 가져올 수 있는 매핑정보MAP
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //URL별 매핑정보 생성
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //요청 URI에 맞는 구현체가져옴
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //가져온 구현체의 오버라이딩 메소드 실행
        controller.process(request, response);
    }
}