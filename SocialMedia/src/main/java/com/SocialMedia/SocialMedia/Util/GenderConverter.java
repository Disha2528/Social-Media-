package com.SocialMedia.SocialMedia.Util;

import com.SocialMedia.SocialMedia.Static.Gender;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class GenderConverter implements DynamoDBTypeConverter<String , Gender> {
    @Override
    public String convert(Gender gender) {
        return gender.toString();
    }

    @Override
    public Gender unconvert(String s) {
        return Gender.valueOf(s);
    }

}
