package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.batch.RemoteItemReader;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.ResourcesItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

/*    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public DataSource dataSource;*/

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ItemReader<MediaInfoEntity> reader(Environment env) {
        RemoteItemReader reader = new RemoteItemReader(
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
    }

    /*@Bean
    public ItemReader itemReader(Environment env, RestTemplate restTemplate) {
        RemoteItemReader reader = new RemoteItemReader(
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

    /*@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

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
