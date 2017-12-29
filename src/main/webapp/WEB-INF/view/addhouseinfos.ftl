<html>
<head>
    <title>Add House Information</title>
    <script src="/common/jquery-3.2.0.min.js"></script>
</head>
<div style="width:100%;float:left;">
    <form id="houseInfoForm">
        <label for="title" style="width: 110px;float:left;">Title:</label>
        <input id="title" type="text" style="width:390px;"/><br>
        <label for="area" style="width: 110px;float:left;">Area:</label>
        <input id="area" type="text" style="width:390px;"/><br>
        <label for="price" style="width: 110px;float:left;">Price:</label>
        <input id="price" type="text" style="width:390px;"/><br>
        <label for="floor" style="width: 110px;float:left;">Floor:</label>
        <input id="floor" type="text" style="width:390px;"/><br>
        <label for="totalFloor" style="width: 110px;float:left;">TotalFloor:</label>
        <input id="totalFloor" type="text" style="width:390px;"/><br>
        <label for="description" style="width: 110px;float:left;">Description:</label>
        <input id="description" type="text" style="width:390px;"/><br>
        <label for="selections" style="width: 110px;float:left;">House:</label>
        <select id="house" style="width:390px;">
        <#list houseOpts as opt>
            <option value="${opt.id}">${opt.bedroom} ${opt.livroom} ${opt.community} ${opt.address} ${opt.buildYear}</option>
        </#list>
        </select></br>
        <input id="subBtn" type="submit" value="submit"/>
    </form>
</div>
<div>
    <button id="addHouseBtn">Add A New House</button>
</div>
<div id="houseDiv" style="width:500px;display:none;">
    <form id="houseForm">
        <label for="livroom" style="width:110px;float:left;">Living Room:</label>
        <label><input type="radio" id="livroom" value="1">1</label>
        <label><input type="radio" id="livroom" value="2">2</label>
        <label><input type="radio" id="livroom" value="3">3</label></br>
        <label for="bedroom" style="width:110px;float:left;">Bedroom:</label>
        <label><input type="radio" id="bedroom" value="1">1</label>
        <label><input type="radio" id="bedroom" value="2">2</label>
        <label><input type="radio" id="bedroom" value="3">3</label>
        <label><input type="radio" id="bedroom" value="4">4</label>
        <label><input type="radio" id="bedroom" value="5">5</label></br>
        <lable for="community" style="width:110px;float:left;">Community:</lable>
        <input id="community" type="text" style="width:390px;float:left;"/></br>
        <lable for="address" style="width:110px;float:left;">Address:</lable>
        <input id="address" type="text" style="width:390px;float:left;"/></br>
        <label for="buildYear" style="width:110px;float:left;">Build Year:</label>
        <select id="buildYear" style="width:390px;">
        </select><br>
        <button id="addHouseSubBtn">Submit</button>
    </form>
</div>
<script>
    $('#subBtn').click(function () {
        console.log("posting");
        var param = {
            title: $('#title').val(),
            area: $('#area').val(),
            price: $('#price').val(),
            floor: $('#floor').val(),
            totalFloor: $('#totalFloor').val(),
            description: $('#description').val(),
            house: $('#house').val()
        };
        $.ajax({
            type: 'post',
            url: '/addHouseInfo',
            data: param,
            success: function (result) {
                console.log(result.status);
                window.alert(result.status);
                return false;
            },
            error: function () {
                return false;
            }
        });
        return false;
    });
    for (var i = 1980; i < new Date().getFullYear(); i++)
        $('#buildYear').append('<option value="' + i + '">' + i + '</option>');
    $('#addHouseBtn').click(function () {
        if($('#houseDiv').is(':hidden'))
            $('#houseDiv').show();
        else
            $('#houseDiv').hide();
    });
    $('#addHouseSubBtn').click(function(){
        console.log('add new house committed.');
        var param = {
            livroom : $('#livroom').val(),
            bedroom : $('#bedroom').val(),
            community : $('#community').val(),
            address : $('#address').val(),
            buildYear : $('#buildYear').val()
        };
        $.ajax({
            type:'post',
            url:'/addHouse',
            data:'param',
            success:function(result){
                console.log(result);
                window.alert(result.status);
                return false;
            },
            error:function(result){
                console.log(result);
            }
        });
    });

</script>
</html>