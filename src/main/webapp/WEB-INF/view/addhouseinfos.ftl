<html>
<head>
    <title>Add House Information</title>
    <script src="/common/jquery-3.2.0.min.js"></script>
</head>
<div style="width:500px;float:left;">
    <form id="houseInfoForm">
        <label for="title" style="width: 100px;float:left;">Title:</label><input id="title" type="text" style="width:400px;"/><br>
        <label for="area" style="width: 100px;float:left;">Area:</label><input id="area" type="text" style="width:400px;"/><br>
        <label for="price" style="width: 100px;float:left;">Price:</label><input id="price" type="text" style="width:400px;"/><br>
        <label for="floor" style="width: 100px;float:left;">Floor:</label><input id="floor" type="text" style="width:400px;"/><br>
        <label for="totalFloor" style="width: 100px;float:left;">TotalFloor:</label><input id="totalFloor" type="text" style="width:400px;"/><br>
        <label for="description" style="width: 100px;float:left;">Description:</label><input id="description" type="text" style="width:400px;"/><br>
        <label for="selections" style="width: 100px;float:left;">House:</label>
        <select id="house" style="width:400px;">
            <#list houseOpts as opt>
                <option value="${opt.id}">${opt.bedroom} ${opt.livroom} ${opt.community} ${opt.address} ${opt.buildYear}</option>
            </#list>
        </select></br>
        <input id="subBtn" type="submit" value="submit"/>
    </form>
</div>
<div style="width:500px;">
    <button id="addHouseBtn" >Add A New House</button>
    <form id="houseForm">

    </form>
</div>
<script>
    $('#addHouseBtn').click(function(){
        var houseForm =
                '        <label for="livroom" style="width: 100px;float:left;">Living Room:</label>\n' +
                '        <label><input type="radio" id="livroom" value="1">1</label>\n' +
                '        <label><input type="radio" id="livroom" value="2">2</label>\n' +
                '        <label><input type="radio" id="livroom" value="3">3</label></br>\n' +
                '        <label for="bedroom" style="width: 100px;float:left;">Bedroom:</label>\n' +
                '        <label><input type="radio" id="bedroom" value="1">1</label>\n' +
                '        <label><input type="radio" id="bedroom" value="2">2</label>\n' +
                '        <label><input type="radio" id="bedroom" value="3">3</label>\n' +
                '        <label><input type="radio" id="bedroom" value="4">4</label>\n' +
                '        <label><input type="radio" id="bedroom" value="5">5</label></br>\n' +
                '        <lable for="community" style="width: 100px;float:left;">Community:</lable>\n' +
                '        <input id="community" type="text" style="width:300px;float:left;"/></br>\n' +
                '        <lable for="address" style="width: 100px;float:left;">Address:</lable>\n' +
                '        <input id="address" type="text" style="width:300px;float:left;"/></br>\n' +
                '        <label for="buildYear" style="width: 100px;float:left;">Build Year:</label>\n' +
                '        <select id="buildYear">' +
                '        </select><br>\n' +
                '        <button id="addHouseSubBtn">Submit</button>\n';
        $('#houseForm').append(houseForm)
        for(var i = 1980; i < new Date().getFullYear() ; i ++)
            $('#buildYear').append('<option value="' + i + '">'+i+'</option>');

    });
    $('#subBtn').click(function(){
        console.log("posting");
        var param = {
            title:$('#title').val(),
            area:$('#area').val(),
            price:$('#price').val(),
            floor:$('#floor').val(),
            totalFloor:$('#totalFloor').val(),
            description:$('#description').val(),
            house:$('#house').val()
        }
        $.ajax({
            type:'post',
            url:'/addHouseInfo',
            data:param,
            success:function(result){
                console.log(result.status);
                window.alert(result.status);
                return false;
            },
            error:function(){
                return false;
            }
        });
        return false;
    });
</script>
</html>