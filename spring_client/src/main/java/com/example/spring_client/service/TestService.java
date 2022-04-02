package com.example.spring_client.service;

import com.example.spring_client.entity.Test;
import java.util.List;

public interface TestService {
    public String add(String user, String pass);

    public String delete(String user);

    public String update(String user, String password);

    public List<Test> queAll();

    public Test queOne(String user);
}
