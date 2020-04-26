package platform.zframe.common.support.office.word.util;


import platform.zframe.common.support.office.word.DocBoot;
import platform.zframe.common.support.office.word.config.Configure;
import platform.zframe.common.support.office.word.exception.RenderException;
import platform.zframe.common.support.office.word.policy.*;
import platform.zframe.common.support.office.word.template.ElementTemplate;

import java.util.ArrayList;
import java.util.List;

/**

 * @version 
 */
@Deprecated
public final class CodeGenUtils {
	
	private static final String importStr = "import zyt.docgen.data Name;\n"
			+ "import zyt.docgen.data PictureRenderData;\n"
			+ "import zyt.docgen.data MiniTableRenderData;\n"
			+ "import zyt.docgen.data TextRenderData;\n"
			+ "import zyt.docgen.data DocxRenderData;\n"
			+ "import zyt.docgen.data NumbericRenderData;\n";

	public static String generateJavaObject(DocBoot template, String packageStr, String className){
		if (null == template) throw new IllegalArgumentException("template is null,should be setted first.");
		List<ElementTemplate> elementTemplates = template.getElementTemplates();
		if (null == elementTemplates || elementTemplates.isEmpty()) return "";
		Configure config = template.getConfig();
		RenderPolicy policy = null;
		List<String> existFields = new ArrayList<String>();
		StringBuffer sb= new StringBuffer();
		StringBuffer sbGetterSetter= new StringBuffer();
		sb.append(packageStr).append("\n");
		sb.append(importStr);
		sb.append("public class " + upCaseFirstChar(className) + "{").append("\n");
		for (ElementTemplate runTemplate : elementTemplates) {
		    policy = config.getPolicy(runTemplate.getTagName(), runTemplate.getSign());
			if (null == policy) 
				throw new RenderException("cannot find render policy: [" + runTemplate.getTagName() + "]");
			
			String tagName = runTemplate.getTagName();
			String field = tagName;
			if (existFields.contains(field)) continue;
			else existFields.add(field);
			if (isOptimusFeild(tagName)) {
				sb.append("@Name(\"" + tagName + "\")\n");
				field = optimusFeild(tagName);
			}
			
			if (policy instanceof TextRenderPolicy){
				sb.append("private String ").append(field).append(";\n");
				sbGetterSetter.append(genGetterSetter("String", field));
			}else if (policy instanceof MiniTableRenderPolicy){
				sb.append("private MiniTableRenderData ").append(field).append(";\n");
				sbGetterSetter.append(genGetterSetter("TableRenderData", field));
			}else if (policy instanceof PictureRenderPolicy){
				sb.append("private PictureRenderData ").append(field).append(";\n");
				sbGetterSetter.append(genGetterSetter("PictureRenderData", field));
			}else if (policy instanceof NumbericRenderPolicy){
				sb.append("private NumbericRenderData ").append(field).append(";\n");
				sbGetterSetter.append(genGetterSetter("NumbericRenderData", field));
			}else if (policy instanceof DocxRenderPolicy){
                sb.append("private DocxRenderData ").append(field).append(";\n");
                sbGetterSetter.append(genGetterSetter("DocxRenderData", field));
            }else{
				sb.append("private Object ").append(field).append(";\n");
				sbGetterSetter.append(genGetterSetter("Object", field));
			}  
		}
		return sb.append(sbGetterSetter.toString()).append("\n").append("}").toString();
	}

	public static String genGetterSetter(String type, String field) {
		StringBuffer sb = new StringBuffer();
		sb.append("public void set").append(upCaseFirstChar(field)).append("(" + type + " ").append(field).append("){\n")
		.append("    this.").append(field).append(" = ").append(field).append(";\n}\n");
		sb.append("public " + type + " get").append(upCaseFirstChar(field)).append("(){\n")
		.append("    return this.").append(field).append(";\n}\n");
		return sb.toString();
	}
	
	public static String upCaseFirstChar(String str) {
		if (null == str) return null;
		char character = str.charAt(0);
		return (character + "").toUpperCase() + str.substring(1);
	}
	
	public static boolean isOptimusFeild(String str) {
		if (null == str) return false;
		String[] split = str.split("_");
		if (split.length <= 1) return false;
		return true;
	}

	public static String optimusFeild(String str) {
		if (null == str) return str;
		String[] split = str.split("_");
		if (split.length <= 1) return str;
		StringBuffer sb = new StringBuffer(split[0]);
		for (int i = 1; i < split.length; i++) {
			sb.append(upCaseFirstChar(split[i]));
		}
		return sb.toString();
	}

}
