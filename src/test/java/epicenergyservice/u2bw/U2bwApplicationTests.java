package epicenergyservice.u2bw;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class U2bwApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	//	private String nome;
//	private String cognome;
//	private String email;
	private String username;
	private String password;
	private String loginToken;

	//	@Value("${admin.nome}")
//	public void setAdminNome(String s) {
//		this.nome = s;
//	}
//	@Value("${admin.cognome}")
//	public void setAdminCognome(String s) {
//		this.cognome = s;
//	}
//	@Value("${admin.email}")
//	public void setAdminEmail(String s) {
//		this.email = s;
//	}
	@Value("${admin.username}")
	public void setAdminUserName(String s) {
		this.username = s;
	}

	@Value("${admin.password}")
	public void setAdminPassword(String s) {
		this.password = s;
	}

	String setLoginUserJson;

	public void setLoginUserJson(String username, String password) {
		this.setLoginUserJson = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	//@BeforeAll
	@Test
	void testUserLogin() throws Exception {
		setLoginUserJson(username, password);
		log.info(setLoginUserJson);
//		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
//				.accept(MediaType.APPLICATION_JSON).content(setLoginUserJson)
//				.contentType(MediaType.APPLICATION_JSON));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/auth/login")
				.accept(MediaType.APPLICATION_JSON).content(setLoginUserJson)
				.contentType(MediaType.APPLICATION_JSON);

		log.info(requestBuilder.toString());
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
//
//		assertEquals("http://localhost:5080/auth/login",
//				response.getHeader(HttpHeaders.LOCATION));
//
//
//		log.info(response.toString());
	}
	@Test
	void testUtenti() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/utenti")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXVybyIsImlhdCI6MTY4NzQ2OTMzNCwiZXhwIjoxNjg4MDc0MTM0fQ.VkMjjugul6oZ1Op1N3lp6bO5wh3TJFEH_WXjLZmzxUc");
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	@Test
	void testComuni() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/comuni")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXVybyIsImlhdCI6MTY4NzQ2OTMzNCwiZXhwIjoxNjg4MDc0MTM0fQ.VkMjjugul6oZ1Op1N3lp6bO5wh3TJFEH_WXjLZmzxUc");
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
}

//	@Test
//	void getClienti() throws Exception {
//
////		RequestBuilder requestBuilder = MockMvcRequestBuilders
////				.get("/clienti")
////				.header("Authorization", "Bearer " + loginToken);
//////				.accept(MediaType.APPLICATION_JSON).content(setLoginUserJson)
//////				.contentType(MediaType.APPLICATION_JSON);
////		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
////	MvcResult response = mockMvc
////			.perform(get("/clienti")
////					.header("Authorization", "Bearer " + loginToken)
////					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
////					.accept(MediaType.APPLICATION_JSON))
////			.andExpect(status().isOk())
////			.andReturn();
//
////	}
//}


//    mockMvc.perform(post("/users/reset_token")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(ResetPasswordTokenDTO))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.resetPasswordToken").value(randomString_254))
//			.andExpect(jsonPath("$.login").value("login"))
//			.andExpect(jsonPath("$.status").value("200"))
//			.andExpect(jsonPath("$.error").value(""))
//			.andExpect(jsonPath("$.errorDescription").value(""));

//	String token = "your_token_here";
//	MvcResult response = mockMvc
//			.perform(post("/auth/login")
//					.header("Authorization", "Bearer " + token)
//					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//					.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andReturn();