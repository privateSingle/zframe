function importExcel(url) {
    layer.msg('导入数据', {
        time: 0 //不自动关闭
        , closeBtn: 1
        , btn: ['导入模板', '下载模板']
        , btn1: function (index) {
            $("#file").trigger("click");
            layer.close(index);
        }, btn2: function (index) {
            window.location.href=url+"?opr=tmpl";
            layer.close(index);
        }
    });
}

function getFilePath(url) {
    var formData = new FormData();
    formData.append("file", $("#file")[0].files[0]);
    var msg = layer.msg('正在导入中,请稍等...', {icon: 16, shade: [0.5, '#f5f5f5'], scrollbar: false, offset: '0px', time: 0});
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function (R) {
            if (R.code == 0) {
                layer.close(msg);
                $t.Refresh();
                Msg.success(R.msg);
            } else {
                layer.close(msg);
                Alert.alert(R.msg)
            }
        }
    });
}
function exportExcel(url) {
    var httpurl=url;
    layer.msg('导出数据', {
        time: 0 //不自动关闭
        , closeBtn: 1
        , btn: ['当前页', '所有页']
        , btn1: function (index) {
            window.location.href=url+"?opr=data&page="+$("#page").val()+"&limit="+$("#limit").val()+"&sidx=createTime&order=desc";
            layer.close(index);
        }, btn2: function (index) {
            window.location.href=url+"?opr=data"+"&sidx=createTime&order=desc";
            layer.close(index);
        }
    });
}