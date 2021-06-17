package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional


@Controller(value = "/autores")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put(value = "/{id}")
    @Transactional
    //micronaut consegue fazer binding/linkar a variavel descrição que vem no argumento do método
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        val optionalAutor = autorRepository.findById(id)
        if (optionalAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = optionalAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }

}