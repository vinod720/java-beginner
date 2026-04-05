package tutorial_4.currency_converter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	private Scanner sc;
	ArrayList<Currency> currencies = new ArrayList<>();

	// 1st Step: open the file
	public void openFile() {
		try {
			InputStream stream = Reader.class.getResourceAsStream("rates.txt");
			if (stream == null) {
				throw new IllegalStateException("rates.txt not found in classpath");
			}
			sc = new Scanner(stream);
		} catch (Exception e) {
			System.out.println("ERROR loading rates file: " + e.getMessage());
		}
	}

	// 2nd Step: read lines from the file and store in ArrayList
	public void readFile() {
		if (sc == null) return;
		while (sc.hasNext()) {
			currencies.add(new Currency(sc.next(), Double.parseDouble(sc.next())));
		}
		sc.close();
	}

	public void printOut() {
		for (Currency currency : currencies) {
			System.out.println(currency.getName() + " " + currency.getRate());
		}
	}

	public int sizeOf() {
		return currencies.size();
	}

	public String getName(int i) {
		return currencies.get(i).getName();
	}

	public double getRate(int i) {
		return currencies.get(i).getRate();
	}
}
