package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.models.Role;
import org.springframework.core.convert.converter.Converter;


@Configuration
public class ConverterToRole implements Converter <String,Role>{
    @Override
    public Role convert(String id){
        Role role = new Role();
        role.setId(Long.valueOf(id));
        return role;
    }
}
