package demo.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtil {

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// THAY Đ THÀNH D
		slug = slug.replaceAll("đ", "d");
		// XÓA CÁC KÝ TỰ ĐẶT BIỆT
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// THAY SPACE THÀNH DẤU GẠCH NGANG
		slug = slug.replaceAll("[\\s]", "-");
		// ĐỔI NHIỀU KÝ TỰ GẠCH NGANG LIÊN TIẾP THÀNH 1 KÝ TỰ GẠCH NGANG
		slug = slug.replaceAll("(-+)", "-");
		// XÓA CÁC KÝ TỰ GẠCH NGANG Ở ĐẦU VÀ CUỐI
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}
}