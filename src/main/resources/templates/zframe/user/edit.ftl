<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
    <#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <input type="hidden" name="userId" value="${(model.userId)!""}">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名<span class="span_must">*</span></label>

            <label class="layui-label-right">${(model.username)!""}</label>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="email" value="${(model.email)!""}" lay-verify="email" placeholder="请输入邮箱"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="mobile" value="${(model.mobile)!""}" lay-verify="phone" placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属部门</label>
            <div class="layui-input-normal">

                <input id="org"
                       zType="treeTool" zProps="url:'/organize/select',name:'orgId'"
                       value="${(model.orgId)!""}"   placeholder="请选择所属部门" class="layui-input"/>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属角色</label>
            <div zType="selectTool" zProps="url:'/sys/role/findAll',multiple:'true'"
                 value="${(roleIdList)!""}" name="roleIdList[]" class="layui-input-normal"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div zType="radioTool" zProps="enumName:'StateEnum'" name="status"
                 value="${(model.status)!""}" class="layui-input-inline"></div>
        </div>


        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/sys/user/update"><i
                            class="fa fa-floppy-o">&nbsp;</i>保存
                    </button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
