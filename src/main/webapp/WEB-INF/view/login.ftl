<#escape x as x?html>
<#assign base=request.contextPath />
<html>
<head>
    <title>Broker Login</title>
    <script src="${base}/common/jquery-3.2.0.min.js"></script>
</head>
    <div style="width:200px;float:left;">
        <form id="loginForm">
            <label>Username:</label><input id="username" type="text" id="username" /><br>
            <label>Password:</label><input id="password" type="password" id="password" /><br>
            <input id="subBtn" type="submit" value="submit" />
        </form>
    </div>
<script>
    $('#subBtn').click(function(){
        var username = $('#username').val();
        var password = $('#password').val();
        var param = {username: username,password: password};
        $.ajax({
            type:"post",
            url:"<#--${base}-->/HouseWebDemo/login",
            data: param,
            success:function(result){
                console.log(result.status);
                if(result.status == "success"){
                    window.alert("Login successful. Jump to house information.");
                    window.location.href="/HouseWebDemo/houseInfos";
                    return false;
                }else{
                    window.alert(result.status);
                }
            },
            error:function(result){
            }
        });
        return false;
    })
</script>
</html>
</#escape>