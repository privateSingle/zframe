<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
<#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <input type="hidden" name="menuId" value="${(model.menuId)!""}">
        <div class="layui-form-item">
            <label class="layui-form-label">类型</label>
            <!--单选框渲染控件-->
            <div zType="radioTool" zProps="codeName:'menuType',filter:'menuType'" name="type" value="${(model.type)!""}"
                 class="layui-input-normal"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-normal">
                <input type="text" name="name" value="${(model.name)!""}" lay-verify="required" autocomplete="off"
                       placeholder="请输入菜单名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上级菜单</label>
            <div class="layui-input-normal">
                <input value="${(model.parentId)!""}" id="parent"
                       zType="treeTool" zProps="url:'/sys/menu/select',name:'parentId'"
                       placeholder="请选择上级菜单" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单URL</label>
            <div class="layui-input-normal">
                <input type="text" name="url" value="${(model.url)!""}" placeholder="请输入url" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">授权标识</label>
            <div class="layui-input-normal">
                <input type="text" name="perms" value="${(model.perms)!""}" placeholder="多个用逗号分隔，如：user:list,user:create"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序号</label>
            <div class="layui-input-normal">
                <input type="text" name="orderNum" value="${(model.orderNum)!""}" placeholder="请输入排序号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图标</label>
            <div class="layui-input-normal">
                <input type="text" name="icon" value="${(model.icon)!""}" placeholder="请输入图标" autocomplete="off"
                       class="layui-input">
                <code style="margin-top: 4px; display: block;">

                    获取图标：http://www.fontawesome.com.cn/faicons/

                </code>
            </div>
        </div>
        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/sys/menu/update"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>

    </form>
</div>
<script>
    layui.use(['form'], function () {
        var form = layui.form();
        //监听单选
        form.on('radio(menuType)', function (data) {
            if (data.value == 0) {
                $(".layui-form-item").show();
                $("[name='url']").parent().parent().hide();
                $("[name='perms']").parent().parent().hide();
                form.render();
                return false;
            }
            if (data.value == 1) {
                $(".layui-form-item").show();
                form.render();
                return false;
            }
            if (data.value == 2) {
                $(".layui-form-item").show();
                $("[name='url']").parent().parent().hide();
                $("[name='order_num']").parent().parent().hide();
                $("[name='icon']").parent().parent().hide();
                form.render();
                return false;
            }
        });


    });
    $(document).ready(function () {
        if ($($("[lay-filter='menuType']")[0]).attr("checked")  != undefined) {
            $("[name='url']").parents(".layui-form-item").hide();
            $("[name='perms']").parents(".layui-form-item").hide();
        }
        if ($($("[lay-filter='menuType']")[2]).attr("checked")  != undefined) {
            $("[name='url']").parents(".layui-form-item").hide();
            $("[name='order_num']").parents(".layui-form-item").hide();
            $("[name='icon']").parents(".layui-form-item").hide();
        }
    });
</script>
</body>
</html>
