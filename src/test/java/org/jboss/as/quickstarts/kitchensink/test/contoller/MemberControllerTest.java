package org.jboss.as.quickstarts.kitchensink.test.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.as.quickstarts.kitchensink.controller.MemberController;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.jboss.as.quickstarts.kitchensink.util.MemberListHolder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberRegistration memberRegistration;

    @MockBean
    private MemberListHolder memberListHolder;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void whenAuthenticated_thenGetMembers() throws Exception {
        List<Member> members = List.of(new Member("1", "John Doe", "john@example.com", "1234567890"));
        when(memberListHolder.getMembers()).thenReturn(members);

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(view().name("members/list"))
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attribute("members", hasSize(1)));
    }

    @Test
    void whenUnauthenticated_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/members")
                        .header("Accept", "text/html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void whenAuthenticatedAndValidInput_thenAddMember() throws Exception {
        mockMvc.perform(post("/members")
                        .param("name", "Jane Doe")
                        .param("email", "jane@example.com")
                        .param("phoneNumber", "9876543217")
                                .with(csrf())
                        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/members?success"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void whenAuthenticatedAndInvalidInput_thenShowErrors() throws Exception {
        mockMvc.perform(post("/members")
                        .param("name", "") // Invalid name
                        .param("email", "invalid-email") // Invalid email
                        .param("phoneNumber", "123") // Invalid phone number
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("members/list"))
                .andExpect(model().attributeHasFieldErrors("member", "name", "email", "phoneNumber"));
    }

    @Test
    void whenUnauthenticatedAndSubmit_thenRedirectToLogin() throws Exception {
        mockMvc.perform(post("/members")
                        .param("name", "Jane Doe")
                        .param("email", "jane@example.com")
                        .param("phoneNumber", "0987654321")
                        .with(csrf())
                        .header("Accept", "text/html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }


}
