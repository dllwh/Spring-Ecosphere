package org.dllwh.thymeleaf.extend.customtag;

import org.dllwh.thymeleaf.extend.utils.ThymeleafTagHelper;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingTagProcessor extends AbstractElementTagProcessor {

	private static final String	TAG_NAME	= "pager";	// 标签名   
	private static final int	PRECEDENCE	= 10000;	// 优先级，必须是10000，否则读取不到标签的赋值 
														//  

	public PagingTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {

		String pageIndexString = tag.getAttributeValue("value");
		ThymeleafTagHelper.createText(context, tag, structureHandler,
				HtmlEscape.escapeHtml5(pageIndexString));
		log.info("pageIndexString:" + pageIndexString);
	}

}
