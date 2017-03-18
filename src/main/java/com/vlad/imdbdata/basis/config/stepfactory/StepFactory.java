package com.vlad.imdbdata.basis.config.stepfactory;

import org.springframework.batch.core.Step;

/*
* Each step factory is responsible for creating a... step
* */
public interface StepFactory {
    Step createStep();
}
