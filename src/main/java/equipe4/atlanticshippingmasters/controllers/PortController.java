package equipe4.atlanticshippingmasters.controllers;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.service.PortService;

@Controller
public class PortController {
	
	@Autowired
	private PortService ps;
	
	@GetMapping("/")
	public String index(Model model) {
		Iterable<Port> iterablePortlist=ps.getAllPorts();
		
		List<Port> portList = StreamSupport.stream(iterablePortlist.spliterator(), false).collect(Collectors.toList()).subList(0, 3);
		
		model.addAttribute("ports",portList);
		
		return "index";

	}


	@GetMapping("/port/{id}")
	public String portView(@PathVariable Integer id, Model model) {
		
		// Chaîner la méthode orElse permet de renvoyer comme variable un Port et non un Optional<Port>
		model.addAttribute("port", ps.getPort(id).orElse(null));
		return "ports";
	}

}
