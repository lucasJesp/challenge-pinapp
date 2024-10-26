package com.pinapp.msvc.clientes.controllers;

import com.pinapp.msvc.clientes.controllers.request.ClienteRequest;
import com.pinapp.msvc.clientes.controllers.response.ClienteConFechaProbableMuerteResponse;
import com.pinapp.msvc.clientes.controllers.response.ClienteKPIResponse;
import com.pinapp.msvc.clientes.controllers.response.ClienteResponse;
import com.pinapp.msvc.clientes.models.dto.ClienteConFechaProbableMuerte;
import com.pinapp.msvc.clientes.models.dto.ClienteKPI;
import com.pinapp.msvc.clientes.models.entity.Cliente;
import com.pinapp.msvc.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pinapp.msvc.clientes.util.Messages.FECHAS_PROBABLES_NO_OBTENIDAS;
import static com.pinapp.msvc.clientes.util.Messages.KPI_NO_OBTENIDOS;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/creacliente")
    @Operation(summary = "Crea un nuevo cliente", description = "Este endpoint permite crear un nuevo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Cliente creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class))),

    })
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toEntity();
        ClienteResponse response = ClienteResponse.fromEnity(clienteService.guardar(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/kpideclientes")
    @Operation(
            summary = "Obtener KPI de Clientes",
            description = "Este endpoint permite obtener las métricas clave de rendimiento (KPI) relacionadas con los clientes, que incluyen: " +
                    "- Promedio de edad entre todos los clientes. " +
                    "- Desviación estándar de las edades de todos los clientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "KPI de clientes obtenidos exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteKPIResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = KPI_NO_OBTENIDOS,
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ClienteKPIResponse> ontenerKPI() {
        ClienteKPI kpi = clienteService.obtenerKPI();
        ClienteKPIResponse response = ClienteKPIResponse.fromDTO(kpi);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/listclientes")
    @Operation(
            summary = "Listar personas con fechas probables de muerte",
            description = "Este endpoint permite obtener una lista de personas que incluye todos sus datos relevantes, " +
                    "junto con la fecha probable de muerte calculada en base a su fecha de nacimiento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de personas obtenida exitosamente, incluyendo todos los datos y la fecha probable de muerte.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClienteConFechaProbableMuerteResponse.class)))), // Aquí se usa @ArraySchema
            @ApiResponse(responseCode = "500",
                    description = FECHAS_PROBABLES_NO_OBTENIDAS,
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ClienteConFechaProbableMuerteResponse>> listarClientes() {
        List<ClienteConFechaProbableMuerte> clientes = clienteService.listarConFechasProbables();
        List<ClienteConFechaProbableMuerteResponse> responseList = clientes.stream()
                .map(ClienteConFechaProbableMuerteResponse::fromDTO)
                .toList();
        return ResponseEntity.ok(responseList);
    }


}
