package kr.yerina.wmp.autonomousRegistration.filters;

import javax.servlet.FilterConfig;

import kr.yerina.wmp.autonomousRegistration.utils.AgentUtils;
import kr.yerina.wmp.autonomousRegistration.utils.HttpUtils;
import kr.yerina.wmp.autonomousRegistration.utils.MappedDiagnosticContextUtil;
import kr.yerina.wmp.autonomousRegistration.utils.RequestWrapper;
import org.apache.log4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogbackMdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = RequestWrapper.of(request);

        // Set Http Header
        MappedDiagnosticContextUtil.setJsonValue(MappedDiagnosticContextUtil.HEADER_MAP_MDC, requestWrapper.headerMap());

        // Set Http Body
        MappedDiagnosticContextUtil.setJsonValue(MappedDiagnosticContextUtil.PARAMETER_MAP_MDC, requestWrapper.parameterMap());

        // If you use SpringSecurity, you could SpringSecurity UserDetail Information.
        MappedDiagnosticContextUtil.setJsonValue(MappedDiagnosticContextUtil.USER_INFO_MDC, HttpUtils.getCurrentUser());

        // Set Agent Detail
        MappedDiagnosticContextUtil.setJsonValue(MappedDiagnosticContextUtil.AGENT_DETAIL_MDC, AgentUtils.getAgentDetail((HttpServletRequest) request));

        // Set Http Request URI
        MappedDiagnosticContextUtil.set(MappedDiagnosticContextUtil.REQUEST_URI_MDC, requestWrapper.getRequestUri());

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}