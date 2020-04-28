<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
    <#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-normal">
                <input type="text" name="username" value="" lay-verify="required|username" placeholder="请输入用户名"
                      autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-normal">
                <input type="text" name="email" value="" lay-verify="required" placeholder="请输入邮箱" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-normal">
                <input type="text" name="mobile" value="" lay-verify="required" placeholder="请输入手机号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <p style="margin-left: 60px;">初始密码为123456</p>
        </div>

        <div class="layui-form-item">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" style="margin-right: 1700px" lay-filter="submit" data-url="/sys/user/registerData"><i
                            class="fa fa-floppy-o">&nbsp;</i>保存
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>

</html>
