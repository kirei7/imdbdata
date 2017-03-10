package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.batch.RemoteMovieItemReader;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import com.vlad.imdbdata.basis.repos.MediaInfoRepository;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:batch.properties")
public class BatchConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Environment env;
    @Autowired
    private MediaInfoRepository mediaInfoRepository;

 /*   @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(batchDataSource());
    }
    @Bean
    public DataSource batchDataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setDriverClass(JDBCDriver.class);
        ds.setUrl("jdbc:hsqldb:mem:.");
    }*/

    @Bean
    public Map<MediaType, Job> jobs() {
        Map<MediaType, Job> jobs = new HashMap<>();
        jobs.put(MediaType.MOVIE, movieJob());
        //TODO add seriesJob
        return jobs;
    }

    @Bean
    public Job movieJob() {
        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .flow(movieStep1())
                .end()
                .build();
    }

    @Bean
    public Step movieStep1() {
        return stepBuilderFactory.get("movieStep1")
                .<MediaInfoEntity, MediaInfoEntity> chunk(1)
                .reader(movieReader(env))
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<MediaInfoEntity> movieReader(Environment env) {
        RemoteMovieItemReader reader = new RemoteMovieItemReader(
                env.getProperty("remote.api.url"),
                restTemplate()
        );
        return reader;
    }

    //TODO
    @Bean
    public StringItemProcessor processor() {
        return new StringItemProcessor();
    }
    static class StringItemProcessor implements ItemProcessor<MediaInfoEntity, MediaInfoEntity> {
        private int index = 0;
        @Override
        public MediaInfoEntity process(MediaInfoEntity s) throws Exception {
            if (index != 0) return null;
            index++;
            return s;
        }
    }
    @Bean
    public RepositoryItemWriter<MediaInfoEntity> writer() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            @Override
            public void write(List items) throws Exception{
                    LOGGER.debug("WRITE!");
                    super.write(items);
            }
        };
        writer.setRepository(mediaInfoRepository);
        writer.setMethodName("save");
        return writer;
    }

    //TODO end
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //the following two beans is used to avoid persisting batch metadata in a database
    /*@Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }*/

/*    @Bean
    public ItemReader<MediaInfoEntity> reader(Environment env) {
        RemoteMovieItemReader reader = new RemoteMovieItemReader(
            env.getProperty("remote.api.url"),
            restTemplate()
        );
        reader.setQueryParams("?i=tt0944947&Season=1");
        try {
            LOGGER.debug(reader.read().getImdbId());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return reader;
    }*/

    /*@Bean
    public ItemReader itemReader(Environment env, RestTemplate restTemplate) {
        RemoteMovieItemReader reader = new RemoteMovieItemReader(
                env.getProperty("remote.api.url"),
                restTemplate
        );
        reader.setQueryParams("?i=tt0944947&season=1&episode=1");
        try {
            System.out.println(reader.read().getImdbId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }*/

    /*

    // tag::readerwriterprocessor[]
    @Bean
    public ItemReader reader() {
        ResourcesItemReader reader = new ResourcesItemReader();
        try {
            reader.setResources(new Resource[]{new UrlResource("http://www.omdbapi.com/?i=tt0944947&Season=1")});
        } catch (MalformedURLException ex) {
            LOGGER.error(ex.toString());
        }
        try {
            LOGGER.debug("MARK", reader.read().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return reader;
    }

    @Bean
    public StringItemProcessor processor() {
        return new StringItemProcessor();
    }
    static class StringItemProcessor implements ItemProcessor<String, String> {

        @Override
        public String process(String s) throws Exception {
            return s;
        }
    }
    @Bean
    public JdbcBatchItemWriter<String> writer() {
        JdbcBatchItemWriter<String> writer = new JdbcBatchItemWriter<String>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<String>());
        writer.setSql("INSERT INTO info (str_info) VALUES (:strInfo)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }*/
    // end::jobstep[]
    /*@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("test.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }*/
    // end::jobstep[]
}
