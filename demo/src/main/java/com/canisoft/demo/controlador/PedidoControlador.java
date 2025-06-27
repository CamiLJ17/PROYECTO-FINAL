package com.canisoft.demo.controlador;

import com.canisoft.demo.logica.PedidoLogica;
import com.canisoft.demo.modelo.Pedido;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControlador {

    private final PedidoLogica pedidoLogica;

    public PedidoControlador(PedidoLogica pedidoLogica) {
        this.pedidoLogica = pedidoLogica;
    }

    // ✅ DTO para recibir JSON con dirección y forma de pago
    @Data
    public static class CrearPedidoRequest {
        private Long idUsuario;
        private String direccionEnvio;
        private String formaPago;
    }

    // ✅ Crear un pedido desde el carrito del usuario (usando JSON completo)
    @PostMapping("/crear")
    public ResponseEntity<Pedido> crearPedidoDesdeCarrito(@RequestBody CrearPedidoRequest request) {
        Pedido pedido = pedidoLogica.crearPedidoDesdeCarrito(
                request.getIdUsuario(),
                request.getDireccionEnvio(),
                request.getFormaPago()
        );
        return ResponseEntity.ok(pedido);
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoLogica.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    // Listar pedidos por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable Long idUsuario) {
        List<Pedido> pedidos = pedidoLogica.listarPedidosPorUsuario(idUsuario);
        return ResponseEntity.ok(pedidos);
    }
}
