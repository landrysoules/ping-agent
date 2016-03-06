package rs.spaceinvade.pingagent.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Utilities meant to facilitate some test operations.
 * @author Landry Soules
 *
 */
public class TestUtils {
	/**
	 * Transforms InputStream into String.
	 */
	public static String readInputStream(InputStream input) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}
}
