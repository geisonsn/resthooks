package bosh.resthooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResthooksApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	 void getAllHooks_success() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders
				.get("/subscriptions2")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

//	@Test
//	void getHookById_success() throws Exception {
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.get("/subscriptions/1")
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk());
//	}
//
//	@Test
//	void getHookById_notfound() throws Exception {
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.get("/subscriptions/10000")
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isNotFound());
//	}
//
//	@Test
//	void createHook_success() throws Exception {
//		Hook hook = Hook.builder()
//			.url("subscriptions/new")
//			.topicId("hkljkwewewe212121")
//			.hookUri("http://localhost:8080/subscriptions/new")
//			.topicName("hook.new")
//			.topicDescription("New topic");
//
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.post("/subscriptions")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(hook))
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isCreated());
//	}
//
//	@Test
//	void updateHook_success() throws Exception {
//		Hook hook = Hook.builder()
//			.url("subscriptions/update")
//			.topicId("hkljkwewewe212121")
//			.hookUri("http://localhost:8080/subscriptions/update")
//			.topicName("hook.updated")
//			.topicDescription("Updated topic");
//
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.put("/subscriptions/1")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(hook))
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk());
//	}
//
//	@Test
//	void updateHook_notfound() throws Exception {
//		Hook hook = Hook.builder()
//			.url("subscriptions/update")
//			.topicId("hkljkwewewe212121")
//			.hookUri("http://localhost:8080/subscriptions/update")
//			.topicName("hook.updated")
//			.topicDescription("Updated topic");
//
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.put("/subscriptions/11")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(hook))
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isNotFound());
//	}
//
//	@Test
//	void deleteHook_success() throws Exception {
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.delete("/subscriptions/2")
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk());
//	}
//
//	@Test
//	void deleteHook_notfound() throws Exception {
//		this.mockMvc
//			.perform(MockMvcRequestBuilders
//				.delete("/subscriptions/20")
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isNotFound());
//	}

}
