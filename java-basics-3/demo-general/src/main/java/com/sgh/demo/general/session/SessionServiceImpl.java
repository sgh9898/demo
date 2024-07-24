package com.sgh.demo.common.session;

import com.sgh.demo.general.database.db.entity.DemoEntity;
import com.sgh.demo.common.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
 * Session 功能
 *
 * @author Song gh on 2023/3/6.
 */
@Service
public class SessionServiceImpl implements com.sgh.demo.common.session.SessionService {

    /** 添加 session */
    @Override
    public String createSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            DemoEntity entity = new DemoEntity();
            entity.setName("测试 session 功能");
            session.setAttribute("demo", entity);
            session.setAttribute("demoMessage", "这是一条新建的 session 信息");
            session.setMaxInactiveInterval(60);
            return "新建 Session 成功";
        } else {
            session.setAttribute("demoMessage", "这是一条已经存在的 session 信息");
            return "Session 已经存在";
        }
    }

    /** 读取 session */
    @Override
    public Object readSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DemoEntity entity = (DemoEntity) session.getAttribute("demo");
        return JsonUtils.beanToJson(entity);
    }
}
