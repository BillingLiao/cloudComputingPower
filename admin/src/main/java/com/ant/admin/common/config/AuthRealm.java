package com.ant.admin.common.config;

import com.ant.admin.dao.FuncDao;
import com.ant.admin.dao.UserDao;
import com.ant.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaopinchao
 * @date 2018-7-28 08:04
 */
public class AuthRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private FuncDao funcDao;

    @Autowired
    private UserDao userDao;

    {super.setName("authRealm");}

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取session中的用户
        String user_name=(String) principals.fromRealm(this.getClass().getName()).iterator().next();
        //通过用户名来查询用户所具有的所有操作权限
        Set<String> permissions = new HashSet<>();
        permissions = funcDao.getFuncByUserName(user_name);


        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        info.addStringPermissions(permissions);
        //将菜单放入shiro的session中
        //SecurityUtils.getSubject().getSession().setAttribute("user",permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从主体传过来的认证信息中，获得用户名
        String username = (String) token.getPrincipal();
        //通过用户名得到数据库中的凭证
        User user = userDao.getUserByName(username);
        String password = null;
        if(user == null){
            return null;
        }
        password = user.getPassword();
        if (StringUtils.isEmpty(password)){
            return null;
        }
        //将用户登录信息放入shiro的session中
        SecurityUtils.getSubject().getSession().setAttribute("user",user);
        //放入shiro.调用CredentialsMatcher检验密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),password,this.getClass().getName());
        return authenticationInfo;
    }
}
