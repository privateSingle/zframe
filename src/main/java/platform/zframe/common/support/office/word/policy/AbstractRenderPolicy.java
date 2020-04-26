package platform.zframe.common.support.office.word.policy;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import platform.zframe.common.support.office.word.DocBoot;
import platform.zframe.common.support.office.word.template.ElementTemplate;
import platform.zframe.common.support.office.word.template.run.RunTemplate;

/**
 * 抽象策略

 * @version 
 */
public abstract class AbstractRenderPolicy implements RenderPolicy {

    /**
     * 校验data model
     * 
     * @param data
     * @return
     */
    protected abstract boolean validate(Object data);

    /**
     * 执行模板渲染
     * 
     * @param runTemplate 文档模板
     * @param data 数据模型
     * @param template 文档对象
     * @throws Exception
     */
    public abstract void doRender(RunTemplate runTemplate, Object data, DocBoot template)
            throws Exception;

    /* 
     * 骨架
     * (non-Javadoc)
     * @see RenderPolicy#render(ElementTemplate, java.lang.Object, DocBoot)
     */
    @Override
    public void render(ElementTemplate eleTemplate, Object data, DocBoot template) {
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        // validate
        if (!validate(data)) {
            clearPlaceholder(runTemplate.getRun());
            return;
        }

        // do render
        try {
            doRender(runTemplate, data, template);
        } catch (Exception e) {
            doRenderException(runTemplate, data, e);
        }

    }

    protected void doRenderException(RunTemplate runTemplate, Object data, Exception e) {
        logger.error("Render template:{} error", runTemplate, e);
    }

    protected void clearPlaceholder(XWPFRun run) {
        run.setText("", 0);
    }

}
