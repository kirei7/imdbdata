package com.vlad.imdbdata.basis.service;

import com.vlad.imdbdata.basis.batch.ValueContainer;
import com.vlad.imdbdata.basis.config.MediaType;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
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
import org.springframework.stereotype.Service;

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
    @Autowired
    private CommonMediaRepository repository;
    @Autowired
    private ValueContainer container;

    public String importMedia(String title, Integer year, MediaType type) {
        Job job = jobs.get(type);
        try {
            jobLauncher.run(
                    job,
                    new JobParameters(makeParameters(title, year, type))
            );
        } catch (JobInstanceAlreadyCompleteException
                | JobExecutionAlreadyRunningException
                | JobParametersInvalidException
                | JobRestartException ex) {
            LOGGER.error(ex.getMessage());
        }
        String resultType = container.getMediaType();
        String resultTitle = container.getMediaTitle();
        String result;
        if (resultType == null || resultTitle == null)
            result = "An error occurred, see log for more details";
        else
            result= resultType + " " + resultTitle + " was successfully imported";
        container.setMediaTitle(null);
        container.setMediaType(null);
        return result;
    }

    public long itemsCount() {
        return repository.count();
    }

    Map<String, JobParameter> makeParameters(String title, Integer year, MediaType type) {
        Map<String, JobParameter> map = new HashMap<String, JobParameter>() {
            {
                put("title", new JobParameter(title));
                put("type", new JobParameter(type.toString()));
            }
        };
        if (year != null) map.put("year", new JobParameter(new Long(year)));
        return map;
    }
}
