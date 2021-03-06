package com.jraska.vsb.or1.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JobScheduleTest {
  //region Constants

  public static final String TEST_JOB = "TestJob";

  //endregion

  //region Test Methods

  @Test
  public void testStartingTimes() throws Exception {
    int[] durations = {5, 3, 7, 2};

    int startTime = 5;
    JobSchedule jobSchedule = new JobSchedule(new Job(TEST_JOB, durations), startTime);

    int[] startingTimes = jobSchedule.getStartingTimes();

    int[] expected = {5, 10, 13, 20};
    assertThat(startingTimes, equalTo(expected));
  }

  @Test
  public void testEndingTimes() throws Exception {
    int[] durations = {5, 3, 7, 2};

    int startTime = 3;
    JobSchedule jobSchedule = new JobSchedule(new Job(TEST_JOB, durations), startTime);

    int[] endingTimes = jobSchedule.getDepartureTimes();

    int[] expected = {8, 11, 18, 20};
    assertThat(endingTimes, equalTo(expected));
  }

  @Test
  public void testJobIntervals() throws Exception {
    int[] durations = {5, 3, 7, 2};

    int startTime = 4;
    JobSchedule jobSchedule = new JobSchedule(new Job(TEST_JOB, durations), startTime);

    Interval[] expected = {new Interval(4, 9), new Interval(9, 12), new Interval(12, 19), new Interval(19, 21)};

    assertThat(jobSchedule.getJobIntervals(), equalTo(expected));
  }

  @Test
  public void testCalculateNextStartTime() throws Exception {
    int[] durations = {5, 4, 4, 3};

    JobSchedule jobSchedule = new JobSchedule(new Job(TEST_JOB, durations), 0);

    int[] nextDurations = {5, 4, 4, 6};
    Job nextJob = new Job("TestJob2", nextDurations);

    int start = JobSchedule.calculateNextStart(jobSchedule, nextJob);

    assertThat("Bad start found.", start, equalTo(5));
  }

  //endregion
}
