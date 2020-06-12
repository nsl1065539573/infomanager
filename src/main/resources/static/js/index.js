$(document).ready(function () {
    getData();
    altRows("info_table");
    $("#add").click(function () {
       add();
    });
    $(".updateBtn").on("click", function () {
        alert("update")
    });
    $("#update").click(function () {
        updateCommit();
    });
});
//设置交错行的表格颜色不一样
function altRows(id){
	if(document.getElementsByName){
		var table = document.getElementById(id); 
        var rows = table.getElementsByTagName("tr");
		 for(i = 0; i < rows.length; i++){
		     console.log(1111);
		     console.log(JSON.stringify(rows[i]));
            if(i % 2 === 0){
                rows[i].className = "evenrowcolor";
            }else{
                rows[i].className = "oddrowcolor";
            }     
        }
	}
}

// 请求数据添加到表格中
function  getData() {
    $.ajax({
        url:"./findAll",
        type:"GET",
        success:function (result) {
            var data = result.data;
            // 遍历数据，将其放在table中
            $.each(data, function (i, item) {
                var $tr = $("<tr></tr>");
                $.each(item, function (i, item) {
                    var $td = $("<td></td>");
                    $td.append(item);
                    $td.appendTo($tr);
                });
                var $td1 = $("<td><button class='updateBtn btnStyle'>修改</button> <button class='delete btnStyle'>删除</button></td>");
                var table = document.getElementById("info_table");
                var size = table.getElementsByTagName("tr").length - 1;
                if (size %2 === 0){
                    $tr.addClass("oddrowcolor");
                } else {
                    $tr.addClass("evenrowcolor");
                }
                $td1.appendTo($tr);
                $tr.appendTo(table);
            });
            $(".delete").click(function () {
                remove($(this));
            });
            $(".updateBtn").click(function () {
                update($(this));
            });
        }
    });

}

// 点击新增按钮
function  add() {
    alert(111);
    var $tr = $("<tr></tr>");
    var $tdId = $("<td>new</td>");
    $tdId.appendTo($tr);
    $(".addInput").each(function (index, domEle) {
        var $td = $("<td></td>");
        $td.append($(domEle).val());
        $td.appendTo($tr);
    });
    var opt_type = $('#opt_type option:selected').val();
    var $tdOpt = $("<td></td>");
    $tdOpt.append(opt_type);
    $tdOpt.appendTo($tr);
    var date = new Date();
    for (var i = 0; i < 2; i++){
        var $td = $("<td></td>");
        $td.append(date.toLocaleDateString());
        $td.appendTo($tr);
    }
    var $td1 = $("<td><button class='updateBtn btnStyle'>修改</button> <button class='delete btnStyle'>删除</button></td>");
    $td1.appendTo($tr);
    var table = document.getElementById("info_table");
    var size = table.getElementsByTagName("tr").length - 1;
    if (size % 2 === 0){
        $tr.addClass("oddrowcolor");
    } else {
        $tr.addClass("evenrowcolor");
    }
    $tr.appendTo($("#info_table"));
    console.log($tr);
    // 前端界面添加后，发送到后端接口去新增
    var fileName = $("#fileName").val();
    var description = $("#description").val();
    var details = $("#details").val();
    $.ajax({
        url:'./create',
        type:'POST',
        data:{
            fileName:fileName,
            description:description,
            details:details,
            opt_type:opt_type
        },
        success:function (result) {
            var resId = result.id;
            if (resId != null){
                $tdId.text(resId);
            }
            console.log(JSON.stringify(result));
        }
    });
    $(".delete").click(function () {
        remove($(this));
    });
    $(".updateBtn").click(function () {
        update($(this));
    });
}

// 点击删除按钮
function remove(item) {
    var $tr = item.parent().parent();
    var id = $tr.find("td").eq(0).text();
    $.ajax({
        url:"./delete",
        type:"POST",
        data:{
            id:id
        },
        success:function () {
            item.parent().parent().remove();
        }
    });
}

// 点击修改按钮
function update(item) {
    alert("click update button");
    $("#shadow").css({'display':'block'});
    $('#addBox').show();
    // 获取修改按钮的那一行
    var $tr = item.parent().parent();
    // 获取可以修改的列
    // 文件名
    var fileName = $tr.find("td").eq(1).text();
    // 修改原因
    var description = $tr.find("td").eq(2).text();
    // 修改内容
    var details = $tr.find("td").eq(3).text();
    // 操作类型
    var opt_type = $tr.find("td").eq(4).text();
    // id
    var id = $tr.find("td").eq(0).text();
    // 将列填充到文本框中
    $("#upFileName").val(fileName);
    $("#upDescription").val(description);
    $("#upDetails").val(details);
    $("#upOpt_type").val(opt_type);
    $("#update").attr("data-value", id);
}

function updateCommit() {
    var fileName = $("#upFileName").val();
    var description = $("#upDescription").val();
    var details = $("#upDetails").val();
    var opt_type = $("#upOpt_type").val();
    console.log("打印opt_type" + opt_type);
    var id = $("#update").attr("data-value");
    if (fileName === "" || fileName === null){
        alert("请输入文件名");
    }
    if (description === "" || description === null){
        alert("请输入修改原因");
    }
    if (details === "" || details === null){
        alert("请输入修改明细");
    }
    if (opt_type === "" || opt_type === null){
        alert("请输入操作类型");
    }
    $.ajax({
        url:"./update",
        type:"PUT",
        data:{
            id:id,
            fileName:fileName,
            description:description,
            details:details,
            opt_type:opt_type
        },
        success:function (result) {
            alert("更新成功！");
            location.reload();
        }
    })
}