package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

@Controller(value = "/autores")
class DeletaAutorController(private val autorRepository: AutorRepository) {

    @Delete(value = "/{id}")
    @Transactional
    fun deleta(@PathVariable id: Long): HttpResponse<Any>{
        val optionalAutor = autorRepository.findById(id)
        if (optionalAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }

}