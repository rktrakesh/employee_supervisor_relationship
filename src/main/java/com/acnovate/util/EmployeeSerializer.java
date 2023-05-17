package com.acnovate.util;

import com.acnovate.entities.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EmployeeSerializer extends JsonSerializer<Employee> {

    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", employee.getId());
        jsonGenerator.writeStringField("name", employee.getName());

        Employee supervisor = employee.getSupervisor();
        if (supervisor != null) {
            jsonGenerator.writeStringField("supervisor", supervisor.getName());
        }

        jsonGenerator.writeEndObject();
    }
}
