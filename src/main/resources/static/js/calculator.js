const addStepButton = document.querySelector("#addStepButton");

const stepModule = (() => {

	// Je récupère ma chaîne de caractère envoyé par le controlleur et la transforme en JSON
	let portListIterator = 3;
	const portList = JSON.parse(ports);
	// Queryselector
	const pointer = document.querySelector("#pointer");
	const ignoreList = [];

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
				<select id="step${portListIterator}" class="form-select" aria-label="Sélection de l'étape" name="step${portListIterator}">
				${generateOptions()}
				</select> 
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
	return {
		insert
	};
})();
addStepButton.addEventListener("click", stepModule.insert);





const addStep = () => {

	//    const option = document.createElement("option");
	//    option.textContent = "Ok";
	//
	//    const stepField = document.createElement("select");
	//    stepField.appendChild(option);
	//    stepField.classList.add("form-select");
	//    
	//    
	//    const label = document.createElement("label");
	//    label.classList.add("form-label");
	//    label.textContent = "Ajoutez une étape à votre itinéraire";
	//
	//    const selectorComponent = document.createElement("div");
	//    selectorComponent.appendChild(label);
	//    selectorComponent.appendChild(stepField);
	//	
	//    pointer.insertAdjacentElement("beforebegin", selectorComponent);

	const generateOptions = () => {
		let portOptionsHtml = "";
		for (i = 0; i < portList.length; i++) {
			portOptionsHtml += `<option value="${portList[i].idPort}"
						name="step${portListIterator}">${portList[i].name}</option>`;
		}
		return portOptionsHtml;
	}

	const newSelect = `<div>
				<label for="step${portListIterator}" class="form-label">Ajoutez une étape à votre itinéraire</label> 
				<select id="step${portListIterator}" class="form-select" aria-label="Default select example" name="step${portListIterator}">
				${generateOptions()}
				</select> 
				</div>`;

	pointer.insertAdjacentHTML("beforebegin", newSelect);

	if (portListIterator <= portList.length) {
		portListIterator++;
	} else {
		addStepButton.remove();
	}
}



