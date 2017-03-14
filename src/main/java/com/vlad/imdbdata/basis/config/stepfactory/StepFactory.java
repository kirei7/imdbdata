package com.vlad.imdbdata.basis.config.stepfactory;

import org.springframework.batch.core.Step;


public interface StepFactory {
    Step createStep();
}
