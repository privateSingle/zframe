/**
 *
 */
/**跳转到添加页面*/
function add(url){
    parent.layer.open({
        type: 2,
        title: '添加',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url
    });
}
/**跳转到修改页面*/
function edit(table_id,url){
    var id=getSelectedRow(table_id,url);
    if(id!=null){
        parent.layer.open({
            type: 2,
            title: '修改',
            shadeClose: false,
            shade: [0.3, '#000'],
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: url+"/"+id
        });
    }
}
/**删除*/
function deleteBatch(table_id,url){
    //获取选中的id
    var ids= getSelectedRows(table_id);
    if(ids!=null){
        confirm("确认删除？",function(){
            $.ajax({
                type: "post",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(ids),
                async: false,
                dataType:"json",
                success: function (R) {
                    if (R.code === 0) {
                        Msg.success('操作成功');
                        $(".search-btn").click();
                    } else {
                        Msg.error(R.msg);
                    }
                },
                error: function () {
                    Msg.error("系统繁忙");
                }
            });
        });
    }

}
/**数据渲染对象*/
var Render = {
    //渲染状态列
    customState: function (rowdata,renderData, index, value) {
        if(value == "正常"){
            return '<span style="color:green">'+value+'</span>';
        }
        if(value == "禁用"){
            return '<span style="color:red">'+value+'</span>';
        }
        return value;
    }
};
