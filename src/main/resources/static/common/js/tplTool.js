/**
 *  on 2017/6/21
 *
 */
(function ($) {
    /* 入口函数 */
    $.fn.tplTool = function () {
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
        //从后台获取数据
        var url = zProps.url;
   
        var R = "";
        //如果是从后台获取数据
        if (url != undefined && url != "") {
            tplTool.renderData(zProps.url, $grid);
        }
     
       
    };
    /*默认配置*/
    var zProps = {};
    /*方法对象*/
    var tplTool = {
        /**获取数据 by zhangyantao 2017/6/21*/
        getDataByUrl: function (url) {
            var data;
            $.getJSON(url, function (R) {
                    if (R.code == 0) {
                        data=R;
                    } else {
                        data = {};
                        Msg.error(R.msg);

                    }
                return data;
                });

        },
        /**组件数据渲染 by zhangyantao 2017/7/20*/
        renderData: function (url, $grid) {
            var _grid = $grid;
            $.getJSON(url, function (R) {
                layui.use('laytpl', function () {
                    var laytpl = layui.laytpl;
                    var getTpl = $(_grid).find("script").html();
                    laytpl(getTpl).render(R, function (html) {
                        $(_grid).html(html);
                    });
                });
            });

        }
    }

})(jQuery);
$(document).ready(function () {
    var tpls = $("[zType='tplTool']");
    for (var i = 0; i < tpls.length; i++) {
        $(tpls[i]).tplTool();
    }
});