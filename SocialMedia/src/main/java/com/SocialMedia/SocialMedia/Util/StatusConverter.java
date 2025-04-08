package com.SocialMedia.SocialMedia.Util;

import com.SocialMedia.SocialMedia.Static.Status;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class StatusConverter implements DynamoDBTypeConverter<String, Status> {
    @Override
    public String convert(Status status) {
        return status.toString();
    }

    @Override
    public Status unconvert(String s) {
        return Status.valueOf(s);
    }
}
