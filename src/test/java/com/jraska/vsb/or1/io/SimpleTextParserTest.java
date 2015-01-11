package com.jraska.vsb.or1.io;

import com.jraska.vsb.or1.data.Input;
import com.jraska.vsb.or1.data.Job;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SimpleTextParserTest {
  //region Test methods

  @Test
  public void testParsedSizes() throws Exception {
    String input = "10 50\n" +
            "46 52 79 45 97 10 44 24 85 75 66 49 95 61 19 47 84 13 11 19 98 2 85 44 7 73 19 69 12 73 85 23 53 16 88 8 26 42 58 63 7 2 44 38 24 76 85 61 32 90 \n" +
            "61 87 51 25 73 93 28 90 94 59 64 2 16 35 53 40 81 26 85 4 4 10 63 96 55 71 66 94 7 15 11 99 37 50 56 69 22 56 67 63 96 74 4 42 40 30 93 36 25 87 \n" +
            "3 1 58 85 33 71 58 56 64 43 48 69 96 35 82 53 64 11 61 36 53 87 88 10 32 38 25 24 90 7 11 49 2 76 17 32 39 9 83 69 67 28 88 23 91 71 3 26 41 96 \n" +
            "51 24 21 57 69 51 50 51 21 19 63 91 11 6 31 63 36 39 57 47 56 65 59 4 10 12 62 43 49 54 87 29 2 18 75 39 77 69 15 78 68 37 22 41 92 67 24 87 91 31 \n" +
            "37 16 42 47 94 14 94 34 72 36 88 51 41 71 94 99 11 97 44 77 69 91 38 25 87 7 66 54 86 49 3 48 44 93 37 82 31 59 78 33 36 3 58 10 98 6 44 62 24 94 \n" +
            "79 93 68 75 37 44 34 39 76 62 74 28 78 43 98 83 91 27 6 82 60 44 43 76 99 66 11 35 52 8 40 62 25 24 30 1 73 27 16 91 33 11 99 2 60 90 36 62 15 3 \n" +
            "83 87 38 38 86 67 23 19 97 78 66 67 7 23 67 8 77 71 85 29 49 3 94 76 95 48 4 37 82 57 61 6 97 5 27 95 46 92 46 52 8 11 7 54 72 57 85 22 87 65 \n" +
            "22 29 99 25 98 55 80 82 33 68 47 74 26 61 95 55 11 42 72 14 8 98 90 36 75 69 26 24 55 98 86 30 92 94 66 47 3 41 41 47 89 28 39 80 47 57 74 38 59 5 \n" +
            "27 92 75 94 18 41 37 58 56 20 2 39 91 81 33 14 88 22 36 65 79 23 66 5 15 51 2 81 12 40 59 32 16 87 78 41 43 94 1 93 22 93 62 53 30 34 27 30 54 77 \n" +
            "24 47 39 66 41 46 24 23 68 50 93 22 64 81 94 97 54 82 11 91 23 32 26 22 12 23 34 87 59 2 38 84 62 10 11 93 57 81 10 40 62 49 90 34 11 81 51 21 39 27 \n";


    Input parsedInput = parseInput(input);

    assertThat(parsedInput, notNullValue());
    assertThat(parsedInput.getMachinesCount(), equalTo(10));

    for (Job job : parsedInput.getJobs()) {
      assertThat(job.getDurationsCount(), equalTo(10));
    }
  }

  @Test
  public void testParsedData() throws Exception {
    String input = "5 1\n" +
            "24\n47\n309\n66\n1";
    int[] expected = {24, 47, 309, 66, 1};

    Input parsedInput = parseInput(input);

    assertThat(parsedInput.getJobs()[0].getDurations(), equalTo(expected));
  }

  @Test
  public void testAddJobs() throws Exception {
    String input = "5 1\n" +
            "24\n47\n309\n66\n1";

    Input parsedInput = parseInput(input);

    assertThat(parsedInput.getJobs().length, equalTo(1));
  }

  //endregion

  //region Methods

  public static Input parseInput(String input) {
    SimpleTextParser simpleTextParser = new SimpleTextParser();

    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    return simpleTextParser.parse(inputStream);
  }

  //endregion
}
