/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package platform.zframe.common.support.office.word.resolver;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import platform.zframe.common.support.office.word.config.Configure;
import platform.zframe.common.support.office.word.template.run.RunTemplate;

import java.util.Set;

/**
 * 

 * @version 1.0.0
 */
public class TemplateFactory{
	
	public static final char EMPTY_CHAR = '\0'; 

	public static RunTemplate createRunTemplate(String tag, Configure config, XWPFRun run) {
		RunTemplate template = new RunTemplate();
		Set<Character> gramerChars = config.getGramerChars();
		char fisrtChar = tag.charAt(0);
		Character symbol = Character.valueOf(EMPTY_CHAR);
		for (Character chara : gramerChars){
			if (chara.equals(fisrtChar)){
				symbol = Character.valueOf(fisrtChar);
				break;
			}
		}
		template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
		template.setTagName(symbol.equals(Character.valueOf(EMPTY_CHAR)) ? tag : tag.substring(1));
		template.setSign(symbol);
		template.setRun(run);
		return template;
	}
	
	

}
