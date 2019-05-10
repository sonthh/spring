package demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	//@Autowired
	//private UserDAO userDAO;
	
	public CustomAuthenticationSuccessHandler() {
		super();
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//super.onAuthenticationSuccess(request, response, authentication);
		//this.setAlwaysUseDefaultTargetUrl(false);
		this.setDefaultTargetUrl("/admin");
		this.setAlwaysUseDefaultTargetUrl(false);
		super.onAuthenticationSuccess(request, response, authentication);
		
		System.out.println("Xử lí session");
		//HttpSession session = request.getSession();
		//String username = authentication.getName();
		//User authUser = userDAO.getItemByUsername(username);
		//System.out.println(authUser);
		//session.setAttribute("authUser", authUser);

		/*Set target URL to redirect*/
		//String targetUrl = determineTargetUrl(authentication);
		//String targetUrl = "/admin";
		//redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*protected String determineTargetUrl(Authentication authentication) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (authorities.contains("ROLE_ADMIN")) {
			return "/admin.htm";
		} else if (authorities.contains("ROLE_USER")) {
			return "/user.htm";
		} else {
			throw new IllegalStateException();
		}
	}*/

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
