package br.com.zup.autores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected // precisamos user o instrospected pq em momento de compilação o micronaut ler esse @introspected
// e ele cria um bean de instrospecção em tempo de compilação, assim ele consegue acessar os atributos da classe
// e assim ele consegue por exemplo fazer a validação
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String

) {
    fun toAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse, numero)
        return Autor( nome,  email,  descricao, endereco)
    }



}
