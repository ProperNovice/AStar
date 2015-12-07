package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {

	private static String textFile = "txt.txt";

	private Serialization() {

	}

	public static void writeToFile(Object object) {

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(textFile))) {

			objectOutputStream.writeObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Object readFromFile() {

		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(textFile))) {

			Object object = (Object) objectInputStream.readObject();

			return object;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
