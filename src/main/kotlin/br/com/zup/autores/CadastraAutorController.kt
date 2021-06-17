package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController (val autorRepository: AutorRepository, val enderecoClient: EnderecoClient){

    @Post
    @Transactional
    fun cadastra(@Valid @Body request: NovoAutorRequest): HttpResponse<NovoAutorRequest>{

        val enderecoResponse = EnderecoResponse(rua = "Baixada", cidade = "Camaçari", estado = "BA")

        val autor = request.toAutor(enderecoResponse)
        autorRepository.save(autor)
        val  uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf("id" to autor.id))
        return HttpResponse.created<Any?>(uri).body(request)
    }

}