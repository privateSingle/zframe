<!DOCTYPE html>
<html>
<head>
    <title>菜单列表</title>
<#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <input type="hidden" name="roleId" value="${model.roleId}">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="roleName" maxlength="10" value="${model.roleName}" lay-verify="required"
                       placeholder="请输入角色名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色编码<span class="label_span">:</span></label>
            <label class="layui-label-right">
            ${model.remark}
            </label>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">授权<span class="label_span">:</span></label>
            <div class="layui-input-inline">
                <ul id="menuTree" class="ztree"></ul>
            </div>
        </div>
        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="roleSubmit" ><i
                            class="fa fa-floppy-o">&nbsp;</i>保存
                    </button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>

    </form>
</div>
<script>
    layui.use(['form'], function () {
        var form = layui.form();

        //监听提交
        form.on('submit(roleSubmit)', function (data) {
            //获取选择的菜单
            var nodes = ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].id);
            }
            //向form表单中添加选中的菜单
            data.field.menuIdList = menuIdList;
            var url = "/sys/role/update";
            $.ajax({
                type: "post",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(data.field),
                async: false,
                dataType: "json",
                success: function (R) {
                    if (R.code === 0) {
                        Msg.success("操作成功");
                        $t.Refresh();

                    } else {
                        alert(R.msg);
                    }
                },
                error: function () {
                    alert("系统繁忙");
                }
            });
            return false;
        });


    });

    /*下拉树*/
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: -1
            },
            key: {
                url: "nourl"
            }
        },
        check: {
            enable: true,
            nocheckInherit: true
        }
    };
    var ztree;
    //加载菜单树
    $.ajax({
        //url: "/sys/menu/list",
        type: "post",
        url: '/sys/menu/treePerms',
        contentType: "application/json",
        async: false,
        dataType: "json",
        success: function (R) {
            if (R.code === 0) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, R.data);
                //展开所有节点
                ztree.expandAll(true);
            } else {
                alert(R.msg);
            }
        },
        error: function () {
            alert("系统错误");
        }
    });
    //勾选已选中的菜单
    $.ajax({
        type: "post",
        url: '/sys/role/info/${model.roleId}',
        contentType: "application/json",
        async: false,
        dataType: "json",
        success: function (R) {
            if (R.code === 0) {
                //勾选角色所拥有的菜单
                var menuIds = R.role.menuIdList;
                for (var i = 0; i < menuIds.length; i++) {
                    var node = ztree.getNodeByParam("id", menuIds[i]);
                    if (node != null) {
                        ztree.checkNode(node, true, false);
                    }

                }
            } else {
                alert(R.msg);
            }
        },
        error: function () {
            alert("系统错误");
        }
    });


</script>
</body>
</html>
