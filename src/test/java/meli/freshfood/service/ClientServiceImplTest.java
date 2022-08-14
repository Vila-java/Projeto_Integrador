package meli.freshfood.service;

import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.repository.ClientRepository;
import meli.freshfood.utils.ClientUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;

import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceImplTest {

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Retorna o cliente quando ele existir")
    void findById_returnClient_whenClientIdExists() {

        Optional<Client> clientMocked = Optional.of(ClientUtils.newClient());

        BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(clientMocked);

        Client client = clientService.findById(ArgumentMatchers.anyLong());

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
                () -> clientService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O cliente não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna a lista de produtos quando existir")
    void findAllClients_returnClientList_whenClientListExists() {

        List<Client> clientListMocked = ClientUtils.clientList();

        BDDMockito.when(clientRepository.findAll())
                .thenReturn(clientListMocked);

        List<Client> clientList = clientService.findAllClient();
        assertThat(clientList).isNotNull();
        assertThat(clientList.size()).isEqualTo(clientListMocked.size());
    }

    @Test
    @DisplayName("Retorna exceção caso a lista de clientes não exista")
    void returnNotFoundException_whenFindAllClientsNotExists() {

        BDDMockito.when(clientRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Client> clientListMocked = ClientUtils.clientList();
        List<Client> clientList = clientService.findAllClient();

        assertThat(clientList).isNotNull();
        assertThat(clientList).isEmpty();
        assertThat(clientList.size()).isNotEqualByComparingTo(clientListMocked.size());
    }

    @Test
    @DisplayName("Salva um novo cliente caso seja válido")
    public void save_savePerson_whenValidNewPerson() {
        when(clientRepository.save(ArgumentMatchers.any(Client.class)))
                .thenReturn(ClientUtils.newClient());

        Client client = ClientUtils.newClient();

        Client newClient = clientService.save(client);

        assertThat(newClient).isNotNull();
        assertThat(newClient.getClientId()).isPositive();
        assertThat(newClient.getFirstName()).isEqualTo(client.getFirstName());
        verify(clientRepository, times(1)).save(client);
    }

/*    @Test
    @DisplayName("Retorna exceção caso novo cliente seja inválido")
    public void save_throwException_whenClientIsNotValid() {
        Client client = ClientUtils.newClient();

        assertThrows(BadRequestException.class, () ->{
            clientService.save(client);
        });

        verify(clientRepository, never()).save(client);
    }*/

    @Test
    public void update_returnPersonUpdated_whenValidPerson() {
        Client client = ClientUtils.newClient();

        when(clientRepository.save(client))
                .thenReturn(client);
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.of(client));

        client.setFirstName("Altere o nome");

        Client updatedPerson = clientService.update(client);

        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.getFirstName()).isEqualTo(client.getFirstName());
    }

    @Test
    @DisplayName("Deleta o cliente")
    void deleteClient_whenClientExists() {
        Long client = ClientUtils.newClient().getClientId();

        BDDMockito.willDoNothing().given(clientRepository).deleteById(anyLong());

        clientRepository.deleteById(client);

        verify(clientRepository, only()).deleteById(client);
    }

    @Test
    @DisplayName("Retorna exceção caso não seja possível deletar cliente")
    public void delete_ThrowException_whenIdNotExist() {
        final Long NotExistId = 999L;

        assertThrows(NotFoundException.class, () -> {
            clientService.delete(NotExistId);
        });

        verify(clientRepository, never()).deleteById(NotExistId);
    }
}