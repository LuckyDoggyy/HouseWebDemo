<#import "/spring.ftl" as spring />
<#macro page title>
<#escape x as x?html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
    <meta http-equiv="expires" content="0"/>
    <title>${title}</title>
    <style>
        body {
            padding-top: 60px;
            /* 60px to make the container go all the way to the bottom of the topbar */
        }

        .nowrap {
            white-space: nowrap;
        }

        .dom-hide {
            display: none;
        }

        .the-table {
            table-layout: fixed;
            word-wrap: break-word;
        }
    </style>
    <link rel="icon" href="http://pages.anjukestatic.com/favicon.ico" type="image/ico"/>
    <link rel="shortcut icon" href="http://pages.anjukestatic.com/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap styles -->
<#--<link href="<@spring.url "/common/bootstrap-3.3.7/dist/css/bootstrap.min.css"/>" rel="stylesheet" >-->
    <!-- Generic page styles -->
    <link rel="stylesheet" href="/common/jQuery-File-Upload-master/css/style.css">
    <!-- blueimp Gallery styles -->
    <link rel="stylesheet" href="/common/Gallery-master/css/blueimp-gallery.min.css">
    <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
    <link rel="stylesheet" href="/common/jQuery-File-Upload-master/css/jquery.fileupload.css">
    <link rel="stylesheet" href="/common/jQuery-File-Upload-master/css/jquery.fileupload-ui.css">
    <link href="/common/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/common/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"/>
    <script src="/common/jquery-3.2.0.min.js"></script>
    <script src="/common/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/common/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.fr.js"></script>
    <!-- CSS adjustments for browsers with JavaScript disabled -->
    <noscript>
        <link rel="stylesheet" href="/common/jQuery-File-Upload-master/css/jquery.fileupload-noscript.css">
    </noscript>
    <noscript>
        <link rel="stylesheet" href="/common/jQuery-File-Upload-master/css/jquery.fileupload-ui-noscript.css">
    </noscript>
</head>
<body>
<#nested >
<ul>
    <@selection param="Area" count=3 conditions=["under 50m2", "50-100m2", "above100m2"] />
    <@selection param="Price" count=3 conditions=["under 100W", "100-150W", "above 150W"] />
    <@selection param="Bedroom" count=5 conditions=[1,2,3,4,">5"] />
</ul>
<div id="infoTable">

</div>
</body>
<script>
    $.ajax({
        url:'/selectBy',
        type:'get',
        success:function (result) {
            $('#infoTable').append(result);
        }
    });
</script>
</html>
</#escape>
</#macro>


<#--
<#macro infoTable >
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
        <#assign baseUrl="?pageSize=${pageSize}&pageNumber=">
    <div align="center">
        <ul class="pagination">
            <#if pageNumber == 1 >
                <li class="disabled"><a href="#">&laquo;</a></li>
                <li class="disabled"><a href="#">Previous</a></li>
            <#else>
                <li><a href="${baseUrl}1">&laquo;</a></li>
                <li><a href="${baseUrl}${pageNumber-1}">Previous</a></li>
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
                    <li class="active"><a href="#">${p}</a></li>
                <#else>
                    <li><a href="${baseUrl}${p}">${p}</a></li>
                </#if>
            </#list>
            <#if pageSum == pageNumber>
                <li class="disabled"><a href="#">Next</a></li>
                <li class="disabled"><a href="#">&raquo;</a></li>
            <#else>
                <li><a href="${baseUrl}${pageNumber+1}">Next</a></li>
                <li><a href="${baseUrl}${pageSum}">&raquo;</a></li>
            </#if>
        </ul>
    </div>
    </div>
    </#if>
</#macro>
-->

<#macro selection param count conditions >
<li>
    <label>${param}:</label>
    <#list 1..count as key>
        <button type="submit" id="${param}${key}" value=${key} onclick="selectBy${param}(this.value)">${conditions[key - 1]}</button>
    </#list>
</li>
<script>
    var selectBy${param} = function(obj) {
        console.log(obj);
        console.log("${param}");
        $.ajax({
            url:"/selectBy",
            type: "get",
            async: true,
            data:{
                param: "${param}",
                zone: obj
            },
            timeout: 5000,
            success: function(result){
                $('#infoTable').empty();
                $('#infoTable').append(result);
            },
            error: function(result){
            }
        });
    }
</script>
</#macro>