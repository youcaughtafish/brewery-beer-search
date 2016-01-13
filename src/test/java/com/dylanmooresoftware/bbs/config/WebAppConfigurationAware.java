package com.dylanmooresoftware.bbs.config;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.dylanmooresoftware.bbs.config.ApplicationConfig;
import com.dylanmooresoftware.bbs.config.LuceneConfig;
import com.dylanmooresoftware.bbs.config.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfig.class,
        WebMvcConfig.class,
        LuceneConfig.class
})
@PropertySource("classpath:application.properties")
public abstract class WebAppConfigurationAware {
  @Inject
  protected WebApplicationContext wac;
  protected MockMvc mockMvc;

  @Before
  public void before() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

}
