package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.repository.ClientRepository;
import meli.freshfood.utils.ClientUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceImplTest {

    @InjectMocks
    ClientServiceImpl clientServiceImp;

    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Retorna o cliente quando ele existir")
    void findById_returnClient_whenClientIdExists() {

        Optional<Client> clientMocked = Optional.of(ClientUtils.newClient());

        BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(clientMocked);

        Client client = clientServiceImp.findById(ArgumentMatchers.anyLong());

        assertThat(client.getClientId()).isPositive();
        assertThat(client.getClass()).isEqualTo(Client.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o Id não exista")
    void returnNotFoundException_whenClientIdNotExists() {
        BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O cliente não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> clientServiceImp.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O cliente não foi encontrado!");
    }
}