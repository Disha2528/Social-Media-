package com.coursemanagement.CourseManagement.Util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.coursemanagement.CourseManagement.Static.Role;

public class RoleConverter implements DynamoDBTypeConverter<String, Role> {

    @Override
    public String convert(Role role) {
        return role.name();
    }

    @Override
    public Role unconvert(String value) {
        return Role.valueOf(value);
    }
}
