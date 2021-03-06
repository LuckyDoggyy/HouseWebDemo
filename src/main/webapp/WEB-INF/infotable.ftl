<#escape x as x?html>
<table border="1">
    <thead>
    <tr>
        <td width="90" align="center">id</td>
        <td width="90" align="center">title</td>
        <td width="90" align="center">area</td>
        <td width="90" align="center">price</td>
        <td width="90" align="center">floor</td>
        <td width="90" align="center">t_floor</td>
        <td width="90" align="center">bedroom</td>
        <td width="90" align="center">living_room</td>
        <td width="90" align="center">community</td>
        <td width="90" align="center">address</td>
        <td width="90" align="center">year</td>
        <td width="90" align="center">description</td>
        <td width="90" align="center">pub_time</td>
        <td width="90" align="center">broker_name</td>
        <td width="90" align="center">broker_phone</td>
    </tr>
    </thead>
    <tbody>
        <#list houseInfos as houseInfo>
        <tr>
            <td width="90" align="center">${houseInfo.id}</td>
            <td width="90" align="center">${houseInfo.title}</td>
            <td width="90" align="center">${houseInfo.area}</td>
            <td width="90" align="center">${houseInfo.price}</td>
            <td width="90" align="center">${houseInfo.floor}</td>
            <td width="90" align="center">${houseInfo.totalFloor}</td>
            <td width="90" align="center">${houseInfo.bedroom}</td>
            <td width="90" align="center">${houseInfo.livroom}</td>
            <td width="90" align="center">${houseInfo.community}</td>
            <td width="90" align="center">${houseInfo.address}</td>
            <td width="90" align="center">${houseInfo.buildYear}</td>
            <td width="90" align="center">${houseInfo.description}</td>
            <td width="90" align="center">${houseInfo.pubTime}</td>
            <td width="90" align="center">${houseInfo.name}</td>
            <td width="90" align="center">${houseInfo.phone}</td>
        </tr>
        </#list>
    </tbody>
</table>
<#if pageSum gt 1>
    <#assign baseUrl="/HouseWebDemo/selectBy?pageSize=${pageSize}&pageNumber=">
<div align="center">
    <ul class="pagination">
        <#if pageNumber == 1 >
            <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>
            <li class="disabled"><a href="javascript:void(0);">Previous</a></li>
        <#else>
            <li value="${baseUrl}1"><a href="javascript:void(0);" onclick="getPage(this)">&laquo;</a></li>
            <li value="${baseUrl}${pageNumber - 1}"><a href="javascript:void(0);" onclick="getPage(this)">Previous</a></li>
        </#if>
        <#assign startPage=pageNumber-3/>
        <#if startPage<1 >
            <#assign startPage=1/>
        </#if>
        <#assign endPage=pageNumber+5/>
        <#if (endPage>pageSum-1) >
            <#assign endPage=pageSum/>
            <#if endPage==0>
                <#assign endPage=0/>
            </#if>
        </#if>
        <#list startPage..endPage as p>
            <#if p == pageNumber>
                <li class="active"><a href="javascript:void(0);">${p}</a></li>
            <#else>
                <li value="${baseUrl}${p}"><a href="javascript:void(0);" onclick="getPage(this)">${p}</a></li>
            </#if>
        </#list>
        <#if pageSum == pageNumber>
            <li class="disabled"><a href="javascript:void(0)">Next</a></li>
            <li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>
        <#else>
            <li value="${baseUrl}${pageNumber + 1}"><a href="javascript:void(0);" onclick="getPage(this)">Next</a></li>
            <li value="${baseUrl}${pageSum}"><a href="javascript:void(0);" onclick="getPage(this)">&raquo;</a></li>
        </#if>
    </ul>
</div>
</#if>
</#escape>
