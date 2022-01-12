package academy.kovalevskyi.javadeepdive.week0.day2;

public record Csv(String[] header, String[][] values) {

	public static class Builder {

		private String[] header;
		private String[][] values;

		public Builder header(String[] header) {
			this.header = header;
			return this;
		}

		public Builder values(String[][] values) {
			this.values = values;
			return this;
		}

		public Csv build() {
			return new Csv(header, values);
		}

		public String[] getHeader() {
			return header;
		}

		public String[][] getValues() {
			return values;
		}
	}

	public boolean withHeader() {
		return header != null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Csv csv = (Csv) o;
		return Arrays.equals(header, csv.header) && Arrays.equals(values, csv.values);
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(header);
		result = 31 * result + Arrays.hashCode(values);
		return result;
	}
}