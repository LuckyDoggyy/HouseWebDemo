<#import "/spring.ftl" as spring>
<#escape x as x?html>
    <#macro  page title>
        <!DOCTYPE html>
        <html>
            <head>
                <meta charset="utf-8">
                <title>${title}</title>
            </head>

            <body>
                    <table>
                        <thead>
                        <tr>
                            <td>id</td>
                            <td>title</td>
                            <td>area</td>
                            <td>price</td>
                            <td>floor</td>
                            <td>t_floor</td>
                            <td>description</td>
                            <td>broker_name</td>
                            <td>broker_phone</td>
                            <td>bedroom</td>
                            <td>living_room</td>
                            <td>community</td>
                            <td>address</td>
                            <td>year</td>
                            <td>pub_time</td>
                        </tr>
                        </thead>
                        <tbody>
                        <#list houseInfos as house>
                            <tr>
                                <td>house.id</td>
                                <td>house.title</td>
                                <td>house.area</td>
                                <td>house.price</td>
                                <td>house.floor</td>
                                <td>house.t_floor</td>
                                <td>house.description</td>
                                <td>house.broker_name</td>
                                <td>house.broker_phone</td>
                                <td>house.bedroom</td>
                                <td>house.living_room</td>
                                <td>house.year</td>
                                <td>house.community</td>
                                <td>house.address</td>
                                <td>house.pub_time</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>

            </body>

        </html>

    </#macro>



</#escape>