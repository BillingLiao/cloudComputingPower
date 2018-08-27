package com.ant.admin.common.shiro;

import com.ant.admin.common.utils.Constant;
import com.ant.admin.dao.MenuDao;
import com.ant.admin.dao.UserDao;
import com.ant.admin.entity.Menu;
import com.ant.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhaopinchao
 * @date 2018-7-28 08:04
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserDao userDao;

    //{super.setName("authRealm");}

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
/*
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
*/

        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");

        Integer userId = user.getUserId();

        List<String> permsList;
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<Menu> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(Menu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = userDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;

    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

        //查询用户信息
        User user = new User();
        user.setUserName(token.getUsername());
        user = userDao.selectOne(user);
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),this.getClass().getName());
        //将用户登录信息放入shiro的session中
        SecurityUtils.getSubject().getSession().setAttribute("user",user);

        return info;
    }
        /*//从主体传过来的认证信息中，获得用户名
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

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),this.getClass().getName());

        */
    }

