package org.lic.springmvc.log.filter;

import org.lic.springmvc.log.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LoggingFilter extends OncePerRequestFilter {

    protected static final Logger logger = LoggerFactory
        .getLogger(LoggingFilter.class);

    private static final String REQUEST_PREFIX = "Request(%s): ";

    private static final String RESPONSE_PREFIX = "Response(%s): ";

    private AtomicLong id = new AtomicLong(1);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (logger.isDebugEnabled()) {
            long requestId = id.incrementAndGet();
            request = new RequestWrapper(requestId, request);
            response = new ResponseWrapper(requestId, response);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (logger.isDebugEnabled()) {
                logRequest(request);
                logResponse((ResponseWrapper) response);
            }
        }

    }

    private void logRequest(HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        if (request instanceof RequestWrapper) {
            msg.append(String
                .format(REQUEST_PREFIX, ((RequestWrapper) request).getId()));
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            msg.append("session id=").append(session.getId()).append("; ");
        }
        msg.append("content-type=").append(request.getContentType())
            .append("; ");
        msg.append("uri=").append(request.getRequestURI());
        if (!StringUtil.isEmpty(request.getQueryString())) {
            msg.append('?').append(request.getQueryString());
        }
        msg.append("; ");

        Map<String, String[]> map = request.getParameterMap();
        if (map.size() > 0) {
            msg.append("ParameterMap={");
            for (String key : map.keySet()) {
                msg.append(key).append("=").append(
                    Arrays.toString(map.get(key)));
            }
            msg.append("}; ");
        }

        if (request instanceof RequestWrapper && !isMultipart(request)) {
            RequestWrapper requestWrapper = (RequestWrapper) request;
            try {
                String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper
                    .getCharacterEncoding() : "UTF-8";
                String payload = new String(requestWrapper.toByteArray(), charEncoding);
                if (!StringUtil.isEmpty(payload)) {
                    msg.append("payload=").append(payload);
                }
            } catch (UnsupportedEncodingException e) {
                logger.warn("Failed to parse request payload", e);
            }
        }

        logger.debug(msg.toString());
    }

    private boolean isMultipart(HttpServletRequest request) {
        return request.getContentType() != null
            && request.getContentType().startsWith("multipart/form-data");
    }

    private void logResponse(ResponseWrapper response) {
        StringBuilder msg = new StringBuilder();
        msg.append(String.format(RESPONSE_PREFIX, response.getId()));
        try {
            msg.append(
                new String(response.toByteArray(), response
                    .getCharacterEncoding()));
        } catch (UnsupportedEncodingException e) {
            logger.warn("Failed to parse response payload", e);
        }
        logger.debug(msg.toString());
    }

}
