<#import "/spring.ftl" as spring />
<#escape x as x?html>
    <#macro page title>
    <!DOCTYPE html>
    <html>
    <head>

        <meta charset="utf-8">

        <title>${title}</title>
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
        </noscript
    </head>


    <body>
        <ul>
            <@selection param="Area" count=3 conditions=["under 50m2","50-100m2","above 100m2"] />
            <@selection param="Price" count=3 conditions=["under 100W","100-150W","above 150W"] />
            <@selection param="Bedroom" count=5 conditions=[1,2,3,4,">5"]/>
        </ul>
    </form>
        <#nested>
        <#--<@infoTable page="" houseInfos="" />-->
    </body>
    </html>
    </#macro>
</#escape>


<#macro selection param count conditions>
<from id="${param}From" action="/selectBy" method="get">
    <li>
        <label>${param}:</label>
            <#list 1..count as key>
                <button type="submit" id="${param}${key}" value="${key}" on-click="selectBy${param}(this)">${conditions[key - 1]}</button>

            </#list>
        <script>
            selectBy${param} : function(obj) {

                $.ajax({
                    url:'/selectBy${param}?selectButton='+obj.value,
                    type: 'get',
                    async:true,
                    data:$(obj.parent().parent()).serialize(),
                    timeout: 5000,
                    dataType:'json',
                    success: function(result){
                        page = result.page;
                        houseInfos = result.houseInfos;
                    },
                    error:function(result){
                    }
                })
            }
        </script>
    </li>
</from>

</#macro>

<#macro infoTable houseInfos>
<script>
    $.ajax({
        url:'/showAllHouseInfos',
        type:'get',
        async:true,
        timeout:5000,
        datatype:'json',
        success:function(result){
            ${houseInfos} = result;
        }
    })
</script>
<table border="1">
    <thead>
    <tr>
        <td width="90" align="center">id</td>
        <td width="90" align="center">title</td>
        <td width="90" align="center">area</td>
        <td width="90" align="center">price</td>
        <td width="90" align="center">floor</td>
        <td width="90" align="center">t_floor</td>
        <td width="90" align="center">description</td>
        <td width="90" align="center">broker_name</td>
        <td width="90" align="center">broker_phone</td>
        <td width="90" align="center">bedroom</td>
        <td width="90" align="center">living_room</td>
        <td width="90" align="center">community</td>
        <td width="90" align="center">address</td>
        <td width="90" align="center">year</td>
        <td width="90" align="center">pub_time</td>
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
            <#--<script>
                    var descId = houseInfo.descId;
                    $.ajax({
                        url:'/selectDesc?descId='+descId,
                        type: 'get',
                        async:true,
                        timeout: 5000,
                        dataType:'json',
                        success: function(result){
                            desc = result;
                        }
                    })
            </script>-->
            <td width="90" align="center">desc.description</td>
            <td width="90" align="center">${houseInfo.brokerId}</td>
            <#--<script>
                var brokerId=houseInfo.brokerId
                $.ajax({
                    url:'/selectBroker?brokerId='+brokerId,
                    type:'get',
                    async:true,
                    timeout:5000,
                    dataType:'json',
                    success:function(result){
                        broker = result;
                    }

                })
            </script>-->
            <td width="90" align="center">broker.name</td>
            <td width="90" align="center">broker.phone</td>
            <#--<script>
                var houseId = houseInfo.houseId;
                $.ajax({
                    url:'/selectHouse?houseId='+houseId,
                    type:'get',
                    async:true,
                    timeout:5000,
                    dataType:'json',
                    success:function(result){
                        house = result;
                    }
                })

            </script>-->
            <#--<td width="90" align="center">${house.bedroom}</td>
            <td width="90" align="center">${house.living_room}</td>
            <td width="90" align="center">${house.year}</td>
            <td width="90" align="center">${house.community}</td>
            <td width="90" align="center">${house.address}</td>-->
            <td width="90" align="center">${houseInfo.pubTime}</td>
        </tr>
        </#list>
    </tbody>
</table>
</#macro>



<#macro selection param count conditions>
<from id="${param}From" action="/selectBy" method="get">
    <li>
        <label>${param}:</label>
        <#list 1..count as key>
            <button type="submit" id="${param}${key}" value="${key}" on-click="selectBy${param}(this)">${conditions[key - 1]}</button>

        </#list>
        <script>
            selectBy${param} : function(obj) {

                $.ajax({
                    url:'/selectBy${param}?selectButton='+obj.value,
                    type: 'get',
                    async:true,
                    data:$(obj.parent().parent()).serialize(),
                    timeout: 5000,
                    dataType:'json',
                    success: function(result){
                        page = result.page;
                        houseInfos = result.houseInfos;
                    },
                    error:function(result){
                    }
                })
            }
        </script>
    </li>
</from>

</#macro>

