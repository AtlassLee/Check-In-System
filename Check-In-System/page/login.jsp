<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    

        <meta charset="utf-8">
        <title>Login</title>
        <!-- Mobile specific metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- Force IE9 to render in normal mode -->
        <!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
        <meta name="author" content="SuggeElson" />
        <meta name="description" content=""
        />
        <meta name="keywords" content=""
        />
        <meta name="application-name" content="sprFlat admin template" />
        <!-- Import google fonts - Heading first/ text second -->
        <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700|Droid+Sans:400,700' />
        <!--[if lt IE 9]>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Droid+Sans:400" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Droid+Sans:700" rel="stylesheet" type="text/css" />
<![endif]-->
        <!-- Css files -->
        <!-- Icons -->
        <link href="assets/css/icons.css" rel="stylesheet" />
        <!-- jQueryUI -->
        <link href="assets/css/sprflat-theme/jquery.ui.all.css" rel="stylesheet" />
        <!-- Bootstrap stylesheets (included template modifications) -->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- Plugins stylesheets (all plugin custom css) -->
        <link href="assets/css/plugins.css" rel="stylesheet" />
        <!-- Main stylesheets (template main css file) -->
        <link href="assets/css/main.css" rel="stylesheet" />
        <!-- Custom stylesheets ( Put your own changes here ) -->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/img/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/img/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/img/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/img/ico/apple-touch-icon-57-precomposed.png">
        <link rel="icon" href="assets/img/ico/favicon.ico" type="image/png">
        <!-- Windows8 touch icon ( http://www.buildmypinnedsite.com/ )-->
        <meta name="msapplication-TileColor" content="#3399cc" />
        
  </head>
  
       <body class="login-page"> 
  
      <%
      
      
            
    request.setCharacterEncoding("utf-8");
    

    String userName="";
    String passWord="";
    Cookie[] cookies = request.getCookies();
    if(cookies!=null && cookies.length>0)
    {
    for(Cookie c:cookies)
    {
        System.out.println("cookieName="+c.getName() +"|cookieValue="+c.getValue());
    if(c.getName().equals("lxmUserName") && c.getValue().length()>0)
    {
    userName = URLDecoder.decode(c.getValue(),"utf-8");
    }
    if(c.getName().equals("lxmPassWord") && c.getValue().length()>0)
    {
    passWord = URLDecoder.decode(c.getValue(),"utf-8");
    }
    }
    }
            System.out.println("=====================");
    %>
    
        <!-- Start #login -->
        <div id="login" class="animated bounceIn">
            <img id="logo" src="assets/img/logo.png" alt="sprFlat Logo">
            <!-- Start .login-wrapper -->
            <div class="login-wrapper">
                <ul id="myTab" class="nav nav-tabs nav-justified bn">
                    <li>
                        <a href="#log-in" data-toggle="tab">登录</a>
                    </li>
                    <li class="">
                        <a href="#register" data-toggle="tab">注册</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content bn">
                    <div class="tab-pane fade active in" id="log-in">
                        <div class="social-buttons text-center mt10">
                            <a href="#" class="btn btn-primary btn-alt btn-round btn-lg mr10"><i class="fa-facebook s24"></i></a>
                            <a href="#" class="btn btn-primary btn-alt btn-round btn-lg mr10"><i class="fa-twitter s24"></i></a>
                            <a href="#" class="btn btn-danger btn-alt btn-round btn-lg mr10"><i class="fa-google-plus s24"></i></a>
                            <a href="#" class="btn btn-info btn-alt btn-round btn-lg"><i class="fa-linkedin s24"></i></a>
                        </div>
                        <div class="seperator">
                            <strong>or</strong>
                            <hr>
                        </div>
                        <form class="form-horizontal mt10" action="user/register?action=login" id="form1" name="form1" role="form" method="POST">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="text" name="username" id="username" class="form-control left-icon" value="<%=userName%>" placeholder="请输入你的账号 ...">
                                    <i class="ec-user s16 left-input-icon"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="password" name="password" id="password" class="form-control left-icon" value="<%=passWord%>" placeholder="你的密码">
                                    <i class="ec-locked s16 left-input-icon"></i>
                                    <span class="help-block"><a href="reset.jsp"><small>忘记密码 ?</small></a></span> 
                                    <input id="tip" style="background:none;border:0; width: 172px" type="text" disabled name="" size="">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
                                    <!-- col-lg-12 start here -->
                                    <label class="checkbox">
                                        <input type="checkbox" name="remember" id="remember" value="option">记住密码 ?
                                    </label>
                                </div>
                                <!-- col-lg-12 end here -->
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-4">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-success btn-block" type="button" onclick="getInfo()">登录</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="register">
                        <form class="form-horizontal mt20" action="user/register?action=register" name="form2" id="form2" role="form" method="POST">
						   <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="email1" name="username" type="username" class="form-control left-icon" onBlur="getRegisterInfo()" placeholder="输入你的账号">        
                                    <i class="ec-user s16 left-input-icon"></i> 
                                        <input id='tip1' style='background:none;border:0;' type='text' disabled name='' size='' >
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>

                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="email1" name="email" type="email" class="form-control left-icon" placeholder="输入你的邮箱地址">
                                    <i class="ec-mail s16 left-input-icon"></i> 
                                </div>
                                
                                <div class="col-lg-12 mt15">
                                    <!-- col-lg-12 start here -->
                                    <input type="password" class="form-control left-icon" id="password" name="password" placeholder="输入你的密码">
                                    <i class="ec-locked s16 left-input-icon"></i> 
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-success btn-block" onclick="submitInfo()">注册</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- End #.login-wrapper -->
        </div>
        <!-- End #login -->
        <!-- Javascripts -->
        <!-- Load pace first -->
        <script src="assets/plugins/core/pace/pace.min.js"></script>
        <!-- Important javascript libs(put in all pages) -->
        <script src="assets/js/jquery-1.8.3.min.js"></script>
        <script>
        window.jQuery || document.write('<script src="assets/js/libs/jquery-2.1.1.min.js">\x3C/script>')
        </script>
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script>
        window.jQuery || document.write('<script src="assets/js/libs/jquery-ui-1.10.4.min.js">\x3C/script>')
        </script>
        <!--[if lt IE 9]>
  <script type="text/javascript" src="assets/js/libs/excanvas.min.js"></script>
  <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <script type="text/javascript" src="assets/js/libs/respond.min.js"></script>
<![endif]-->
        <!-- Bootstrap plugins -->
        <script src="assets/js/bootstrap/bootstrap.js"></script>
        <!-- Form plugins -->
        <script src="assets/plugins/forms/icheck/jquery.icheck.js"></script>
        <script src="assets/plugins/forms/validation/jquery.validate.js"></script>
        <script src="assets/plugins/forms/validation/additional-methods.min.js"></script>
        <!-- Init plugins olny for this page -->
        <script src="assets/js/pages/login.js"></script>

    
    
    
    <script language="javascript" src="js/AjaxRequest.js"></script>
    <script language="javascript">
    
    function onerror()
    {
    alert("ajax发生错误");
    }
    
    function getInfo()
    {
    var url = "user/ajax?action=login&nocache=" + new Date().getTime()+"&username="+form1.username.value+"&password="+form1.password.value;
    var loader = new net.AjaxRequest(url,deal_getInfo,onerror,"POST");
    }
    
    function deal_getInfo()
    {
    var t = document.getElementById("tip")
    if(this.req.responseText.includes("成功"))
    {
    
    var check = document.getElementById("remember");
    if(check.checked==true)
    {
    setCookie("lxmUserName",form1.username.value,864000);
    setCookie("lxmPassWord",form1.password.value,864000);
    }
    //form1.submit();
    window.location.href="profile.html"
   
    }
    
    else
    {
    t.style.color='red';
    t.value=this.req.responseText;
    }

    }
    
function setCookie(name, value, iDay) 
{
    var oDate=new Date();
     
    oDate.setDate(oDate.getDate()+iDay);
     
    document.cookie=name+'='+encodeURIComponent(value)+';expires='+oDate;
}

    function submitInfo()
    {
    var url = "user/ajax?action=register&nocache=" + new Date().getTime()+"&username="+form2.username.value;
    var loader = new net.AjaxRequest(url,deal_submitInfo,onerror,"POST");
    }
    function deal_submitInfo()
    {
    var t = document.getElementById("tip1")
    
    if(this.req.responseText.includes("不存在"))
    form2.submit();
    else
    {
    t.style.color='red';
    t.value=this.req.responseText;
    }

    }
    
    
    function getRegisterInfo()
    {
    var url = "user/ajax?action=register&nocache=" + new Date().getTime()+"&username="+form2.username.value;
    var loader = new net.AjaxRequest(url,deal_getRegisterInfo,onerror,"POST");
    }
    function deal_getRegisterInfo()
    {
    var t = document.getElementById("tip1");
    if(this.req.responseText.includes("不存在"))
    {
        t.style.color='green';
        t.value="该用户名可以使用";
    }
    else
    {
    t.style.color='red';
    t.value="该用户名已经被注册！";
    }

    }
    

    </script>
  </body>
</html>