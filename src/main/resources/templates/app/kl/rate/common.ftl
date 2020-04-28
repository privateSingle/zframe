<#--  利率表公用页面-->
<#--  dalele 2020-04-27 13:29:30-->
                         <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">利率%<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="rate" maxlength="10,2" lay-verify="required"
                 value="${(model.rate)!""}"  placeholder="请输入利率"  class="layui-input">
            </div>
        </div>
