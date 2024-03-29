/**
 *  on 2017/6/21
 *
 */
(function ($) {
    /* 入口函数 */
    $.fn.selectTool = function () {
        //当前表格对象
        var $grid = this;
        //获取表格参数
        zProps = $grid.attr("zProps");
        if (!zProps) {
            return
        }
        zProps = zProps ? zProps : "";
        //将表格参数转为json
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
            R = selectTool.getDataByCode(zProps.codeName);
        }
        //如果是从后台获取数据
        if (url != undefined && url != "") {
            R = selectTool.getDataByUrl(zProps.url);
        }
        //如果是从枚举获取数据
        if (enumName != undefined && url != "") {
            R = selectTool.getDataByEnum(zProps.enumName);
        }
        selectTool.renderData(R, $grid, zProps);
    };
    /*默认配置*/
    var zProps = {};
    /*方法对象*/
    var selectTool = {
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
        /**渲染下拉框数据 by zhangyantao 2017/6/21*/
        renderData: function (R, $grid, zProps) {
            var _grid = $grid;
            //获取下拉控件的name
            var _name = $(_grid).attr("name");
            //获取下拉控件的默认值
            var _value = $(_grid).attr("value");
            //获取需要验证的参数
            var _verify = $(_grid).attr("lay-verify") || "";

            $(_grid).removeAttr("lay-verify");
            $(_grid).removeAttr("name");
            $(_grid).removeAttr("value");
            //是否是下拉多选
            var _multiple=zProps.multiple||"false";
            //获取是否有提示
            var _selectTip = zProps.tips || "请选择";
            //获取监控标识
            var filter = zProps.filter || "";
            //搜索功能参数
            var _search = zProps.search || "true";
            //获取下拉框禁用的值
            var _disabled = zProps.disabled || "";
            var _disableds = _disabled.split(",");
            var data = R.data;
            var _select = '<select name="' + _name + '" ></select>';
            //是否是多选
            if (_multiple == "true") {
                _select = _select.replace('<select', '<select  multiple value="'+_value+'"');

            }
            //是否开启搜索功能
            if (_search == "true") {
                _select = _select.replace("<select", "<select  lay-search");

            }
            //添加监控标识
            if (filter != "") {
                _select = _select.replace("<select", "<select lay-filter=" + filter);
            }
            //验证值
            if (_verify != undefined && _verify != "") {
                _select = _select.replace("<select", "<select  lay-verify='" + _verify + "'");
            }
            $(_grid).append(_select);

            if(_selectTip!="false"){
                $(_grid).find("select").append('<option value="">' + _selectTip + '</option>');
            }

            if (data != undefined) {
                for (var i = 0; i < data.length; i++) {
                    var _option = '<option value="' + data[i].code + '">' + data[i].value + '</option>';
                    if(_multiple == "false"){
                        //设置默认值
                        if (_value == data[i].code) {
                            _option = _option.replace("<option", "<option selected ")
                        }
                    }
                    if(_multiple == "true"){
                        var _values=_value.split(",")||"";
                        if(_values){
                            for(var z=0;z<_values.length;z++){
                                //设置默认值
                                if (_values[z] == data[i].code) {
                                    _option = _option.replace("<option", "<option selected ")
                                }
                            }
                        }
                    }
                    //设置禁用
                    if (_disableds.indexOf(data[i].code) != -1) {
                        _option = _option.replace("<option", "<option disabled ")
                    }
                    $(_grid).find("select").append(_option);
                }
            }
            //渲染下拉框
            layui.use('form', function () {
                var form = layui.form();
                form.render();
            });

        }
    }

})(jQuery);
$(document).ready(function () {
    //下拉树查询
    var selects = $("[zType='selectTool']");
    for (var i = 0; i < selects.length; i++) {
        $(selects[i]).selectTool();
    }
});