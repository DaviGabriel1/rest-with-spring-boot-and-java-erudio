package br.org.davi.controllers;

import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import br.org.davi.util.MediaType;

import static br.org.davi.util.MediaType.*;

@RestController
@RequestMapping("/api/person/v1") //mapping a nivel de controller
@Tag(name = "People",description = "Endpoints for managing peoples")
public class PersonController{

    @Autowired //fará a instanciação do service em tempo de execução (private PersonService service = new PersonService())
    private PersonService service;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}/*indica que produz json*/)
    @Operation(summary = "finds a person", description = "finds a person",tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess",responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "No Content",responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unathorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal server error",responseCode = "500",content = @Content)
            }
    )
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return service.findById(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "finds all people", description = "finds all people",tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess",responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unathorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal server error",responseCode = "500",content = @Content)
            }
    )
    public List<PersonVO> findAll(){
        return service.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}/*indica que consome json*/)
    @Operation(summary = "create a person",description = "create a person", tags = {"People"},
    responses = {
            @ApiResponse(description = "Created",responseCode = "200",content = @Content(
                    schema = @Schema(implementation = PersonVO.class)
            )),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
            @ApiResponse(description = "Unathorized",responseCode = "401",content = @Content),
            @ApiResponse(description = "Internal Server Error",responseCode = "500",content = @Content)
    }
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception{
        return service.create(person);
    }

    @Operation(summary = "updating a person", description = "updating a person", tags = {"Person"},
            responses = {
            @ApiResponse(description = "Updated",responseCode = "200",content = @Content(
                    schema = @Schema(implementation = PersonVO.class)
            )),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
            @ApiResponse(description = "Unathorized",responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found",responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Server Error",responseCode = "500",content = @Content)
            }
    )
    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}/*indica que consome json*/)
    public PersonVO update(@RequestBody PersonVO person) throws Exception{
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "deleting a person",description = "deleting a person",tags = {"People"},responses = {
            @ApiResponse(description = "No Content",responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
            @ApiResponse(description = "Unathorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500",content = @Content)
    }

    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //retorna o status code 204 no content, ideal para delete
    }

}
