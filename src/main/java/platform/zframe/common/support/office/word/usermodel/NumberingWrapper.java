package platform.zframe.common.support.office.word.usermodel;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFNum;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;

import java.lang.reflect.Field;
import java.util.List;

/**
 * This is a utility class so that I can get access to the protected fields
 * within XWPFNumbering.
 */
public class NumberingWrapper {

	private final XWPFNumbering numbering;

	public NumberingWrapper(XWPFNumbering numbering) {
		this.numbering = numbering;
	}

	public List<XWPFAbstractNum> getAbstractNums() {
		try {
			Class clas = numbering.getClass();
			Field field = clas.getDeclaredField("abstractNums");
			field.setAccessible(true);
			Object obj=field.get(numbering);
			return (List<XWPFAbstractNum>)obj;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List<XWPFNum> getNums() {
		try {
			Class clas = numbering.getClass();
			Field field = clas.getDeclaredField("nums");
			field.setAccessible(true);
			Object obj=field.get(numbering);
			return (List<XWPFNum> )obj;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public XWPFNumbering getNumbering() {
		return numbering;
	}

	public int getAbstractNumsSize() {
		List<XWPFAbstractNum> list=getAbstractNums();
		return list == null ? 0 : list.size();
	}

}
