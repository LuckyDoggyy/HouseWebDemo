<#import "spring.ftl" as spring />
<#import "common.ftl" as common />
<@common.page "House Informations" >
<div>
    <#if broker?exists>
        <a href="/addHouseInfoPage">${broker.name}</a>
        <a id="outhref" href="/logout">退出</a>
        <#else >
        <a href="/welcome">登录</a>
    </#if>
</div>
<script>
    $('#outhref').click(function(){
        location.reload(true);
    });
</script>
</@common.page>
