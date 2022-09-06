const addStepButton = document.querySelector("#addStepButton");

const addStep = () => {
    const option = document.createElement("option");
    option.textContent = "Ok";
    const stepField = document.createElement("select");
    stepField.appendChild(option);
    stepField.classList.add("form-select");
    console.log(stepField);
    // stepField.textContent = "Nouvel élément";
    addStepButton.insertAdjacentElement("beforebegin", stepField);
}

addStepButton.addEventListener("click", addStep);