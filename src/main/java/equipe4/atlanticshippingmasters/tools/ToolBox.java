package equipe4.atlanticshippingmasters.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import equipe4.atlanticshippingmasters.model.Port;

public class ToolBox {
	
	public List<Port> shortenPortList(Iterable<Port> iterablePortlist){
		//converted itrableportlist to a list
		List<Port> portList= StreamSupport.stream(iterablePortlist.spliterator(), false).collect(Collectors.toList());
		
		List<Port> randomList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			int randomIndex = random.nextInt(portList.size());
			Port randomPort= portList.get(randomIndex);
			randomList.add(randomPort);
			portList.remove(randomIndex);
		}
		
		return randomList;
	}
}
