package academy.kovalevskyi.javadeepdive.week0.day2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvHelper {

	public static Csv parseFile(Reader reader) throws FileNotFoundException {
		return new Csv(new String[0], new String[0][0]);

	}

	public static Csv parseFile(Reader reader, boolean withHeader, char delimiter) throws FileNotFoundException {
		String[] header = null;
		String[] arrLine = null;
		String delimiterStr = String.valueOf(delimiter);
		ArrayList<String> listLine = new ArrayList<>();
		BufferedReader bufferedReader = null;

		try  {
			bufferedReader = new BufferedReader(reader);

			if (withHeader) {
				String headerStr = bufferedReader.readLine();
				String line;
				if (headerStr != null) {
					header = headerStr.split(delimiterStr);
				}
			}

			String strLine = null;
			while ((strLine = bufferedReader.readLine()) != null) {
				arrLine = strLine.split(delimiterStr);
				listLine.add(Arrays.toString(arrLine));

			}
		}
		catch (IOException ex) {
			ex.printStackTrace();

		}
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}



		System.out.println(Arrays.toString(header));

		String[][] resultArray = new String[listLine.size()][1];
		for (int i = 0; i < listLine.size(); i++) {
			for (int i1 = 0; i1 < 1; i1++) {
				resultArray[i][i1] = listLine.get(i);
			}
		}
		System.out.println(Arrays.deepToString(resultArray));
		return new Csv.Builder()
						.values(resultArray)
						.header(header)
						.build();

	}


	public static void writeCsv (Writer write, Csv csv, char delimiter) throws IOException {
		// TODO
	}

//	public static void main (String[]args) throws FileNotFoundException {
//		Reader reader = new BufferedReader(new FileReader("D:\\test.txt"));
//		parseFile(reader, true, ',');
//
//		String[][] arr = new String[2][1];
//		arr[0][0] = "1";
//		arr[1][0] = "2";
//		System.out.println(Arrays.deepToString(arr));
//	}

}

