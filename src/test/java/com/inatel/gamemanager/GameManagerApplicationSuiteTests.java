package com.inatel.gamemanager;

import com.inatel.gamemanager.clients.publishermanager.PublisherManagerClientTest;
import com.inatel.gamemanager.clients.publishermanager.models.RegisterRequestBodyTest;
import com.inatel.gamemanager.controllers.GameManagementControllerTest;
import com.inatel.gamemanager.controllers.PublisherManagerListenerControllerTest;
import com.inatel.gamemanager.exceptions.settings.ExceptionDetailsTest;
import com.inatel.gamemanager.exceptions.settings.GlobalExceptionHandlerTest;
import com.inatel.gamemanager.models.dtos.requests.GameRequestTest;
import com.inatel.gamemanager.models.dtos.responses.GameResponseTest;
import com.inatel.gamemanager.models.dtos.responses.HttpResponseTest;
import com.inatel.gamemanager.models.entities.GameTest;
import com.inatel.gamemanager.services.GameManagementServiceTest;
import com.inatel.gamemanager.utils.JsonConverterUtilTest;
import com.inatel.gamemanager.utils.TimeFormatterUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		// clients dir
		RegisterRequestBodyTest.class,
		PublisherManagerClientTest.class,

		// controllers dir
		GameManagementControllerTest.class,
		PublisherManagerListenerControllerTest.class,

		// exceptions dir
		ExceptionDetailsTest.class,
		GlobalExceptionHandlerTest.class,

		// models dir
		GameRequestTest.class,
		GameResponseTest.class,
		HttpResponseTest.class,
		GameTest.class,

		// services dir
		GameManagementServiceTest.class,

		// utils dir
		JsonConverterUtilTest.class,
		TimeFormatterUtilTest.class
})

public class GameManagerApplicationSuiteTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
	}
}

