<#import "/spring.ftl" as spring />
<#escape x as x?html>
<#macro page title>
    <#assign base=request.contextPath />
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
        <meta http-equiv="expires" content="0"/>
        <title>version2.0</title>
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
        <link rel="stylesheet" href="${base}/common/jQuery-File-Upload-master/css/style.css">
        <!-- blueimp Gallery styles -->


        <#--<link rel="stylesheet" href="/common/Gallery-master/css/blueimp-gallery.min.css">-->


        <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
        <link rel="stylesheet" href="${base}/common/jQuery-File-Upload-master/css/jquery.fileupload.css">
        <link rel="stylesheet" href="${base}/common/jQuery-File-Upload-master/css/jquery.fileupload-ui.css">
        <link href="${base}/common/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${base}/common/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css" rel="stylesheet"/>
        <link href="${base}/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
              media="screen"/>
        <script src="${base}/common/jquery-3.2.0.min.js"></script>
        <script src="${base}/common/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>


        <#--<script src="/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>-->
        <#--<script src="/common/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.fr.js"></script>-->


        <!-- CSS adjustments for browsers with JavaScript disabled -->
        <noscript>
            <link rel="stylesheet" href="${base}/common/jQuery-File-Upload-master/css/jquery.fileupload-noscript.css">
        </noscript>
        <noscript>
            <link rel="stylesheet" href="${base}/common/jQuery-File-Upload-master/css/jquery.fileupload-ui-noscript.css">
        </noscript>
    </head>
    <body>
        <#nested >
    <#--<form id="selectForm">-->
        <ul>
            <@selection param="Area" count=3 conditions=["under 50m2", "50-100m2", "above100m2"] />
            <@selection param="Price" count=3 conditions=["under 100W", "100-150W", "above 150W"] />
            <@selection param="Bedroom" count=5 conditions=[1,2,3,4,">5"] />
        </ul>
    <#--</form>-->
    <div id="infoTable">

    </div>
    </body>
    <script>
        var global={param:'',zone:''};
        $.ajax({
            url: '<#--${base}-->/HouseWebDemo/selectBy',
            type: 'get',
            async : true,
            timeout:5000,
            success: function (result) {
                $('#infoTable').append(result);
                return false;
            }
        });
        var getParam = function(obj){
            global ={
                zone:$(obj).prev().val(),
                param:$(obj).prev().prev().val()
            };
            $.ajax({
                url : "<#--${base}-->/HouseWebDemo/selectBy",
                type : "get",
                async : true,
                data : global,
                timeout : 5000,
                success : function (result) {
                    $('#infoTable').empty();
                    $('#infoTable').append(result);
                    return false;
                }
            });
            return false;
        };
        var getPage = function(obj){
            var temp = $(obj).parent('li');
            window.alert(temp.attr('value'));
            var url = temp.attr('value');
            if(global.param != '' && global.zone != ''){
                url += '&param='+global.param+'&zone='+global.zone;
            }
//            obj.href='#';
            window.alert(<#--${base}-->url);

            $.ajax({
                url: <#--${base}-->url,
                type:'get',
                async : true,
                success:function(result){
                    $('#infoTable').empty();
                    $('#infoTable').append(result);
                    return false;
                }
            });
        }

    </script>
    </html>
</#macro>
</#escape>


<#macro selection param count conditions >
<li>
    <div style="float: left ">
<label>${param}:</label></div>
    <#list 1..count as key>
    <div style="float: left">
            <input type="hidden" id="${param}" value=${param} />
            <input type="hidden" id="zone" value="${key}" />
            <input type="button" value="${conditions[key - 1]}" onclick="getParam(this)" />
    </div>
    </#list>
</li>
<br>
</#macro>
