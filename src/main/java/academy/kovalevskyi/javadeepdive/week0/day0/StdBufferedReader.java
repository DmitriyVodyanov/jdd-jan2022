package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.*;

public class StdBufferedReader implements Closeable {

	private static Reader reader;
	private static char[] buffer;
	private static int indexOfBuffer = 0;
	private static int charCountOfBuffer = 0;

	private final static int DEFAULT_BUFFER_SIZE = 8;

	public StdBufferedReader(Reader reader) {
		this(reader, DEFAULT_BUFFER_SIZE);
	}

	public StdBufferedReader(Reader reader, int bufferSize) {
		if (reader == null) {
			throw new NullPointerException();
		}
		if (bufferSize <= 1) {
			throw new IllegalArgumentException("Buffer size <= 0");
		}
		this.reader = reader;
		buffer = new char[bufferSize];
	}

	public boolean hasNext() throws IOException {
		if (indexOfBuffer >= charCountOfBuffer) {
			fill();
		}
		return indexOfBuffer < charCountOfBuffer;
	}

	public char[] readLine() throws IOException {
		char[] line = null;

		do {
			if (indexOfBuffer >= charCountOfBuffer) {
				fill();
			}

			if (indexOfBuffer >= charCountOfBuffer) {
				if (line != null && line.length > 0) {
					return line;
				} else {
					return null;
				}
			}

			boolean endOfLine = false;
			int currentIndex = indexOfBuffer;

			while (currentIndex < charCountOfBuffer) {
				if (buffer[currentIndex] == '\n') {
					endOfLine = true;
					break;
				}

				currentIndex++;
			}

			int indexOfStartChar = indexOfBuffer;
			indexOfBuffer = currentIndex;

			if (endOfLine) {
				line = fillAndGetLine(line, currentIndex, indexOfStartChar);
				indexOfBuffer++;
				return line;
			}

			line = fillAndGetLine(line, currentIndex, indexOfStartChar);
		} while (true);
	}

	private char[] fillAndGetLine(char[] line, int currentIndex, int indexOfStartChar) {
		if (line == null) {
			line = new char[currentIndex - indexOfStartChar];
			System.arraycopy(buffer, indexOfStartChar, line, 0, currentIndex - indexOfStartChar);
		} else {
			char[] tmp = new char[line.length + (currentIndex - indexOfStartChar)];
			System.arraycopy(line, 0, tmp, 0, line.length);
			System.arraycopy(buffer, indexOfStartChar, tmp, line.length, currentIndex - indexOfStartChar);
			line = tmp.clone();
		}
		return line;
	}

	private static void fill() throws IOException {
		int currentLengthOfBuffer = reader.read(buffer,0, buffer.length);
		if (currentLengthOfBuffer > 0) {
			charCountOfBuffer = currentLengthOfBuffer;
			indexOfBuffer = 0;
		}
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new BufferedReader(new FileReader("/home/dm/test.txt"));
		StdBufferedReader bufferedReader = new StdBufferedReader(reader);
//		BufferedReader br = new BufferedReader(reader);
//		System.out.println(bufferedReader.readLine());
//		int readCharsCount;
//		fill();
//		System.out.println(readCharsCount = reader.read(buffer,0, buffer.length));
//		System.out.println(Arrays.toString(buffer));
	}
}
