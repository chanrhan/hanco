package com.hanco.hanco.job;

import com.hanco.hanco.mapper.SolvedAcMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

// 주차별 달성 기록 Job
@RequiredArgsConstructor
@Slf4j
@Component
public class RecordWeeklySolvedJob implements Job {
	private final SolvedAcMapper solvedAcMapper;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("Coding Study Weekly Job is processing!");

		LocalDate today = LocalDate.now();
		if(today.getDayOfWeek() != DayOfWeek.SUNDAY){
			log.error("Weekly Job must have run at Sunday!");
			return;
		}
		solvedAcMapper.insertWeeklyScore(60, 2000);
	}
}
