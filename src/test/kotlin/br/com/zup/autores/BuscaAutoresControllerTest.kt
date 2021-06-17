package br.com.zup.autores

import io.micronaut.http.*
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.URI
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest{

//    @field:Inject
//    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client(value = "/")
    lateinit var client: HttpClient
    lateinit var enderecoResponse: EnderecoResponse
    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        enderecoResponse = EnderecoResponse(rua = "Baixada", cidade = "Camaçari", estado = "BH")
        val endereco = Endereco(enderecoResponse = enderecoResponse, numero = "11111-11")
        autor = Autor(nome = "Rodrigo Amalri", email = "rodrigo@gmail.com", descricao = "Oi meu chapa", endereco = endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun deveRetornarOsDetalhesDeUmAutor() {
        val create = URI.create("Valor")


        val response =
            client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body().nome)
        assertEquals(autor.email, response.body().email)
        assertEquals(autor.descricao, response.body().descricao)
    }

//    fun deveCadastraUmNovoAutor(){
//
//        val novoAutorRequest = NovoAutorRequest(nome = "Jefferson", email = "jefferson@gmail.com",
//            descricao = "alguma coisa", cep = "111111", numero = "123333")
//        val request = HttpRequest.POST("/autores", novoAutorRequest)
//        val response = client.toBlocking().exchange(request, Any::class.java)
//
//        Mockito.`when`(enderecoClient.consultaJSON(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))
//
//        assertEquals(HttpStatus.CREATED, response.status)
//        assertTrue(response.headers.contains("Location"))
//        assertTrue(response.header("location").matches("/autores/\\d".toRegex()))
//    }
//    @MockBean
//    fun enderecoMock(): EnderecoClient{
//        return Mockito.mock(EnderecoClient::class.java)
//    }
}