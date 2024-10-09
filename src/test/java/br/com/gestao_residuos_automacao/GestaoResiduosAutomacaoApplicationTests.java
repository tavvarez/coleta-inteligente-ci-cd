import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.mockito.Mockito;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.context.support.WithMockUser;
import br.com.gestao_residuos_automacao.controller.AgendamentoColetaController;
import br.com.gestao_residuos_automacao.service.AgendamentoColetaService;

import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;

@WebMvcTest(AgendamentoColetaController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {GestaoResiduosAutomacaoApplicationTests.TestConfig.class})
public class GestaoResiduosAutomacaoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoColetaController agendamentoColetaController;

    @Test
    @WithMockUser(username = "teste", password = "123", roles = "USER")
    public void testGetAgendamentoDeColeta() throws Exception {
        mockMvc.perform(get("/agendamento-de-coleta/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "teste", password = "123", roles = "USER")
    public void testPostAgendamentoDeColeta() throws Exception {
        String requestBody = "{ \"agendamentoId\": 100, \"rota\": { \"rotaId\": 1, \"descricao\": \"Rota 77\", \"caminhaoId\": 1, \"data\": 1713668400000, \"latitude\": 40.7128, \"longitude\": -74.006, \"capacidadeAtual\": 8.0 }, \"dataAgendada\": 1714359600000, \"status\": \"Pendente\"}";
        mockMvc.perform(post("/agendamento-de-coleta")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("teste", "123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "teste", password = "123", roles = "USER")
    public void testPutAgendamentoDeColeta() throws Exception {
        String requestBody = "{ \"agendamentoId\": 100, \"rota\": { \"rotaId\": 1, \"descricao\": \"Rota 77\", \"caminhaoId\": 1, \"data\": 1713668400000, \"latitude\": 40.7128, \"longitude\": -74.006, \"capacidadeAtual\": 8.0 }, \"dataAgendada\": 1714359600000, \"status\": \"Cocnluida\"}";
        mockMvc.perform(put("/agendamento-de-coleta/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "teste", password = "123", roles = "USER")
    public void testDeleteAgendamentoDeColeta() throws Exception {
        mockMvc.perform(delete("/agendamento-de-coleta/100"))
                .andExpect(status().isNoContent());
    }

    @Configuration
    static class TestConfig {
        @Bean
        public AgendamentoColetaController agendamentoColetaController() {
            return Mockito.mock(AgendamentoColetaController.class);
        }
    }
}
