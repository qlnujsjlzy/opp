import edu.exam.online.professional.mapper.ExamineeMapper;
import edu.exam.online.professional.utils.SessionUser;
import edu.exam.online.professional.utils.ThreadLocalReqAndResp;
import edu.exam.online.professional.utils.ThreadLocalSessionUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by guodandan on 2016/3/24.
 */
public class Test {
    public static Logger log = LogManager.getLogger(Test.class.getName());

    protected static HttpServletRequest request() {
        return ThreadLocalReqAndResp.getRequest();
    }

    protected static HttpServletResponse response() {
        return ThreadLocalReqAndResp.getResponse();
    }

    @Autowired
    private ExamineeMapper examineeMapper;

    protected static HttpSession session() {
        return ThreadLocalReqAndResp.getSession();
    }

    public static void test1() {
        SessionUser sessionUser = new SessionUser();
        sessionUser.setType("examinee");
        sessionUser.setUserName("lizhaoyang");
        sessionUser.setUserId("97987q98410325");
        ThreadLocalSessionUser.setUser(sessionUser);
        request().getSession().getAttribute("SessionUser");
        session().setAttribute("SessionUser", sessionUser);
    }

    public static void main(String[] args) {
        SessionUser sessionUser = new SessionUser();
        if (sessionUser != null) {
            System.out.println("不为空");
            sessionUser = null;
            if (sessionUser != null) {
                System.out.println("不为空附空--不为空");
            } else {
                System.out.println("不为空附空--为空");
            }
        } else {
            System.out.println("为空");
        }
    }

}
