package com.volkov.demoproducer.repository;

import com.lordofthejars.nosqlunit.annotation.CustomComparisonStrategy;
import com.lordofthejars.nosqlunit.annotation.IgnorePropertyValue;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoFlexibleComparisonStrategy;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import com.volkov.demoproducer.service.DemoUserService;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CustomComparisonStrategy(comparisonStrategy = MongoFlexibleComparisonStrategy.class)
public class DemoUserRepositoryTest {

    @Autowired
    DemoUserService demoUserService;

    @ClassRule
    InMemoryMongoDb inMemoryMongoDb = InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule().build();

    @UsingDataSet(locations = "InitialDataSave.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location = "ExpectedDataSave.json")
    @IgnorePropertyValue(properties = {"creationDate"})
    @Test
    public void when_saveDemoUser_then_success() {
        demoUserService.saveDemoUser(new DemoUserDTO("test", "test"));
    }

    @UsingDataSet(locations = "ExpectedDataDelete.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location = "InitialDataDelete.json")
    @IgnorePropertyValue(properties = {"creationDate"})
    @Test
    public void when_deleteDemoUser_then_success() {
        demoUserService.deleteDemoUserByUsername("test");
    }
}
