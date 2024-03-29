/**
 *  on 2017/6/21
 *
 */
(function ($) {
    /* 入口函数 */
    $.fn.labelTool = function () {
        //当前对象
        var $grid = this;
        //获取参数
        zProps = $grid.attr("zProps");
        if (!zProps) {
            return
        }
        zProps = zProps ? zProps : "";
        //将参数转为json
        zProps = eval("({" + zProps + "})");
        //获取数据的地址，只能通过表码或url，如果两个都写，默认是url
        //从表码获取数据
        var codeName = zProps.codeName;
        //从后台获取数据
        var url = zProps.url;
        //从枚举获取数据
        var enumName = zProps.enumName;
        var R = "";
        //如果是通过表码取值
        if (codeName != undefined && codeName != "") {
            R = labelTool.getDataByCode(zProps.codeName);
        }
        //如果是从后台获取数据
        if (url != undefined && url != "") {
            R = labelTool.getDataByUrl(zProps.url);
        }
        //如果是从枚举获取数据
        if (enumName != undefined && url != "") {
            R = labelTool.getDataByEnum(zProps.enumName);
        }
        labelTool.renderData(R, $grid, zProps);
    };
    /*默认配置*/
    var zProps = {};
    /*方法对象*/
    var labelTool = {
        /**获取数据 by zhangyantao 2017/6/21*/
        getDataByUrl: function (url) {
            var data;
            $.ajax({
                url: url,
                async: false,
                dataType: "json",
                success: function (R) {
                    if (R.code == 0) {
                        data = R;
                    } else {
                        data = {};
                        alert(R.msg);
                    }
                }
            });
            return data;
        },
        /**获取数据 by zhangyantao 2017/7/5*/
        getDataByCode: function (codeName) {
            /**localStorage是否已存在该数据*/
            var data = $t.getStorageItem(codeName);
            if (!data) {
                $.ajax({
                    url: $s.getDataByCode,
                    async: false,
                    data: {codeName: codeName},
                    type: 'post',
                    dataType: "json",
                    success: function (R) {
                        if (R.code == 0) {
                            data = R;
                            /**设置localStorage缓存*/
                            $t.setStorageItem(codeName, data);
                        } else {
                            data = {};
                            alert(R.msg);
                        }
                    }
                });

            }

            return data;
        },
        /**获取数据 by zhangyantao 2017/7/19*/
        getDataByEnum: function (enumName) {
            /**localStorage是否已存在该数据*/
            var data = $t.getStorageItem(enumName);
            if (!data) {
                $.ajax({
                    url: $s.getDataByEnum,
                    async: false,
                    type: 'post',
                    data: {enumName: enumName},
                    dataType: "json",
                    success: function (R) {
                        if (R.code == 0) {
                            data = R;
                            /**设置localStorage缓存*/
                            $t.setStorageItem(enumName, data);
                        } else {
                            data = {};
                            alert(R.msg);
                        }
                    }
                });
            }
            return data;
        },
        /**组件数据渲染 by zhangyantao 2017/7/20*/
        renderData: function (R, $grid, zProps) {
            var _grid = $grid;
            var _value = $(_grid).attr("value");
            $(_grid).removeAttr("value");
            var _showColor = zProps.showColor||"false";
            var data = R.data;
            var valueHtml="";
            if (data != undefined) {
                for (var i = 0; i < data.length; i++) {
                    //设置默认值
                    if (_value == data[i].code) {
                        valueHtml=data[i].value;
                        //是否显示颜色
                        if(_showColor=="true"){
                            //默认值为1时绿色  0时红色
                            if(_value==1){
                                valueHtml="<span style='color:green'>"+data[i].value+"</span>"
                            }
                            if(_value==0){
                                valueHtml="<span style='color:red'>"+data[i].value+"</span>"
                            }
                        }
                        $(_grid).html(valueHtml);
                    }
                }
            }
        }
    }

})(jQuery);
$(document).ready(function () {
    var labels = $("[zType='labelTool']");
    for (var i = 0; i < labels.length; i++) {
        $(labels[i]).labelTool();
    }
});