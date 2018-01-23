<#import "spring.ftl" as spring />
<#import "common.ftl" as common />
<#escape x as x?html>
<@common.page "House Informations" >
<div>
    <#if broker?exists>
        <a href="/HouseWebDemo/addHouseInfoPage">${broker.name}</a>
        <a id="outhref" href="<#--${base}-->/HouseWebDemo/logout">退出</a>
        <#else >
        <a href="<#--${base}-->/HouseWebDemo/welcome">登录</a>
    </#if>
</div>
<script>
    $('#outhref').click(function(){
        location.reload(true);
    });
</script>
</@common.page>
</#escape>