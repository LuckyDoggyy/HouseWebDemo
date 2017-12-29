<html>
<head>
    <title>Add House Information</title>
    <script src="/common/jquery-3.2.0.min.js"></script>
</head>
<div align="center" style="width:500px">
    <form id="houseInfoForm">
        <label for="title" style="width: 100px">Title:</label><input id="title" type="text"/><br>
        <label for="area" style="width: 100px">Area:</label><input id="area" type="text"/><br>
        <label for="price" style="width: 100px">Price:</label><input id="price" type="text"/><br>
        <label for="floor" style="width: 100px">Floor:</label><input id="floor" type="text"/><br>
        <label for="totalFloor" style="width: 100px">TotalFloor:</label><input id="totalFloor" type="text"/><br>
        <label for="description" style="width: 100px">Description:</label><input id="description" type="text"/><br>
        <label for="selections" style="width: 100px">House:</label>
        <select id="house">
            <#list houseOpts as opt>
                <option value="${opt.id}">${opt.bedroom} ${opt.livroom} ${opt.community} ${opt.address} ${opt.buildYear}</option>
            </#list>
        </select></br>
        <input id="subBtn" type="submit" value="submit"/>
    </form>
</div>
<script>
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