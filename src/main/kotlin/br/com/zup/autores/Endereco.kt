package br.com.zup.autores

import io.micronaut.core.annotation.Introspected
import javax.persistence.Embeddable


@Embeddable
@Introspected
class Endereco(
    enderecoResponse: EnderecoResponse,
    val numero: String
) {
    val rua = enderecoResponse.rua
        get() = field
    val cidade = enderecoResponse.cidade
        get() = field
    val estado = enderecoResponse.estado
        get() = field
}
