package org.dllwh.thymeleaf.extend.utils;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public final class ThymeleafTagHelper {

    /**
     * @param context          页面上下文
     * @param tag              标签
     * @param structureHandler
     * @param text
     * @方法描述:TODO(这里用一句话描述这个方法的作用)
     */
    public static void createText(ITemplateContext context, IProcessableElementTag tag,
                                  IElementTagStructureHandler structureHandler, CharSequence text) {
        // 标签名
        final String elementCompleteName = tag.getElementCompleteName();
        // 创建模型
        final IModelFactory modelFactory = context.getModelFactory();
        final IModel model = modelFactory.createModel();
        // 添加模型 标签
        model.add(modelFactory.createOpenElementTag(elementCompleteName));
        model.add(modelFactory.createText(text));
        // 添加模型 标签
        model.add(modelFactory.createCloseElementTag(elementCompleteName));

        // 指示引擎用指定的模型替换整个元素。(替换页面标签)
        structureHandler.replaceWith(model, false);
    }
}
