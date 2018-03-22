<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>统一授权中心</title>
</head>
<link rel="stylesheet" href="${basePath}/static/css/style.css"/>
<script type="text/javascript" src="${path}/static/js/framework/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${path}/static/js/component/jquery.validate.min.js"></script>
<script type="text/javascript" src="${path}/static/js/component/messages_zh.js"></script>
<body>
<div>
	<div id="head" aglin="center">
		<h1 color="red">统一授权中心</h1>
	</div>
    <div id="content" algin="center">
		<form id="login" action="${path}/login" method="post">
			username:<input type="text" name="username" value="Jason" /> </br>
			password:<input type="password" name="password" value="123456" />
			<input name="clientUrl" value="${clientUrl}" />
			</br>
			<input type="submit" value="Login" />
		</form>
	</div>
	
	<#--  数据校验测试 -->
	<form id="ff" action="http://ucnaer.cn" method="post">
	        姓名：<input type="text" name="sname" /><br/>
	        密码：<input type="password" name="spass" id="spass"/><br/>
	        确认密码：<input type="password" name="spass2" /><br/>
	        爱好：
	       上网：<input type="checkbox" name="slike" value="上网"/>
	        唱歌：<input type="checkbox" name="slike" value="唱歌"/>
	        编程：<input type="checkbox" name="slike" value="编程"/>
	        书法：<input type="checkbox" name="slike" value="书法"/><br/>
	        邮箱：<input type="text" name="semail" /><br/>
	        头像：<input type="file" name="simage" /><br/>
	        <input type="submit" value="提交">
    </form>
</div>
</body>
<script>
 $(function(){
    //让当前表单调用validate方法，实现表单验证功能
    $("#ff").validate({
        debug:true, //调试模式，即使验证成功也不会跳转到目标页面
        
        rules:{     //配置验证规则，key就是被验证的dom对象，value就是调用验证的方法(也是json格式)
            sname:{
                required:true,  //必填。如果验证方法不需要参数，则配置为true
                rangelength:[6,12]
            },
            spass:{
                required:true,
                rangelength:[6,12]
            },
            spass2:{
                required:true,
                equalTo:'#spass' //表示和id="spass"的值相同
            },
            slike:{
                required:true,
            },
            semail:{
                required:true,
                email:true
            },
            simage:{
                required:true,
                extension:'gif|jpe?g|png'
            }
        }
    });
});
</script>
</html>