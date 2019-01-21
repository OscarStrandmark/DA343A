package l01;

public class DString implements DynamicString { // implementera metoderna i DynamicString - se lab
	private char[] text;
	private int length = 0;
	
	public DString() {
		this(10);
	}
	
	public DString(int size) {
		size = (size<=0) ? 10 : size;
		text = new char[size];
		length = 0;
	}
	
	public DString(String str) {
		text = str.toCharArray();
		length = text.length;
	}
	
	public DString(DString str) {
		text = new char[str.length()];
		for(int i=0; i<str.length(); i++) {
			text[i] = str.charAt(i);
		}
		length = text.length;
	}
	
	private void grow() {
		char[] newArr = new char[length*2];
		System.arraycopy(text, 0, newArr, 0, text.length);
		text = newArr;
	}
	
	public void append(char chr) {
		if(length==text.length) {
			grow();
		}
		text[length++] = chr;
	}

	@Override
	public int length() {
		return text.length;
	}

	@Override
	public char charAt(int index) {
		return text[index];
	}

	@Override
	public void append(DString str) {
		char[] newText = new char[text.length + str.length];

		for (int i = 0; i < text.length; i++) {
			newText[i] = text[i];
		}
		
		for (int i = 0; i < str.length; i++) {
			newText[text.length] = str.charAt(i);
		}
	}

	@Override
	public void delete(int start, int end) {

		char[] newText = new char[text.length - (end - start)];
		
		for (int i = 0; i < text.length; i++) {
			if(i >= start && i <= start) {
				text[i] = (char) -1;
			}
		}
		
		int counter = 0;
		for (int i = 0; i < text.length; i++) {
			if (text[i] != -1) {
				newText[counter] = text[i];
				counter++;
			}
		}
	}

	@Override
	public void delete(int index) {

		char[] newText = new char[text.length-1];
		
		int counter = 0;
		for (int i = 0; i < text.length; i++) {
			if(i != index) {
				newText[counter] = text[i];
				counter++;
			}
		}
	}

	@Override
	public String substring(int start, int end) {

		String ret = "";
		
		for (int i = 0; i < text.length; i++) {
			if(i >= start && i <= end) {
				ret += text[i];
			}
		}
		return ret;
	}

	@Override
	public String substring(int start) {

		String ret = "";
		
		for (int i = start; i < text.length; i++) {
			ret += text[i];
		}
		return ret;
	}

	@Override
	public int indexOf(char chr) {

		for (int i = 0; i < text.length; i++) {
			if(text[i] == chr) {
				return i;
			}
		}
		return -1;
	}
}