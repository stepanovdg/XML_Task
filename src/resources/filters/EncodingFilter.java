package resources.filters;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/26/12
 * Time: 10:45 AM
 */
import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    private String code;

    public EncodingFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter(ENCODING);
    }

    public void destroy() {
        code = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setCharacterEncoding(code);
        request.setCharacterEncoding(code);
        chain.doFilter(request, response);
    }
}