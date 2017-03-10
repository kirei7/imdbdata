package com.vlad.imdbdata.basis.service;

import com.vlad.imdbdata.basis.batch.RemoteMovieItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 09.03.17.
 */
@Service
public class MediaInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaInfoService.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Map<MediaType, Job> jobs;

    public void importMedia(String title, Integer year, MediaType type) {
        Job job = jobs.get(type);
        try {
            jobLauncher.run(
                    job,
                    new JobParameters(makeParameters(title, year))
            );
        } catch (JobInstanceAlreadyCompleteException
                | JobExecutionAlreadyRunningException
                | JobParametersInvalidException
                | JobRestartException ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    protected Map<String, JobParameter> makeParameters(String title, Integer year) {
        Map<String, JobParameter> map = new HashMap<String, JobParameter>() {
            {
                put("title", new JobParameter(title));
            }
        };
        if (year != null) map.put("year", new JobParameter(new Long(year)));
        return map;
    }
}
