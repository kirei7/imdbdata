package com.vlad.imdbdata.basis.batch.reader;

import com.vlad.imdbdata.basis.batch.reader.util.DataFetcher;
import com.vlad.imdbdata.basis.batch.reader.util.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Map;

public class CommonMediaInfoReader implements ItemReader<Map<String, String>> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonMediaInfoReader.class);

    private DataFetcher dataFetcher;
    private final String apiUrl;
    private Map<String, String> data;
    private boolean isReaded;


    public CommonMediaInfoReader(DataFetcher dataFetcher, String apiUrl) {
        this.apiUrl = apiUrl;
        this.dataFetcher = dataFetcher;
    }

    //here all connections and data fetching occurs
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        isReaded = false;
        Map<String, JobParameter> parameters = stepExecution
                .getJobExecution()
                .getJobParameters()
                .getParameters();
        String url = apiUrl + DataMapper.makeUrl(parameters);
        data = dataFetcher.fetchData(url);
    }

    @Override
    public Map<String, String> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (isReaded) {
            return null;
        }
        isReaded = true;
        LOGGER.info("read() returns: " + data.toString());
        return data;
    }
}
