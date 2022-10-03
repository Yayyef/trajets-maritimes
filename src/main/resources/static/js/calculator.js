
// On créé un module pour encapsuler mes méthodes et ne rendre disponible que le nécéssaire dans le global scope
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
		// On fait une boucle pour créér les options
		for (i = 0; i < portList.length; i++) {
			portOptionsHtml += `<option value="${portList[i].idPort}"
						name="port${portListIterator}">${portList[i].name}</option>`;
		}
		return portOptionsHtml;
	}
	const generateSelect = () => {
		return `<div>
				
				<label for="port${portListIterator}" class="form-label">Ajoutez une étape à votre itinéraire</label> 
				<div  class="d-flex">
				
				<div>
					<select id="port${portListIterator}" class="form-select" aria-label="Sélection de l'étape" name="port${portListIterator}">
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
	
	// On renvoie seulement la fonction insert
	return {
		insert
	};
})();
window.addEventListener("load", stepModule.insert);
window.addEventListener("load", stepModule.insert);
// On sélectionne notre boutton dans le html et on lui ajoute l'événement insert
const addStepButton = document.querySelector("#addStepButton");
addStepButton.addEventListener("click", stepModule.insert);
