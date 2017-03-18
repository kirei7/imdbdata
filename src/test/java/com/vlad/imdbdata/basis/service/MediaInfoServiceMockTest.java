package com.vlad.imdbdata.basis.service;

import com.vlad.imdbdata.basis.batch.ValueContainer;
import com.vlad.imdbdata.basis.config.MediaType;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/*
* This test has the same functionality as MediaInfoServiceTest,
* the difference is here MockitoJUnitRunner used, so there is
* no need to create an application context and in all that config files mess.
* There is no need in 2 identical tests (this one is enough), but
* I left other test just for presentation purpose.
* */
@RunWith(MockitoJUnitRunner.class)
public class MediaInfoServiceMockTest extends TestCase{

    @InjectMocks
    private MediaInfoService service;
    @Mock
    private JobLauncher jobLauncher;
    @Mock
    private ValueContainer valueContainer;
    @Spy
    private Map<MediaType, Job> jobs;

    public MediaInfoServiceMockTest() {
        jobs = new HashMap<>();
        jobs.put(MediaType.MOVIE, mock(Job.class));
        jobs.put(MediaType.SERIES, mock(Job.class));
    }

    @Test
    public void jobShouldBeLaunchedOnImportMediaCall()
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {
        service.importMedia("title", 1974, MediaType.MOVIE);
        verify(jobLauncher).run(any(Job.class), any(JobParameters.class));
        reset(jobLauncher);
    }

    @Test
    public void nullsInValueContainerShouldCauseErrorMessageReturn() {
        String str = "someStr";
        final String errorMsg = "An error occurred, see log for more details";

        when(valueContainer.getMediaTitle()).thenReturn(null);
        when(valueContainer.getMediaType()).thenReturn("movie");
        assertEquals(
                errorMsg,
                service.importMedia(str, 1994, MediaType.MOVIE)
        );

        when(valueContainer.getMediaTitle()).thenReturn("Star Wars");
        when(valueContainer.getMediaType()).thenReturn(null);
        assertEquals(
                errorMsg,
                service.importMedia(str, 1994, MediaType.MOVIE)
        );
        reset(jobLauncher);
    }

    @Test
    public void makeUrlShouldReturnProperMap() {
        String title = "title";
        Integer year = 1963;
        MediaType type = MediaType.SERIES;
        Map<String, JobParameter> result = service.makeParameters(title, year, type);
        assertEquals(title, result.get("title").getValue());
        assertEquals(new Long(year), result.get("year").getValue());
        assertEquals(type.toString(), result.get("type").getValue());
    }

}
