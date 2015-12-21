/*
 * Copyright 2015 Wipro Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wipro.ats.bdre.md.dao;

import com.wipro.ats.bdre.md.dao.jpa.Users;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by PR324290 on 10/28/2015.
 */
public class UsersDAOTest {

    private static final Logger LOGGER = Logger.getLogger(UsersDAOTest.class);

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        AutowireCapableBeanFactory acbFactory = context.getAutowireCapableBeanFactory();
        acbFactory.autowireBean(this);
    }


    @Autowired
    UsersDAO usersDAO;

    @Test
    public void testList() throws Exception {
        LOGGER.info("Size of Users is atleast:" + usersDAO.list(0, 10).size());
    }

    @Test
    public void testTotalRecordCount() throws Exception {
        LOGGER.info("Size of Users is:" + usersDAO.totalRecordCount());
    }


    @Test
    @Ignore
    public void testGet() throws Exception {

        LOGGER.info("Users(0) passwd:" + usersDAO.get("admin").getUsername());
    }


    @Test
    public void testInsertUpdateAndDelete() throws Exception {
        Users users = new Users();
        users.setUsername("Test");
        users.setPassword("Test");
        users.setEnabled(true);

        String usersId = usersDAO.insert(users);
        LOGGER.info("New Users added with ID:" + usersId);
        users.setPassword("Test_updated");
        usersDAO.update(users);
        users = usersDAO.get(usersId);
        LOGGER.info("Updated users with passwd:" + users.getPassword());
        usersDAO.delete(usersId);
        LOGGER.info("Users Deleted with ID:" + usersId);
    }
}