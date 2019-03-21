package com.dtb.saesc.api.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LinkServiceTest {
	@MockBean
	private LinkRepository repository;
	
	@Autowired
	private LinkService service;
	
	private static final String IP = "";
	
	@Before
	public void init() {
	}
}
