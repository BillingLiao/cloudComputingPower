package com.ant.webPage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ant.entity.User;
import com.ant.webPage.model.Msg;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.tool.CodeConstant;

import com.ant.webPage.tool.HttpTool;
import com.ant.webPage.tool.TokenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限拦截器
 * @author 莫小雨
 *
 */
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private Msg msg;
	
	@Autowired private UserService userService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		
		if(CheckTool.isString(token)) {
			int userId = TokenTool.verify(token);
			ValueOperations<String, User> operations=redisTemplate.opsForValue();
			boolean exists = redisTemplate.hasKey("user:"+userId);
			if(exists) {
				//有缓存的情况，直接从缓存中取
				User user = operations.get("user:" + userId);
				if(user.getStatus() == 0) {
					//被锁定的情况，从数据库中确认
					user = userService.findOne(userId);
					operations.set("user:"+userId, user);
					msg.set("您被管理员锁定，请联系管理员处理", CodeConstant.LOCK, null);
				} else {
					request.getSession().setAttribute("user", user);
					return true;
				}
			} else {
				//没有缓存的情况，从数据库中取
				if(userId > 0) {
					User user = userService.findOne(userId);
					if(user != null) {
						
						if(user.getStatus() == 0) {
							//被锁定的情况
							msg.set("您被管理员锁定，请联系管理员处理", CodeConstant.LOCK, null);
						} else {
							request.getSession().setAttribute("user", user);
							operations.set("user:"+userId, user);
							return true;
						}
					} else {
						msg.set("没有找到指定用户", CodeConstant.ERR_AUTH, null);
					}
				} else if(userId == 0) {
					msg.set("token超时，请重新登录", CodeConstant.ERR_AUTH, null);
				} else {
					msg.set("token有误", CodeConstant.ERR_AUTH, null);
				}
			}
		}else {
			//没有带token的情况
			msg.set("请先登录", CodeConstant.ERR_AUTH, null);
		}
		HttpTool.jsonMsg(response, msg);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(response.getStatus() == 200) {
			//访问正常的情况
			return ;
		} else if(response.getStatus() == 500) {
			msg.set("Server Error by wenxi", CodeConstant.ERROR, null);
		} else if(response.getStatus() == 404) {
			msg.set("URL Error by wenxi", CodeConstant.ERROR, null);
		} else {
			msg.set("Nuknow Error:" + response.getStatus() + " by wenxi", CodeConstant.ERROR, null);
		}
//		modelAndView.addObject(msg);
//		HttpTool.jsonMsg(response, msg);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
