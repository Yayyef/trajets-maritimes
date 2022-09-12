const addStepButton = document.querySelector("#addStepButton");

// On créé un 
const stepModule = (() => {

	// Je récupère ma chaîne de caractère envoyé par le controlleur et la transforme en JSON
	let portListIterator = 3;
	// On récupère la chaîne de caractères ports, on la converti au format JSON pour en exploiter le contenu lors de la génération du HTML.
	const portList = JSON.parse(ports);
	// Queryselector permet de récupérer un élément du DOM (Document Object Model, la représentation des noeuds html sous form d'abre) comme variable pour pouvoir le manipuler
	const pointer = document.querySelector("#pointer");
	const ignoreList = [];

	// On génère le html
	const generateOptions = () => {
		let portOptionsHtml = "";
		// On fait une boucle pour créér les 
		for (i = 0; i < portList.length; i++) {
			portOptionsHtml += `<option value="${portList[i].idPort}"
						name="step${portListIterator}">${portList[i].name}</option>`;
		}
		return portOptionsHtml;
	}
	const generateSelect = () => {
		return `<div>
				
				<label for="step${portListIterator}" class="form-label">Ajoutez une étape à votre itinéraire</label> 
				<div  class="d-flex">
				
				<div>
					<select id="step${portListIterator}" class="form-select" aria-label="Sélection de l'étape" name="step${portListIterator}">
				${generateOptions()}
				</select> 
				</div>
				
				<button type="button" id="removeButton" class="btn btn-danger">Supprimer étape</button>
				</div>
				
				</div>`;
	}

	const insert = () => {
		if (portListIterator === portList.length - 1) {
			pointer.insertAdjacentHTML("beforebegin", generateSelect());
			addStepButton.remove();
		} else {
			pointer.insertAdjacentHTML("beforebegin", generateSelect());
			portListIterator++;
		}

	}
	
	// On renvoie un seulement la fonction insert
	return {
		insert
	};
})();

addStepButton.addEventListener("click", stepModule.insert);
