package org.dllwh.thymeleaf.extend.customtag;

import org.dllwh.thymeleaf.extend.utils.ThymeleafTagHelper;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThymeleafProcessor extends AbstractAttributeTagProcessor {
	private static final int PRECEDENCE = 300;

	/**
	 * @param templateMode
	 *            模板模式，这里使用HTML模板。
	 * @param dialectPrefix
	 *            标签前缀。即xxx:text中的xxx。在此例子中prefix为thSys。
	 * @param elementName
	 *            匹配标签元素名。举例来说如果是div，则我们的自定义标签只能用在div标签中。为null能够匹配所有的标签。
	 * @param prefixElementName
	 *            标签名是否要求前缀。
	 * @param attributeName:
	 *            自定义标签属性名。这里为text。
	 * @param prefixAttributeName
	 *            属性名是否要求前缀，如果为true，Thymeeleaf会要求使用text属性时必须加上前缀 ，即thSys:text。
	 * @param precedence
	 *            标签处理的优先级，此处使用和Thymeleaf标准方言相同的优先级。
	 * @param removeAttribute
	 *            标签处理后是否移除自定义属性。
	 */
	protected ThymeleafProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, "text", true, PRECEDENCE, true);
	}

	/**
	 * context 页面上下文
	 */
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			AttributeName attributeName, String attributeValue,
			IElementTagStructureHandler structureHandler) {
		// 获取标签内容表达式
		String rawValue = tag.getAttributeValue(attributeName);
		log.info("获取标签内容表达式:" + rawValue + ":attributeValue" + attributeName + ":attributeValue:"
				+ attributeValue);

		ThymeleafTagHelper.createText(context, tag, structureHandler, HtmlEscape.escapeHtml5("这个是测试"));
	}

}
