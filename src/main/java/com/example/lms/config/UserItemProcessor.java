package com.example.lms.config;

import com.example.lms.model.UserData;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<UserData, UserData> {
    @Override
    public UserData process(UserData UserData) throws Exception {
        return UserData;
    }
}
