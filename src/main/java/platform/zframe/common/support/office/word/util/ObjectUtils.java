package platform.zframe.common.support.office.word.util;

public final class ObjectUtils {

	public static <T> T requireNonNull(T obj, String message) {
		if (obj == null)
			throw new NullPointerException(message);
		return obj;
	}

}
