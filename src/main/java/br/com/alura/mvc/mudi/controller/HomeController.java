package br.com.alura.mvc.mudi.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.javafaker.Faker;

import br.com.alura.mvc.mudi.model.Pedido;

@Controller
public class HomeController {

	private Faker f = new Faker();

	@GetMapping("/home")
	public String home(Model model) throws IllegalArgumentException, ParseException {

		List<Pedido> pedidos = new ArrayList<Pedido>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < 20; i++) {
			Pedido pedido = new Pedido();
			pedido.setNomeProduto(f.commerce().productName());
			pedido.setValorNegociado(new BigDecimal(f.commerce().price().replace(",", ".")));
			pedido.setDataDaEntrega(f.date().between(sdf.parse("01/01/1980"), sdf.parse("31/12/2022")));
			pedido.setUrlImagem("https://images-na.ssl-images-amazon.com/images/I/81UgYuadkpL._AC_SL1500_.jpg");
			pedido.setUrlProduto(f.internet().domainName());
			pedido.setDescricao(f.lorem().paragraph());
			pedidos.add(pedido);
		}
		model.addAttribute("pedidos", pedidos);
		return "home";
	}
}
