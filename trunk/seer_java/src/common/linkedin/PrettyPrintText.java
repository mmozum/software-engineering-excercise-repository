package common.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PrettyPrintText {

	public static void main(String[] args) {

		String text = "A mutable sequence of characters. This class provides an API compatible with StringBuffer, but with no guarantee of synchronization. This class is designed for use as a drop-in replacement for StringBuffer in places where the string buffer was being used by a single thread (as is generally the case). Where possible, it is recommended that this class be used in preference to StringBuffer as it will be faster under most implementations.";
		int lineLen = 30;

		System.out.println(reformat(text, lineLen));

	}

	/**
	 * Given a paragraph of text, reformat it such that there are exactly K
	 * characters per line. Whitespaces should spread as evenly as possible.
	 * 
	 * @param text
	 *            - text to be formated
	 * @param lineLen
	 *            - length of a line
	 * @return - formated text
	 */
	public static String reformat(String text, int lineLen) {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(text);

		List<String> list = new ArrayList<String>();
		int currentLen = -1;
		String next = null;

		String spacesStr = new String(new char[lineLen]).replace('\0', ' ');

		if (st.hasMoreTokens()) {
			next = st.nextToken();
		}

		while (next != null || !list.isEmpty()) {

			if (next != null
					&& (list.isEmpty() || currentLen + next.length() + 1 <= lineLen)) {
				// read next token
				currentLen += next.length() + 1;
				list.add(next);
				if (st.hasMoreTokens()) {
					next = st.nextToken();
				} else {
					next = null;
				}
			} else {
				assert (!list.isEmpty());
				// print a line
				if (list.size() == 1) {
					sb.append(list.get(0)).append("\n");
				} else {
					int remainedSpace = lineLen - currentLen
							+ (list.size() - 1);
					int spacesLo = remainedSpace / (list.size() - 1);
					int numLo = list.size() - 1
							- (remainedSpace - spacesLo * (list.size() - 1));

					for (int i = 0; i < list.size() - 1; i++) {
						sb.append(list.get(i));

						if (i < numLo) {
							sb.append(spacesStr.substring(0, spacesLo));
						} else {
							sb.append(spacesStr.substring(0, spacesLo + 1));
						}
					}

					sb.append(list.get(list.size() - 1)).append("\n");
				}

				list.clear();
				currentLen = -1;
			}
		}

		return sb.toString();
	}

}
