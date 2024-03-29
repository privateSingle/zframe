<!DOCTYPE html>
<html>
<head>
    <title>修改</title>
    <#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <input type="hidden" name="areaId" value="${model.areaId}">
        <div class="layui-form-item">
            <label class="layui-form-label">地区名称<span class="span_must">&nbsp;*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="areaName" value="${model.areaName}" lay-verify="required" placeholder="请输入地区名称" maxlength="50" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地区行政编码<span class="span_must">&nbsp;*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="xzCode" lay-verify="number" value="${model.xzCode}" placeholder="请输入地区行政编码" maxlength="8" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态<span class="span_must">&nbsp;*</span></label>
            <div class="layui-input-normal">
                <div zType="radioTool" zProps="codeName:'state'" name="state"
                     value="${model.state}" class="layui-input-inline"></div>
            </div>
        </div>


        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/area/update"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>


    </form>
</div>

</body>
</html>
