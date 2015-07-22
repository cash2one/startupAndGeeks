<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>校讯通＋教育管理平台 - 登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css"/>
</head>
<body>
<div class="g-wrap">
    <div class="g-top">
        <div class="wrap f-ma f-cb">
            <a class="s-logo f-fl" href="javascript:void(0)">校讯通＋教育管理平台</a>
        </div>
    </div>
    <div class="g-main blue">
        <div class="g-mainwrap f-ma f-cb">
            <div class="g-loginwrap f-fr">
                <form action="" method="post">
                    <h2>用户登录</h2>
                    <ul class="m-formul">
                        <li class="iptwrap username">
                            <input type="text" class="u-ipt j-ipt j-usernumber" name="username" id="username" placeholder="请输入手机号码" value="<shiro:principal/>"/>
                            <p class="u-errotips j-errotips"></p>
                        </li>
                        <li class="iptwrap password">
                            <input type="password" class="u-ipt j-ipt j-userpassword" name="password" id="password" placeholder="请输入密码"/>
                            <p class="u-errotips j-errotips"></p></li>
                        <li class="f-cb">
                            <div class="atloginwrap f-fl"><input type="checkbox" name="rememberMe" value="true" class="ipt-c" id="rememberMe"><label for="rememberMe">自动登录</label></div>
                            <div class="gbpasswrord f-fr"><a href="javascript:void(0)">忘记密码？</a></div>
                        </li>
                        <li class="u-erro j-erroinf erro">${error}</li>
                        <li class="f-cb">
                            <input type="submit" class="u-lgbtn lgbtn j-getlogin  f-fl" value="登录">
                            <input type="reset" class="u-lgbtn lgreset  f-fr" value="重置">
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="g-footer">
        <p class="wrap f-ma">中移（杭州）信息技术有限公司</p>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/methodCommUnit.js"></script>
<script type="text/javascript">
    $(function(){
        var loginBihv = {
            '$ipt': $('.j-ipt'),
            '$usernumber': $('.j-usernumber'),
            '$userpassword': $('.j-userpassword'),
            '$login': $('.j-getlogin'),
            '$erroinf': $('.j-erroinf'),
            'loginform': document.getElementById('loginform')
        };
        loginBihv.bindE = function(){
            var _self = this;
            /* 表单交互信息 */
            _self.$ipt.bind({
                'focus': function(){
                    var $upt = $(this).parent(),
                        $errotips = $upt.find('.j-errotips');
                    $upt.removeClass('erro').addClass('focus');
                    $errotips.empty().hide();
                    _self.$erroinf.empty().hide();
                },
                'blur': function(){
                    var $this = $(this),
                        val = $this.val(),
                        $upt = $(this).parent(),
                        $errotips = $upt.find('.j-errotips');
                    $upt.removeClass('focus');
                    if($this.is('.j-usernumber') && val && !checkUnit.checkPhoneNumber(val)){
                        $errotips.text('电话号码格式不正确').fadeIn(100);
                        $upt.addClass('erro');
                    }
                }
            });
            /*  登录 */
            _self.$login.bind({
                'click': function(){
                    var userNumber = _self.$usernumber.val(),
                        userPassword = _self.$userpassword.val();
                    if(!userNumber){
                        _self.$erroinf.text('手机号码不能为空').show();
                        _self.$usernumber.parent().addClass('erro');
                        return false;
                    }
                    if(!userPassword){
                        _self.$erroinf.text('密码不能为空').show();
                        _self.$userpassword.parent().addClass('erro');
                        return false;
                    }
                    _self.loginform.submit();
                }
            })
        };
        loginBihv.bindE();
    });
</script>
</body>
</html>