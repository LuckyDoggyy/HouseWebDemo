<#import "spring.ftl" as spring />
<#import "common.ftl" as common />
<@common.page "House Informations" >
<div>
    <#if broker?exists>
        <div><a href="/#">${broker.name}</a></div>
        <div></div><a id="outhref" href="/logout">退出</a></div>
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
