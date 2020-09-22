package com.man.handle;

import com.man.common.anno.IgnoreLogin;
import com.man.common.anno.Permission;
import com.man.common.constant.AuthorizationConstants;
import com.man.common.enums.SysErrorEnums;
import com.man.common.enums.TokenState;
import com.man.exception.SchoolException;
import com.man.service.LoginService;
import com.man.util.Jwt;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class AdminAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().toUpperCase().equals(RequestMethod.OPTIONS.toString())) {
            return true;
        }
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//            String requestUri = request.getRequestURI();
//            String contextPath = request.getContextPath();
//            String url = requestUri.substring(contextPath.length());
//            Set<String> allRmi = getAllRmi(request);
//            if (!allRmi.contains(url)){
//                throw new SchoolException("无法找到请求路径"+url);
//
//            }
            Method method = ((HandlerMethod)handler).getMethod();
            boolean hasLoginAnnotation= method.isAnnotationPresent(IgnoreLogin.class);
            //如果有ignoreLogin注解，直接跳过认证
            if(hasLoginAnnotation) {
                return true;
            }
            //从头信息中获取token
            String token = request.getHeader(AuthorizationConstants.AUTHORIZATION);

            if (StringUtils.isEmpty(token)) {
                throw new SchoolException(SysErrorEnums.AUTHORIZATION_NOT_BLANK.getErrorMessage());
            }

            JSONObject jo = Jwt.getJo(token);
            if(jo == null){
                throw new SchoolException(SysErrorEnums.TOKEN_INVALID.getErrorMessage());
            }

            TokenState tokenStatus = (TokenState) Jwt.valid(jo).get("state");
            if (tokenStatus.equals(TokenState.INVALID)){
                throw new SchoolException(SysErrorEnums.TOKEN_INVALID.getErrorMessage());
            }
            if (tokenStatus.equals(TokenState.EXPIRED)){
                throw new SchoolException(SysErrorEnums.TOKEN_EXPIRED.getErrorMessage());
            }
            JSONObject user =(JSONObject) jo.get(Jwt.subjectKey);
            String userName = (String)user.get("username");
            Object o = jo.get(Jwt.tokenKey);
            int userId = 0;
            if (o instanceof Long){
                Long lon = (Long) o;
                long l = lon.longValue();
                userId = (int)l;
            }
            //权限认证
            return permissionInterceptor(handler,userName,userId);
        }
        return false;
    }

    private boolean permissionInterceptor(Object handler, String userName, Integer userId){
        Method method = ((HandlerMethod)handler).getMethod();
        boolean hasLoginAnnotation= method.isAnnotationPresent(Permission.class);
        if (hasLoginAnnotation){
            Permission permission = method.getAnnotation(Permission.class);
            //获得方法表示的资源
            String resource = permission.value();
            Set<String> permissions = loginService.selectMyPermission();

            String[] resources = resource.split(",");
            for (String s : resources) {
                //判断用户资源中是否包含s.只要存在一个包含关系，直接返回true
                boolean isPass = permissions.contains(s);
                if (isPass){
                    return isPass;
                }
            }
            throw new SchoolException("无访问权限");
        }
        return true;
    }

    private Set<String> getAllRmi(HttpServletRequest request){
        Set<String> result = new HashSet<>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            result.addAll(pSet);
        }
        return result;
    }
}